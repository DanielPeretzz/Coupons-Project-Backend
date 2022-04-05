package com.example.couponsproject.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class Company {

    public Company(String email, String name, String password, List<Coupon> couponList) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.couponList = couponList;
    }

    public Company(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true , nullable = false)
    private String email;

    @Column(name = "name",unique = true, nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private List<Coupon> couponList;
}
