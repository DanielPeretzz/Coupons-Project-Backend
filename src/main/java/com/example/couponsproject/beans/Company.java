package com.example.couponsproject.beans;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "password")
@Builder

public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true , nullable = false)
    private String email;

    @Column(name = "name",unique = true, nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private Integer password;

    @Transient
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ToString.Exclude
    private List<Coupon> couponList;
}
