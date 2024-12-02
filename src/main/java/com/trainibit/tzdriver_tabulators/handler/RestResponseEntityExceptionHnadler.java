package com.trainibit.tzdriver_tabulators.handler;

import com.trainibit.tzdriver_tabulators.response.ApiErrorResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

public class RestResponseEntityExceptionHnadler {

    @ControllerAdvice
    public class RestResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {

        @ExceptionHandler(value = {NoSuchElementException.class})
        protected ResponseEntity<Object> handleNoSuchElement(RuntimeException ex, WebRequest request) {
            ApiErrorResponse bodyOfResponse = new ApiErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
            return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
        }

        @ExceptionHandler(value = {DataAccessException.class})
        protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
            ApiErrorResponse bodyOfResponse = new ApiErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
            return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
        }

        @ExceptionHandler(value = {BadRequestException.class})
        protected ResponseEntity<Object> handleBadRequestException(RuntimeException ex, WebRequest request) {
            ApiErrorResponse bodyOfResponse = new ApiErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
            return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        }
    }

}
