package com.example.fundingapi.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_INPUT_VALUE("Parameter가 Invalid 합니다.", "M001"),
    NOT_EXISTS_USED_ID_HEADER("Header에 USER ID가 없습니다.", "M002"),
    NOT_SIGNED_UP_USER("가입된 사용자가 아닙니다. ", "M003"),
    SOLD_OUT("이미 모집 완료 되었습니다. ", "M004"),
    FUNDING_AMOUNT_IS_GREATER_THAN_CAN_BE_FUNDED_AMOUNT("펀딩 금액이 펀딩 가능한 잔여 금액을 초과하였습니다", "M005"),
    ALREADY_INVESTING_PRODUCT("이미 펀딩에 참여한 상품입니다.", "M006")
    ;


    private final String message;
    private final String code;

    ErrorCode(String message, String code) {

        this.message = message;
        this.code = code;
    }

}
