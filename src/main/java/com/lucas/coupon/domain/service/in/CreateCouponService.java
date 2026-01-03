package com.lucas.coupon.domain.service.in;

import com.lucas.coupon.domain.exceptions.CouponAlreadyExistsException;
import com.lucas.coupon.domain.exceptions.InvalidCodeException;
import com.lucas.coupon.domain.model.Coupon;
import com.lucas.coupon.domain.port.in.CreateCouponUseCase;
import com.lucas.coupon.domain.port.out.CouponRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateCouponService implements CreateCouponUseCase {
    private final CouponRepositoryPort repositoryPort;

    @Override
    public Coupon execute(Coupon coupon) {
        coupon.setCode(
            this.normalizeCode(coupon.getCode())
        );
        Optional<Coupon> couponFounded = repositoryPort.findByCode(coupon.getCode());

        if(couponFounded.isPresent()) throw new CouponAlreadyExistsException("O codigo de cupom informado já existe.");

        Coupon entitySaved = repositoryPort.save(coupon);

        log.info("Saved with successfully");

        return entitySaved;
    }

    private String normalizeCode(String code) {
        if (code == null) {
            log.error("O código não pode ser nulo.");
            throw new InvalidCodeException("O código não pode ser nulo.");
        }

        String cleaned = code.replaceAll("[^A-Za-z0-9]", "");

        if (cleaned.length() > 6) {
            cleaned = cleaned.substring(0, 6);
        }

        if(cleaned.length() < 6) {
            log.error("O código não pode ser menor que 6 dígitos");
            throw new InvalidCodeException("O código não pode ser menor que 6 dígitos");
        }

        return cleaned.toUpperCase();
    }
}
