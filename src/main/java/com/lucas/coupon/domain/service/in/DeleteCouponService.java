package com.lucas.coupon.domain.service.in;

import com.lucas.coupon.domain.exceptions.CouponNotFoundException;
import com.lucas.coupon.domain.model.Coupon;
import com.lucas.coupon.domain.port.in.DeleteCouponUseCase;
import com.lucas.coupon.domain.port.in.GetCouponUseCase;
import com.lucas.coupon.domain.port.out.CouponRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteCouponService implements DeleteCouponUseCase {
    private final CouponRepositoryPort repositoryPort;

    @Override
    public void execute(UUID couponId) {
        Optional<Coupon> couponFounded = repositoryPort.findById(couponId);

        if(couponFounded.isEmpty()) throw new CouponNotFoundException("O cupom não existe.");

        repositoryPort.delete(couponFounded.get());

        log.info("Deleted with successfully [{}]", couponId);
    }
}
