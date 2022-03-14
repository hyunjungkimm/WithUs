package com.example.fundingapi.exception.handler;

import com.example.fundingapi.error.ErrorCode;
import com.example.fundingapi.error.ErrorResponse;
import com.example.fundingapi.exception.entity.EntityNotFoundException;
import com.example.fundingapi.exception.service.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value= {ConstraintViolationException.class, MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(HttpServletRequest e){
        log.error("handleMethodArgumentNotValidException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.REQUIRED_VALUE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);//400
    }

    @ExceptionHandler(value= UnexpectedTypeException.class)
    protected ResponseEntity<ErrorResponse> UnexpectedTypeException(UnexpectedTypeException e){
        log.error("UnexpectedTypeException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.REQUIRED_VALUE);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);//500
    }

    @ExceptionHandler(value=EntityNotFoundException.class)
    protected ResponseEntity<ErrorResponse> EntityNotFoundException(final EntityNotFoundException e) {
        log.error("EntityNotFoundException", e);
        ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);//404
    }

    @ExceptionHandler(value=ServiceException.class)
    protected ResponseEntity<ErrorResponse> ServiceException(final ServiceException e) {
        log.error("ServiceException", e);
        ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);//500
    }

}

