package com.gabriel.aula.application.rest.handler;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.gabriel.aula.application.rest.constant.ApiRestConstant.ERROR_409_DEFAULT;
import static com.gabriel.aula.application.rest.constant.ApiRestConstant.ERROR_500_DEFAULT;
import static com.gabriel.aula.application.rest.constant.ApiRestConstant.JSON_DATA_INVALID;

@ControllerAdvice
public class ApiExceptionResponse extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiResponse> handleInternal(final Exception ex) {
        return generatedError(ERROR_500_DEFAULT, HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ApiResponse> handleEntityNotFoundException(final Exception ex) {
        return generatedError(ex.getMessage(), HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ApiResponse> handleServiceException(final Exception ex) {
        return generatedError(ex.getMessage(), HttpStatus.BAD_REQUEST, ex);
    }


    @ExceptionHandler({DataAccessException.class, HibernateException.class, DataIntegrityViolationException.class})
    protected ResponseEntity<ApiResponse> handleConflict(final DataAccessException ex) {
        return generatedError(ERROR_409_DEFAULT, HttpStatus.CONFLICT, ex);
    }


    @ExceptionHandler({HttpMessageConversionException.class})
    public ResponseEntity<ApiResponse> handleHttpMessageConversionException(final RuntimeException ex) {
        return generatedError(JSON_DATA_INVALID, HttpStatus.BAD_REQUEST, ex);
    }

    private ResponseEntity<ApiResponse> generatedError(String message, HttpStatus http, Exception ex) {
        System.err.println(http.value() + ": " + ex.getMessage());
        return new ResponseEntity<>(new ApiResponse(http.value(), message), http);
    }

    private ResponseEntity<ApiResponse> generatedError(int code, String message, Exception ex) {
        System.err.println(code + ": " + ex.getMessage());
        return new ResponseEntity<>(new ApiResponse(code, message), HttpStatus.valueOf(code));
    }
}