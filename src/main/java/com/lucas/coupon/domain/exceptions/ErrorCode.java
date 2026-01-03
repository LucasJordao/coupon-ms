package com.lucas.coupon.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_ERROR("CP000", "Erro interno."),
    DATABASE_ERROR("CP001", "Erro interno."),
    INVALID_CODE("CP002", "O código fornecido é inválido."),
    BAD_REQUEST("CP003", "Valor fornecido é inválido."),
    COUPON_ALREADY_EXISTS("CP004", "O cupom com o código informado já existe."),
    COUPON_NOT_FOUND("CP005", "O cupom não existe.");

    private final String code;
    private final String message;
}
