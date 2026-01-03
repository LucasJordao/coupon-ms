package com.lucas.coupon.domain.exceptions;

import org.springframework.http.HttpStatus;

public class CouponNotFoundException extends BusinessException{
    public CouponNotFoundException(String message) {
        super(ErrorCode.COUPON_NOT_FOUND, HttpStatus.NOT_FOUND, message);
    }
}
