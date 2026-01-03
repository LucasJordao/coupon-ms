package com.lucas.coupon.infraestructure.entity;

import com.lucas.coupon.domain.model.Coupon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_coupons")
@SQLDelete(sql = "UPDATE tb_coupons SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public class CouponEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private BigDecimal discountValue;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    @Column(nullable = false)
    private boolean published;

    @Column(nullable = false)
    private boolean redeemed;

    @Column(nullable = false)
    @Builder.Default
    private boolean deleted = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CouponStatusType status = CouponStatusType.ACTIVE;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Coupon toModel() {
        return Coupon.builder()
                .id(this.id)
                .code(this.code)
                .description(this.description)
                .discountValue(this.discountValue)
                .expirationDate(this.expirationDate)
                .published(this.published)
                .redeemed(this.redeemed)
                .build();
    }
}
