package stubs;

import com.lucas.coupon.domain.model.Coupon;
import com.lucas.coupon.infraestructure.entity.CouponEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CouponStubs {
    public static Coupon createCoupon() {
        return Coupon.builder()
                .id(UUID.randomUUID())
                .code("XPTO12")
                .redeemed(false)
                .description("Descrição teste")
                .discountValue(BigDecimal.valueOf(2L))
                .expirationDate(LocalDateTime.MAX)
                .published(true)
                .build();
    }

    public static CouponEntity createEntity() {
        return CouponEntity.builder()
                .id(UUID.randomUUID())
                .code("XPTO12")
                .redeemed(false)
                .description("Descrição teste")
                .discountValue(BigDecimal.valueOf(2L))
                .expirationDate(LocalDateTime.MAX)
                .published(true)
                .build();
    }
}
