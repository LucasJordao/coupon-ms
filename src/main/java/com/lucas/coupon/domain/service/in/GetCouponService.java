package com.lucas.coupon.domain.service.in;

import com.lucas.coupon.domain.exceptions.CouponAlreadyExistsException;
import com.lucas.coupon.domain.exceptions.CouponNotFoundException;
import com.lucas.coupon.domain.exceptions.InvalidCodeException;
import com.lucas.coupon.domain.model.Coupon;
import com.lucas.coupon.domain.port.in.CreateCouponUseCase;
import com.lucas.coupon.domain.port.in.GetCouponUseCase;
import com.lucas.coupon.domain.port.out.CouponRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCouponService implements GetCouponUseCase {
    private final CouponRepositoryPort repositoryPort;

    @Override
    public Coupon execute(UUID couponId) {
        Optional<Coupon> couponFounded = repositoryPort.findById(couponId);

        if(couponFounded.isEmpty()) throw new CouponNotFoundException("O cupom procurado não existe.");

        return couponFounded.get();
    }
}
