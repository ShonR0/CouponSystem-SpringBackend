package com.jb.CouponSystemSpringShonRassan.services.customer;

import com.jb.CouponSystemSpringShonRassan.Exceptions.CouponSystemException;
import com.jb.CouponSystemSpringShonRassan.beans.Category;
import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import com.jb.CouponSystemSpringShonRassan.beans.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    void purchaseCoupon(Coupon coupon, Customer customer) throws CouponSystemException;

    List<Coupon> getAllLoggedCustomerCoupons(Customer customer) throws CouponSystemException;

    List<Coupon> getAllLoggedCustomerCouponsByCategory(int customerId, Category category) throws CouponSystemException;

    List<Coupon> getAllLoggedCustomerCouponsByMaxPrice(int customerId, double price) throws CouponSystemException;

    Customer getLoggedCustomer(int customerId) throws CouponSystemException;
}