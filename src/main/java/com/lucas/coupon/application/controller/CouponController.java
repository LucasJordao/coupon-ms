package com.lucas.coupon.application.controller;

import com.lucas.coupon.application.request.CouponRequest;
import com.lucas.coupon.application.response.CouponResponse;
import com.lucas.coupon.domain.model.Coupon;
import com.lucas.coupon.domain.port.in.CreateCouponUseCase;
import com.lucas.coupon.domain.port.in.DeleteCouponUseCase;
import com.lucas.coupon.domain.port.in.GetCouponUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class CouponController {
    private final CreateCouponUseCase createCouponUseCase;
    private final GetCouponUseCase getCouponUseCase;
    private final DeleteCouponUseCase deleteCouponUseCase;
    @PostMapping
    public ResponseEntity<CouponResponse> createCoupon(@Valid @RequestBody CouponRequest request) {
        Coupon couponSaved = createCouponUseCase.execute(request.toModel());

        return ResponseEntity.created(URI.create("/coupon/" + couponSaved.getId()))
                .body(couponSaved.toResponse());
    }

    @GetMapping("/{couponId}")
    public ResponseEntity<CouponResponse> getCoupon(@PathVariable(name = "couponId", required = true) UUID couponId) {
        Coupon couponFounded = getCouponUseCase.execute(couponId);

        return ResponseEntity.ok(couponFounded.toResponse());
    }

    @DeleteMapping("/{couponId}")
    public ResponseEntity<?> deleteCoupon(@PathVariable(name = "couponId", required = true) UUID couponId) {
        deleteCouponUseCase.execute(couponId);

        return ResponseEntity.noContent().build();
    }
}
