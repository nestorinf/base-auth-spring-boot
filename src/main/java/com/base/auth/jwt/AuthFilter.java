package com.base.auth.jwt;

import com.base.auth.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    JwtUtils jwtUtils;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getJwtFromRequest(request);

            String username = jwtUtils.getUserNameFromJwtToken(token);

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                 if(jwtUtils.validateJwtToken(token)) {
                     UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                             userDetails, null, userDetails.getAuthorities()
                     );
                     authenticationToken.setDetails(
                             new WebAuthenticationDetailsSource().buildDetails(request)
                     );

                     SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                 }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        filterChain.doFilter(request, response);
    }
    private String getJwtFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token !=null && token.startsWith("Bearer ")) {
            return token.substring(7, token.length());
        }
        return null;
    }
}
