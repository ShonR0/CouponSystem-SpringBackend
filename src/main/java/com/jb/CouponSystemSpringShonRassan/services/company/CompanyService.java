package com.jb.CouponSystemSpringShonRassan.services.company;

import com.jb.CouponSystemSpringShonRassan.Exceptions.CouponSystemException;
import com.jb.CouponSystemSpringShonRassan.beans.Category;
import com.jb.CouponSystemSpringShonRassan.beans.Company;
import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    void addCoupon(Coupon coupon, int companyId) throws CouponSystemException;
    void updateCoupon(int couponId, Coupon coupon, Company company) throws CouponSystemException;
    void deleteCoupon(int coupon, Company company) throws CouponSystemException;
    List<Coupon> getAllCompanyCoupons(int companyId) throws CouponSystemException;
    List<Coupon> getAllCompanyCouponsByCategory(Company company, Category category) throws CouponSystemException;
    Coupon getSingleLoggedCompanyCoupon(int companyId, int couponId) throws CouponSystemException;
    List<Coupon> getAllCompanyCouponsByMaxPrice(Company company, double price) throws CouponSystemException;
    Company getLoggedCompany(int companyId) throws CouponSystemException;
}
