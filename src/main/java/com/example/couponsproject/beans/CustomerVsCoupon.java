package com.example.couponsproject.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table(name = "Customer_Vs_Coupon" )

public class CustomerVsCoupon {
    public CustomerVsCoupon(Long couponId, Long companyId) {
        this.couponId = couponId;
        this.companyId = companyId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coupon_Id",nullable = false)
    private Long couponId;

    @Column(name = "company_Id",nullable = false)
    private Long companyId;
}
