package com.jb.CouponSystemSpringShonRassan.services.coupon;

import com.jb.CouponSystemSpringShonRassan.beans.Coupon;

import java.util.List;

public interface CouponService {
    int count();
    List<Coupon> getAllCustomerCoupons(int customerId);
    List<Coupon> getAllCoupons();
}
