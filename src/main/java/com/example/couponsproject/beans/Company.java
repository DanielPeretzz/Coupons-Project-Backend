package com.example.couponsproject.beans;

import com.example.couponsproject.service.AdminService;
import jdk.jfr.TransitionFrom;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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

    @OneToMany(mappedBy = "company" , fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coupon> couponList;
}
