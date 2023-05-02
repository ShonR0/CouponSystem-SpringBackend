package com.jb.CouponSystemSpringShonRassan.beans;


import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    @Column(length = 40)
    private String firstName;
    @Column(length = 40)
    private String lastName;
    @Column(length = 40)
    private String email;
    @Column(length = 40)
    private String password;
//    @ManyToMany
//    @OneToMany(mappedBy="customer", cascade = CascadeType.PERSIST)
    @ManyToMany
    @Singular
    private List<Coupon> coupons;
    private UserType userType;

}