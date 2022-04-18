package com.example.couponsproject.beans;

import com.example.couponsproject.enums.Category;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.web.JsonPath;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "coupon" )

public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false)
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


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
            name = "customer_to_coupon",
            joinColumns = @JoinColumn(name = "coupon_Id"),
            inverseJoinColumns = @JoinColumn(name = "customer_Id")
    )
    private List<Customer> customerList;

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", companyId=" + company.getId() +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
