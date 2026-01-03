package com.lucas.coupon.domain.service.in;

import com.lucas.coupon.domain.exceptions.CouponAlreadyExistsException;
import com.lucas.coupon.domain.exceptions.ErrorCode;
import com.lucas.coupon.domain.exceptions.InvalidCodeException;
import com.lucas.coupon.domain.model.Coupon;
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
class CreateCouponServiceTest {

    @InjectMocks
    private CreateCouponService useCase;

    @Mock
    private CouponRepositoryPort repositoryPort;

    @Test
    @DisplayName("Deve criar um cupom com sucesso")
    public void shouldCreateACouponWithSuccess_WhenRequestIsValid() {
        Coupon coupon = CouponStubs.createCoupon();
        coupon.setCode("123-ABCZD");

        when(repositoryPort.findByCode(any())).thenReturn(Optional.empty());
        when(repositoryPort.save(coupon)).thenReturn(coupon);

        Coupon response = useCase.execute(coupon);

        assertEquals(coupon, response);
        verify(repositoryPort, times(1)).save(coupon);
    }

    @Test
    @DisplayName("Deve dar erro ao criar um cupom com codigo que já existe")
    public void shouldReturnErrorToCreateACoupon_WhenCodeIsAlreadyInUse() {
        Coupon coupon = CouponStubs.createCoupon();

        when(repositoryPort.findByCode(coupon.getCode())).thenReturn(Optional.of(coupon));
        CouponAlreadyExistsException response = assertThrows(CouponAlreadyExistsException.class, () -> useCase.execute(coupon));

        assertEquals(ErrorCode.COUPON_ALREADY_EXISTS, response.getError());
        verify(repositoryPort, never()).save(coupon);
    }

    @Test
    @DisplayName("Deve dar erro ao criar um cupom com codigo nulo")
    public void shouldReturnErrorToCreateACoupon_WhenCodeIsNull() {
        Coupon coupon = CouponStubs.createCoupon();
        coupon.setCode(null);

        InvalidCodeException response = assertThrows(InvalidCodeException.class, () -> useCase.execute(coupon));

        assertEquals(ErrorCode.INVALID_CODE, response.getError());
        verify(repositoryPort, never()).save(coupon);
    }

    @Test
    @DisplayName("Deve dar erro ao criar um cupom com codigo menor que 6 digitos")
    public void shouldReturnErrorToCreateACoupon_WhenCodeIsShortThen6() {
        Coupon coupon = CouponStubs.createCoupon();
        coupon.setCode("123");

        InvalidCodeException response = assertThrows(InvalidCodeException.class, () -> useCase.execute(coupon));

        assertEquals(ErrorCode.INVALID_CODE, response.getError());
        verify(repositoryPort, never()).save(coupon);
    }
}