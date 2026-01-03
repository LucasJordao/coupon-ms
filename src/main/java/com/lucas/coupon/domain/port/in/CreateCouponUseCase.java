package com.lucas.coupon.domain.port.in;

import com.lucas.coupon.domain.model.Coupon;

public interface CreateCouponUseCase {
    Coupon execute(Coupon coupon);
}
