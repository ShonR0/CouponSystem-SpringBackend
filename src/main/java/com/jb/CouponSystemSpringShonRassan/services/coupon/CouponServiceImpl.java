package com.jb.CouponSystemSpringShonRassan.services.coupon;

import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import com.jb.CouponSystemSpringShonRassan.repos.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService{
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public int count() {
        return (int) couponRepository.count();
    }

    @Override
    public List<Coupon> getAllCustomerCoupons(int customerId) {
//        return couponRepository.findByCustomerId(customerId);
        return null;
    }
    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }
}
