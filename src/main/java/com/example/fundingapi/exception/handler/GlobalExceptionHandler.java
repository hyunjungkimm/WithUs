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

    @ExceptionHandler(UserServiceException.class)
    protected ResponseEntity<ErrorResponse> UserServiceException(UserServiceException e) {
        log.error("UserServiceException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_EXISTS_USED_ID_HEADER);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FundingServiceException.class)
    protected ResponseEntity<ErrorResponse> FundingServiceException(FundingServiceException e) {
        log.error("FundingServiceException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.SOLD_OUT);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final UserNotFoundException e) {
        log.error("handleEntityNotFoundException", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_SIGNED_UP_USER);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }




}

