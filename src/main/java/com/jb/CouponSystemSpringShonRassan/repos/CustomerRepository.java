package com.jb.CouponSystemSpringShonRassan.repos;

import com.jb.CouponSystemSpringShonRassan.beans.Category;
import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import com.jb.CouponSystemSpringShonRassan.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByCoupons(Coupon coupon);
    boolean existsByEmailAndPassword(String email, String password);
    //    @Query(value = "select coupon_id from customers_coupons where exists (select customer_id from customers_coupons where customer_id = ?", nativeQuery = true)
//    @Query(value = "select * from customers_coupons where customer_id = ?", nativeQuery = true)

    List<Coupon> findByCoupons(Customer customer);
    @Query(value = "select * from CouponSystem.customers_coupons where customer_id = ? and category = ?", nativeQuery = true)
    List<Coupon> findByCouponAndCategory(int customerId, Category category);
    @Query(value = "select * from CouponSystem.customers_coupons where customer_id = ? and price <= ?", nativeQuery = true)
    List<Coupon> findByCouponAndPriceLessThan(int customerId, double price);
}