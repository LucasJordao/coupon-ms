package com.lucas.coupon.application.handler;

import com.lucas.coupon.domain.exceptions.BusinessException;
import com.lucas.coupon.domain.exceptions.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(
                ErrorResponse.builder()
                        .code(ErrorCode.BAD_REQUEST.getCode())
                        .errors(errors)
                        .build()
        );
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getError().getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(
                ErrorResponse.builder()
                        .errors(error)
                        .code(ex.getError().getCode())
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ErrorCode.INTERNAL_ERROR.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorResponse.builder()
                        .errors(error)
                        .code(ErrorCode.INTERNAL_ERROR.getCode())
                        .build()
        );
    }
}
