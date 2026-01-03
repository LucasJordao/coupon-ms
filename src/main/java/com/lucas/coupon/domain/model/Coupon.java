package com.lucas.coupon.domain.model;

import com.lucas.coupon.application.response.CouponResponse;
import com.lucas.coupon.application.response.CouponStatusResponse;
import com.lucas.coupon.infraestructure.entity.CouponEntity;
import com.lucas.coupon.infraestructure.entity.CouponStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class Coupon {
    private UUID id;
    private String code;
    private String description;
    private BigDecimal discountValue;
    private LocalDateTime expirationDate;
    @Builder.Default
    private CouponStatus status = CouponStatus.ACTIVE;
    @Builder.Default
    private Boolean published = false;
    @Builder.Default
    private Boolean redeemed = false;

    public CouponEntity toEntity() {
        return CouponEntity.builder()
                .id(this.id)
                .code(this.code)
                .description(this.description)
                .discountValue(this.discountValue)
                .expirationDate(this.expirationDate)
                .published(this.published)
                .status(CouponStatusType.valueOf(this.status.name()))
                .redeemed(this.redeemed)
                .deleted(false)
                .build();
    }

    public CouponResponse toResponse() {
        return CouponResponse.builder()
                .id(this.id)
                .code(this.code)
                .description(this.description)
                .discountValue(this.discountValue.setScale(1, RoundingMode.HALF_UP))
                .expirationDate(this.expirationDate)
                .published(this.published)
                .status(CouponStatusResponse.valueOf(this.status.name()))
                .redeemed(this.redeemed)
                .build();
    }
}
