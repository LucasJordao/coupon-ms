package com.lucas.coupon.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponResponse {
    private UUID id;
    private String code;
    private String description;
    private BigDecimal discountValue;
    private LocalDateTime expirationDate;
    private CouponStatusResponse status;
    private Boolean published;
    private Boolean redeemed;
}
