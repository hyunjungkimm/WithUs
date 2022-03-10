package com.example.fundingapi.exception.service;

import com.example.fundingapi.error.ErrorCode;
import com.example.fundingapi.exception.BusinessException;
import lombok.Getter;

@Getter
public class ServiceException extends BusinessException {

    private final ErrorCode errorCode;

    public ServiceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
