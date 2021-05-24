package com.tenpo.math.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    APIError illegalArgumentExceptionHandler(MethodArgumentNotValidException ex) {
        logger.error(ex.getMessage());
        return new APIError("Request mal formada");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    APIError illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        logger.error(ex.getMessage());
        return new APIError("Request mal formada");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    APIError resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        logger.error(ex.getMessage());
        return new APIError("Recurso no encontrado");
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(UNAUTHORIZED)
    APIError InvalidTokenExceptionHandler(InvalidTokenException ex){
        logger.error(ex.getMessage());
        return new APIError("Token de autenticación invalido");
    }

    @ExceptionHandler(DuplicatedEntityException.class)
    @ResponseStatus(CONFLICT)
    APIError DuplicatedEntityExceptionHandler(DuplicatedEntityException ex){
        logger.error(ex.getMessage());
        return new APIError(ex.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    APIError HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex){
        logger.error((ex.getMessage()));
        return  new APIError(String.format("HTTP request method not allowed. Methoh: %s", ex.getMethod()));
    }

}
