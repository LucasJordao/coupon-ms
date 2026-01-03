package com.lucas.coupon.infraestructure.repository;

import com.lucas.coupon.infraestructure.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CouponJpaRepository extends JpaRepository<CouponEntity, UUID> {
    Optional<CouponEntity> findByCode(String code);
}
