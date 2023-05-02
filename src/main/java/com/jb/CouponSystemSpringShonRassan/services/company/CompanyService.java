package com.jb.CouponSystemSpringShonRassan.services.company;

import com.jb.CouponSystemSpringShonRassan.Exceptions.CouponSystemException;
import com.jb.CouponSystemSpringShonRassan.Exceptions.SecuritySystemException;
import com.jb.CouponSystemSpringShonRassan.beans.*;
import com.jb.CouponSystemSpringShonRassan.dto.LoginResDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    void addCoupon(Coupon coupon, int companyId) throws CouponSystemException;
    void updateCoupon(int couponId, int companyId, Coupon coupon) throws CouponSystemException;
    void deleteCoupon(int coupon, int companyId) throws CouponSystemException;
    List<Coupon> getAllCompanyCoupons(int companyId) throws CouponSystemException;
    List<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category) throws CouponSystemException;
    Coupon getSingleLoggedCompanyCoupon(int companyId, int couponId) throws CouponSystemException;
    List<Coupon> getAllCompanyCouponsByMaxPrice(int companyId, double price) throws CouponSystemException;
    Company getLoggedCompany(int companyId) throws CouponSystemException;
    void addCompany(String email, String password, String name) throws SecuritySystemException;
    LoginResDto getCompany (String email, String password) throws SecuritySystemException;


}
