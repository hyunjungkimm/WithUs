package com.example.fundingapi.exception.entity.user;

import com.example.fundingapi.error.ErrorCode;
import com.example.fundingapi.exception.entity.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
