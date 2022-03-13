package com.example.fundingapi.exception.service.funding;

import com.example.fundingapi.error.ErrorCode;
import com.example.fundingapi.exception.service.ServiceException;

public class FundingServiceException extends ServiceException {


    public FundingServiceException(ErrorCode errorCode) {
        super(errorCode);
    }

}