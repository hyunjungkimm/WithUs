package com.example.fundingapi.exception.handler;

import com.example.fundingapi.error.ErrorCode;
import com.example.fundingapi.error.ErrorResponse;
import com.example.fundingapi.exception.BusinessException;
import com.example.fundingapi.exception.entity.EntityNotFoundException;
import com.example.fundingapi.exception.entity.user.UserNotFoundException;
import com.example.fundingapi.exception.service.ServiceException;
import com.example.fundingapi.exception.service.funding.FundingServiceException;
import com.example.fundingapi.exception.service.user.UserServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value=BusinessException.class)
    protected ResponseEntity<ErrorResponse> BusinessException(final BusinessException e) {
        log.error("BusinessException", e);
        ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}

