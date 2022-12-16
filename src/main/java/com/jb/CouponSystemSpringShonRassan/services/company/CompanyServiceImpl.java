package com.jb.CouponSystemSpringShonRassan.services.company;

import com.jb.CouponSystemSpringShonRassan.Exceptions.CouponSystemException;
import com.jb.CouponSystemSpringShonRassan.Exceptions.ErrorMsg;
import com.jb.CouponSystemSpringShonRassan.beans.Category;
import com.jb.CouponSystemSpringShonRassan.beans.Company;
import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import com.jb.CouponSystemSpringShonRassan.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!this.companyRepository.existsByEmailAndPassword(email, password)) {
            throw new CouponSystemException(ErrorMsg.COMPANY_NOT_FOUND);
        }
        return true;
    }

    @Override
    public void addCoupon(Coupon coupon, int companyId) throws CouponSystemException {
        String couponName = coupon.getTitle();
        coupon.setCompany(companyRepository.getReferenceById(companyId));
        if (this.couponRepository.existsByTitleAndCompany(couponName, coupon.getCompany())) {
            throw new CouponSystemException(ErrorMsg.COUPON_NAME_EXISTS);
        }
        couponRepository.save(coupon);
    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon, Company company) throws CouponSystemException {
        Coupon toUpdate = couponRepository.getReferenceById(couponId);
        if (!this.couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrorMsg.ID_NOT_FOUND);
        }
        if (toUpdate.getCompany() == company) {
            throw new CouponSystemException(ErrorMsg.NOT_COMPANY_COUPON_);
        }
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int couponId, Company company) throws CouponSystemException {
        Coupon toDelete = couponRepository.getReferenceById(couponId);
        if (!this.couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrorMsg.ID_NOT_FOUND);
        }
        if (toDelete.getCompany() == company) {
            throw new CouponSystemException(ErrorMsg.NOT_COMPANY_COUPON_DELETE);
        }
        couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByCategory(Company company, Category category) throws CouponSystemException {
        int compId = company.getId();
        if (!this.companyRepository.existsById(compId)) {
            throw new CouponSystemException(ErrorMsg.COMPANY_NOT_EXISTS);
        }
        return couponRepository.findByCompanyAndCategory(company, category);
    }

    @Override
    public List<Coupon> getAllCompanyCoupons(int companyId) throws CouponSystemException {
//      int compId = company.getId();
        Company getComp = companyRepository.getReferenceById(companyId);
        if (!this.companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrorMsg.COMPANY_NOT_EXISTS);
        }
        return couponRepository.findByCompany(getComp);
    }

    @Override
    public Coupon getSingleLoggedCompanyCoupon(int companyId , int couponId) throws CouponSystemException {
        Coupon getCoup = couponRepository.getReferenceById(couponId);
        Company getComp = companyRepository.getReferenceById(couponId);
        if (!this.couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrorMsg.ID_NOT_FOUND);
        }
        if (!this.companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrorMsg.COMPANY_NOT_EXISTS);
        }
        if (!this.couponRepository.existsByCompany(getComp)){
            throw new CouponSystemException(ErrorMsg.COUPON_NOT_BELONG);
        }
        return couponRepository.findByCompanyAndId(getComp, couponId);
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByMaxPrice(Company company, double price) throws CouponSystemException {
        if (price < 0) {
            throw new CouponSystemException(ErrorMsg.PRICE_NOT_VALID);
        }
        return couponRepository.findByCompanyAndPriceLessThan(company, price);
    }


    @Override
    public Company getLoggedCompany(int companyId) throws CouponSystemException {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.ID_NOT_FOUND));
    }
}
