package com.lucas.coupon.domain.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {
    private ErrorCode error;
    private HttpStatus statusCode;

    public BusinessException(ErrorCode error, HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.error = error;
    }
}
