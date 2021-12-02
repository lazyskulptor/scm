package com.hyeonjun.scm.controller;

import java.util.HashMap;
import java.util.Map;

import com.hyeonjun.scm.domain.errors.FormSyntaxException;
import com.hyeonjun.scm.domain.errors.NoEntityException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { FormSyntaxException.class })
    protected ResponseEntity<Object> handleSyntaxErr(FormSyntaxException ex, WebRequest request) {
        Map<String, Object> dto = new HashMap<>();
        dto.put("code", ex.getCode());
        dto.put("message", ex.getMessage());
        return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { NoEntityException.class })
    protected ResponseEntity<Object> handleNotFoundEntity(NoEntityException ex, WebRequest request) {
        Map<String, Object> dto = new HashMap<>();
        dto.put("code", ex.getCode());
        dto.put("message", ex.getMessage());
        return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
