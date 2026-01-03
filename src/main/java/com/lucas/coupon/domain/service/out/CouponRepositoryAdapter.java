package com.lucas.coupon.domain.service.out;

import com.lucas.coupon.domain.exceptions.DatabaseException;
import com.lucas.coupon.domain.model.Coupon;
import com.lucas.coupon.domain.port.out.CouponRepositoryPort;
import com.lucas.coupon.infraestructure.entity.CouponEntity;
import com.lucas.coupon.infraestructure.repository.CouponJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class CouponRepositoryAdapter implements CouponRepositoryPort {
    private final CouponJpaRepository repository;

    @Override
    public Optional<Coupon> findByCode(String code) {
        Optional<CouponEntity> entity = repository.findByCode(code);

        return entity.map(CouponEntity::toModel);

    }

    @Override
    public Coupon save(Coupon coupon) {
        try {
            return repository.save(coupon.toEntity()).toModel();
        } catch (Exception ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    @Override
    public Optional<Coupon> findById(UUID couponId) {
        return repository.findById(couponId).map(CouponEntity::toModel);
    }

    @Override
    public void delete(Coupon coupon) {
        try {
            repository.delete(coupon.toEntity());
        } catch (Exception ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }
}
