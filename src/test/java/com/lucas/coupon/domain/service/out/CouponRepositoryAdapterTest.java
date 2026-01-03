package com.lucas.coupon.domain.service.out;

import com.lucas.coupon.domain.exceptions.DatabaseException;
import com.lucas.coupon.domain.exceptions.ErrorCode;
import com.lucas.coupon.domain.model.Coupon;
import com.lucas.coupon.infraestructure.repository.CouponJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stubs.CouponStubs;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponRepositoryAdapterTest {

    @InjectMocks
    private CouponRepositoryAdapter service;

    @Mock
    private CouponJpaRepository repository;

    @Test
    @DisplayName("Deve encontrar um cupom por codigo com sucesso")
    public void shouldFindACouponWithSuccess_WhenCodeIsValid() {
        var entity = CouponStubs.createEntity();

        when(repository.findByCode(entity.getCode())).thenReturn(Optional.of(entity));

        Optional<Coupon> response = service.findByCode(entity.getCode());

        assertTrue(response.isPresent());
        assertEquals(entity.toModel(), response.get());
    }

    @Test
    @DisplayName("Deve encontrar um cupom por id com sucesso")
    public void shouldFindACouponWithSuccess_WhenIdIsValid() {
        var entity = CouponStubs.createEntity();

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        Optional<Coupon> response = service.findById(entity.getId());

        assertTrue(response.isPresent());
        assertEquals(entity.toModel(), response.get());
    }

    @Test
    @DisplayName("Deve criar um cupom com sucesso")
    public void shouldCreateACouponWithSuccess_WhenRequestIsValid() {
        var coupon = CouponStubs.createCoupon();

        when(repository.save(coupon.toEntity())).thenReturn(coupon.toEntity());

        Coupon response = service.save(coupon);

        assertEquals(coupon, response);
    }

    @Test
    @DisplayName("Deve dar erro ao criar um cupom")
    public void shouldThrowErrorToCreateACoupons_WhenInternalServerError() {
        var coupon = CouponStubs.createCoupon();

        when(repository.save(coupon.toEntity())).thenThrow(DatabaseException.class);

        DatabaseException response = assertThrows(DatabaseException.class, () -> service.save(coupon));

        assertEquals(ErrorCode.DATABASE_ERROR, response.getError());
    }

    @Test
    @DisplayName("Deve deletar um cupom com sucesso")
    public void shouldDeleteACouponWithSuccess_WhenRequestIsValid() {
        var coupon = CouponStubs.createCoupon();

        doNothing().when(repository).delete(coupon.toEntity());

        service.delete(coupon);

        verify(repository, times(1)).delete(coupon.toEntity());
    }

    @Test
    @DisplayName("Deve dar erro ao deletar um cupom")
    public void shouldThrowErrorToDeleteACoupons_WhenInternalServerError() {
        var coupon = CouponStubs.createCoupon();

        doThrow(DatabaseException.class).when(repository).delete(coupon.toEntity());

        DatabaseException response = assertThrows(DatabaseException.class, () -> service.delete(coupon));

        assertEquals(ErrorCode.DATABASE_ERROR, response.getError());
    }
}