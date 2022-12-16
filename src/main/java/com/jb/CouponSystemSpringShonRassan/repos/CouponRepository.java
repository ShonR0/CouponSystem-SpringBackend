package com.jb.CouponSystemSpringShonRassan.repos;


import com.jb.CouponSystemSpringShonRassan.beans.Category;
import com.jb.CouponSystemSpringShonRassan.beans.Company;
import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import com.jb.CouponSystemSpringShonRassan.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    @Modifying
    @Transactional
    @Query(value =  "INSERT INTO customers_coupons (customer_id, coupons_id) VALUES (?, ?)" ,nativeQuery = true)
    void purchaseCoupon(int customerId, int couponId);
    Coupon findByEndDate(Date date);
    //findByCustomer!!!!!
//    List<Coupon> findByCustomer(int customerId);
    boolean existsByTitleAndCompany(String name, Company company);
    boolean existsByCompany(Company company);
    List<Coupon> findByCompany(Company company);
    Coupon findByCompanyAndId(Company company, int couponId);
    List<Coupon> findByCompanyAndCategory(Company company, Category category);
    List<Coupon> findByCompanyAndPriceLessThan(Company company, double price);

}