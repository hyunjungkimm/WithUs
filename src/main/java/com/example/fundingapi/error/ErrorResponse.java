package com.example.fundingapi.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class ErrorResponse {
    private String message;
    private String code;

    public ErrorResponse(ErrorCode code) {
        this.message = code.getMessage();
        this.code =  code.getCode();
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }


}
