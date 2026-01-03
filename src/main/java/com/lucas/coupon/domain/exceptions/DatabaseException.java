package com.lucas.coupon.domain.exceptions;

import org.springframework.http.HttpStatus;

public class DatabaseException extends BusinessException{
    public DatabaseException(String message) {
        super(ErrorCode.DATABASE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
