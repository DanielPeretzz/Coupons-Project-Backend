package com.example.couponsproject.beans;

import com.example.couponsproject.enums.Category;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Builder
@Table(name = "coupon" )

public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JoinColumn(name = "company_Id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER )
    private Company company;

    @Column(name = "category",nullable = false)
    @Enumerated(EnumType.STRING)
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


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "customer_to_coupon",
            joinColumns = @JoinColumn(name = "coupon_Id"),
            inverseJoinColumns = @JoinColumn(name = "customer_Id")
    )
    private List<Customer> customerList;


}
