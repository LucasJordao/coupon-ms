package com.lucas.coupon.domain.port.in;

import com.lucas.coupon.domain.model.Coupon;

import java.util.UUID;

public interface DeleteCouponUseCase {
    void execute(UUID couponId);
}
