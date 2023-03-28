package com.base.auth.config;

import com.base.auth.utils.ApiValidationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Map<String, List<String>>> handlerException(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return new ResponseEntity<>(
                errorResponse,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()

                .forEach(error -> errorResponse.put(((FieldError)error).getField(), error.getDefaultMessage()) );

//        System.out.println(e));
//        System.out.println( ex.
//        getBindingResult().getAllErrors().stream().findFirst().stream().forEach(err -> err.getDefaultMessage() ) );
        ApiValidationError apiErrors = new ApiValidationError(ex.getBody().getStatus(), ex.getBody().getTitle(), errorResponse);
        System.out.println(ex.getBody().getStatus()+" "+ex.getBody().getTitle());
        return new ResponseEntity<>(apiErrors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

//    public static Set<ConstraintViolation> getOvalViolations(Object obj){
//
//        Validator validator = new Validator();
//
//        Set<ConstraintViolation> violations = new HashSet(validator.validate(obj));
//
//        return violations;
//    }
}
