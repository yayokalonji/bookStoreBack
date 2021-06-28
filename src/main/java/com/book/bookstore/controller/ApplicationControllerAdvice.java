package com.book.bookstore.controller;

import com.book.bookstore.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    ResponseEntity<Map<String, String>> handleControllerException(HttpServletRequest request, ApiException apiException) {
        return new ResponseEntity<>(apiException.getJsonMessage(), apiException.getStatus());
    }

    protected ResponseEntity<Object> handleMethodArgumentNoValid(MethodArgumentNotValidException exceptionValidException, HttpStatus httpStatus) {
        return new ResponseEntity<>(exceptionValidException.getFieldErrors(), httpStatus);
    }
}
