package com.lucas.coupon.application.request;

import com.lucas.coupon.domain.model.Coupon;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class CouponRequest {
    @NotNull(message = "O código de cupom não pode ser nulo")
    @NotEmpty(message = "O código de cupom não pode ser vazio")
    private String code;
    @NotNull(message = "A descrição não pode ser nula")
    @NotEmpty(message = "A descrição não pode ser vazia")
    private String description;
    @NotNull(message = "O valor de disconto não pode ser nulo")
    @DecimalMin(
            value = "0.5",
            inclusive = true,
            message = "O valor de desconto deve ser no mínimo 0.5"
    )
    private BigDecimal discountValue;
    @NotNull(message = "A data de expiração não pode ser vazia")
    @FutureOrPresent(message = "A data de expiração não pode ser no passado")
    private LocalDateTime expirationDate;
    private Boolean published;

    public Coupon toModel() {
        return Coupon.builder()
                .published(this.published)
                .code(this.code)
                .expirationDate(this.expirationDate)
                .discountValue(this.discountValue)
                .description(this.description)
                .build();
    }
}
