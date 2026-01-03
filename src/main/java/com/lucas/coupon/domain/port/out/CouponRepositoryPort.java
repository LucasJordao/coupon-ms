package com.lucas.coupon.domain.port.out;

import com.lucas.coupon.domain.model.Coupon;

import java.util.Optional;
import java.util.UUID;

public interface CouponRepositoryPort {
    Optional<Coupon> findByCode(String code);
    Coupon save(Coupon coupon);

    Optional<Coupon> findById(UUID couponId);

    void delete(Coupon coupon);
}