package com.example.fundingapi.exception.entity;

import com.example.fundingapi.error.ErrorCode;
import com.example.fundingapi.exception.BusinessException;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends BusinessException {

    private final ErrorCode errorCode;

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
