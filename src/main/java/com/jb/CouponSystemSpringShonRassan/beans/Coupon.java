package com.jb.CouponSystemSpringShonRassan.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "coupons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(length = 40, nullable = false)
    private String title;
    @Column(length = 40, nullable = false)
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(updatable = false)
    @JsonIgnore
    private Company company;
//    @ManyToMany
//    @ToString.Exclude
//    @JsonIgnore
//    private Customer customer;

}
