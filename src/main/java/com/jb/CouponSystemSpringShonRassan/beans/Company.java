package com.jb.CouponSystemSpringShonRassan.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    @Column(length = 40, nullable = false, updatable = false)
    private String name;
    @Column(length = 40, nullable = false)
    private String email;
    @Column(length = 40, nullable = false)
    private String password;
    @OneToMany(mappedBy = "company", cascade = CascadeType.PERSIST)
    @Singular
    private List<Coupon> coupons;

}