package com.lucas.coupon.domain.service.in;

import com.lucas.coupon.domain.exceptions.CouponNotFoundException;
import com.lucas.coupon.domain.exceptions.ErrorCode;
import com.lucas.coupon.domain.model.Coupon;
import com.lucas.coupon.domain.port.out.CouponRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stubs.CouponStubs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCouponServiceTest {
    @InjectMocks
    private GetCouponService useCae;

    @Mock
    private CouponRepositoryPort repositoryPort;

    @Test
    @DisplayName("Deve retornar um cupom com sucesso")
    public void shouldReturnACouponWithSuccess_WhenIdIsValid() {
        var coupon = CouponStubs.createCoupon();
        when(repositoryPort.findById(coupon.getId())).thenReturn(Optional.of(
            coupon
        ));

        Coupon response = useCae.execute(coupon.getId());

        assertNotNull(response);
        assertEquals(coupon.getId(), response.getId());
    }

    @Test
    @DisplayName("Deve retornar erro de cupom não encontrado")
    public void shouldReturnAnException_WhenIdIsInvalid() {
        var id = UUID.randomUUID();
        when(repositoryPort.findById(id)).thenReturn(Optional.empty());

        CouponNotFoundException response = Assertions.assertThrows(CouponNotFoundException.class, () -> useCae.execute(id));

        assertNotNull(response);
        assertEquals(ErrorCode.COUPON_NOT_FOUND.getCode(), response.getError().getCode());
        assertEquals(ErrorCode.COUPON_NOT_FOUND.getMessage(), response.getError().getMessage());
    }
}