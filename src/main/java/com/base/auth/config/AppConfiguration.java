package com.base.auth.config;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class AppConfiguration {
//    @Bean
//    public LocaleResolver localResolver() {
//        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        localeResolver.setDefaultLocale(Locale.US);
//        return localeResolver;
//    }
//    @Bean(name = "messageSource")
//    public ReloadableResourceBundleMessageSource loMessageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:messages");
////        messageSource.setUseCodeAsDefaultMessage(true);
//        return messageSource;
//    }
//    @Bean
//    public LocalValidatorFactoryBean getValidator() {
//        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
////        System.out.println(messageSource().getMessage("user.firstname.notEmpty", null, Locale.ENGLISH));
//        bean.setValidationMessageSource(loMessageSource());
//        return bean;
//    }
}