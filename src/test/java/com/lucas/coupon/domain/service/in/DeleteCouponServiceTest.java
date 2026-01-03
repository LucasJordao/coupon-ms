package com.lucas.coupon.domain.service.in;

import com.lucas.coupon.domain.exceptions.CouponNotFoundException;
import com.lucas.coupon.domain.exceptions.ErrorCode;
import com.lucas.coupon.domain.port.out.CouponRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stubs.CouponStubs;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteCouponServiceTest {

    @InjectMocks
    private DeleteCouponService useCase;

    @Mock
    private CouponRepositoryPort repositoryPort;

    @Test
    @DisplayName("Deve deletar um cupom com sucesso")
    public void shouldDeleteACouponWithSuccess_WhenIdIsValid() {
        var coupon = CouponStubs.createCoupon();

        when(repositoryPort.findById(coupon.getId())).thenReturn(Optional.of(coupon));
        doNothing().when(repositoryPort).delete(coupon);

        useCase.execute(coupon.getId());

        verify(repositoryPort, times(1)).delete(coupon);
    }

    @Test
    @DisplayName("Deve dar erro ao deletar um cupom com id inexistente")
    public void shouldThrowErrorToDeleteACoupon_WhenIdIsInvalid() {
        var coupon = CouponStubs.createCoupon();

        when(repositoryPort.findById(coupon.getId())).thenReturn(Optional.empty());

        CouponNotFoundException response = assertThrows(CouponNotFoundException.class, () -> useCase.execute(coupon.getId()));

        assertEquals(ErrorCode.COUPON_NOT_FOUND, response.getError());
    }
}