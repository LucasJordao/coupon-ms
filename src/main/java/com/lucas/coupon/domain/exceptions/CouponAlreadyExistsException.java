package com.lucas.coupon.domain.exceptions;

import org.springframework.http.HttpStatus;

public class CouponAlreadyExistsException extends BusinessException{
    public CouponAlreadyExistsException(String message) {
        super(ErrorCode.COUPON_ALREADY_EXISTS, HttpStatus.UNPROCESSABLE_CONTENT, message);
    }
}
