package com.lucas.coupon.application.request;

import com.lucas.coupon.domain.model.Coupon;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Resposta de cupom")
public class CouponRequest {
    @Schema(
            description = "Código do cupom (alfanumérico, 6 caracteres)",
            example = "ABC-123"
    )
    @NotNull(message = "O código de cupom não pode ser nulo")
    @NotEmpty(message = "O código de cupom não pode ser vazio")
    private String code;
    @NotNull(message = "A descrição não pode ser nula")
    @NotEmpty(message = "A descrição não pode ser vazia")
    @Schema(
            description = "A descrição do cupom",
            example = "Iure saepe amet. Excepturi saepe inventore nam doloremque voluptatem a. Quaerat odio distinctio eos. Dolor debitis ex molestias nam quae hic suscipit odit nulla. Blanditiis ratione facilis nobis quam deserunt. Doloribus iste corrupti magni ipsum illo beatae consectetur."
    )
    private String description;
    @NotNull(message = "O valor de disconto não pode ser nulo")
    @DecimalMin(
            value = "0.5",
            inclusive = true,
            message = "O valor de desconto deve ser no mínimo 0.5"
    )
    @Schema(
            description = "O valor do desconto",
            example = "0.6"
    )
    private BigDecimal discountValue;
    @NotNull(message = "A data de expiração não pode ser vazia")
    @Schema(
            description = "A data de vencimento",
            example = "2026-11-04T17:14:45.180Z"
    )
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
