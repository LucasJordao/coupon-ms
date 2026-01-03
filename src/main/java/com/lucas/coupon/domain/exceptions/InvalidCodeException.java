package com.lucas.coupon.domain.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidCodeException extends BusinessException {
    public InvalidCodeException(String message) {
        super(ErrorCode.INVALID_CODE, HttpStatus.BAD_REQUEST, message);
    }
}
