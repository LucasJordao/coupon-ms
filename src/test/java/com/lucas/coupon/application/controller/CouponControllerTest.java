package com.lucas.coupon.application.controller;

import com.lucas.coupon.infraestructure.entity.CouponEntity;
import com.lucas.coupon.infraestructure.repository.CouponJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CouponJpaRepository repository;

    @Test
    @DisplayName("Deve criar um cupom com sucesso")
    void shouldCreateACouponWithSuccessfully() throws Exception {
        String json = "{"
                + "\"code\": \"AB-12234@#\","
                + "\"description\": \"Cupom de teste\","
                + "\"discountValue\": 1.5,"
                + "\"expirationDate\": \"" + LocalDateTime.now().plusDays(1) + "\","
                + "\"published\": true"
                + "}";

        mockMvc.perform(
                        post("/coupon")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isCreated());

        var coupons = repository.findAll();
        assertThat(coupons).hasSize(1);

        CouponEntity saved = coupons.get(0);
        assertThat(saved.getCode()).isEqualTo("AB1223");
        assertThat(saved.getDiscountValue()).isEqualByComparingTo(new BigDecimal("1.5"));
        assertThat(saved.isPublished()).isTrue();
        assertThat(saved.isDeleted()).isFalse();
    }

}