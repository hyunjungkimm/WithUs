package com.example.fundingapi.error;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
/*@NoArgsConstructor(access =  AccessLevel.PROTECTED)*/
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
