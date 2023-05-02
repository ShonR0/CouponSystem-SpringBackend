package com.jb.CouponSystemSpringShonRassan.services.customer;

import com.jb.CouponSystemSpringShonRassan.Exceptions.CouponSystemException;
import com.jb.CouponSystemSpringShonRassan.Exceptions.SecuritySystemException;
import com.jb.CouponSystemSpringShonRassan.beans.Category;
import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import com.jb.CouponSystemSpringShonRassan.beans.Customer;
import com.jb.CouponSystemSpringShonRassan.beans.UserType;
import com.jb.CouponSystemSpringShonRassan.dto.CustomerReqDto;
import com.jb.CouponSystemSpringShonRassan.dto.LoginResDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    void purchaseCoupon(int customerId, int couponId) throws CouponSystemException;

    List<Coupon> getAllLoggedCustomerCoupons(int customerId) throws CouponSystemException;

    List<Coupon> getAllLoggedCustomerCouponsByCategory(int customerId, Category category) throws CouponSystemException;

    List<Coupon> getAllLoggedCustomerCouponsByMaxPrice(int customerId, double price) throws CouponSystemException;

//    Customer getLoggedCustomer(int customerId) throws CouponSystemException;

    //================================
    void addCustomer(String email, String password, String firstName, String lastName) throws SecuritySystemException;
    LoginResDto getCustomer (String email, String password) throws SecuritySystemException;

}