package com.example.fundingapi.exception.handler;

import com.example.fundingapi.error.ErrorCode;
import com.example.fundingapi.error.ErrorResponse;
import com.example.fundingapi.exception.service.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundException(
        EntityNotFoundException e,
        HttpServletRequest request
    ){
        final String response = e.getMessage();
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleEntityNotFoundException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_EXISTS_USED_ID_HEADER);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

