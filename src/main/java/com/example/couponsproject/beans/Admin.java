package com.example.couponsproject.beans;

import com.example.couponsproject.enums.Role;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "admin")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @ToString.Exclude
    @Column(name = "password", nullable = false)
    private Integer password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
