package com.example.fundingapi.exception.service.user;

import com.example.fundingapi.error.ErrorCode;
import com.example.fundingapi.exception.service.ServiceException;
import lombok.Getter;

@Getter
public class UserServiceException extends ServiceException {

    private final ErrorCode errorCode;

    public UserServiceException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
