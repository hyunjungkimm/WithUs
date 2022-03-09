package com.example.fundingapi.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class ErrorResponse {
    private String message;
    private String code;
}
