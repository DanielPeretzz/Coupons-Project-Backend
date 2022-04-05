package com.example.couponsproject.beans;
import com.example.couponsproject.enums.Category;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "coupon" )

public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "company_Id", nullable = false)
    @ManyToOne
    private  Company company;

    @Column(name = "category",nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Category category;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_Date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_Date", nullable = false)
    private LocalDate endDate;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "image", nullable = false)
    private String image;
}
