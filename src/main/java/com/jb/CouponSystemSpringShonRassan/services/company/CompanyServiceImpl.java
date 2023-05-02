package com.jb.CouponSystemSpringShonRassan.services.company;

import com.jb.CouponSystemSpringShonRassan.Exceptions.CouponSystemException;
import com.jb.CouponSystemSpringShonRassan.Exceptions.ErrorMsg;
import com.jb.CouponSystemSpringShonRassan.Exceptions.SecMsg;
import com.jb.CouponSystemSpringShonRassan.Exceptions.SecuritySystemException;
import com.jb.CouponSystemSpringShonRassan.beans.Category;
import com.jb.CouponSystemSpringShonRassan.beans.Company;
import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import com.jb.CouponSystemSpringShonRassan.beans.UserType;
import com.jb.CouponSystemSpringShonRassan.dto.LoginResDto;
import com.jb.CouponSystemSpringShonRassan.services.ClientService;
import com.jb.CouponSystemSpringShonRassan.services.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {

    @Autowired
    private TokenService tokenService;

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
    public void updateCoupon(int couponId, int companyId, Coupon coupon) throws CouponSystemException {
        Company comp = companyRepository.getReferenceById(companyId);
        if (!this.couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrorMsg.ID_NOT_FOUND);
        }
        if (coupon.getCompany() == comp) {
            throw new CouponSystemException(ErrorMsg.NOT_COMPANY_COUPON_);
        }
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int couponId, int companyId) throws CouponSystemException {
        Coupon toDelete = couponRepository.getReferenceById(couponId);
        Company company = companyRepository.getReferenceById(companyId);
        if (!this.couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrorMsg.ID_NOT_FOUND);
        }
        if (toDelete.getCompany() != company) {
            throw new CouponSystemException(ErrorMsg.NOT_COMPANY_COUPON_DELETE);
        }
        couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category) throws CouponSystemException {
        Company company = companyRepository.getReferenceById(companyId);
        if (!this.companyRepository.existsById(companyId)) {
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
//        Coupon getCoup = couponRepository.getReferenceById(couponId);
        Company getComp = companyRepository.getReferenceById(companyId);
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
    public List<Coupon> getAllCompanyCouponsByMaxPrice(int companyId, double price) throws CouponSystemException {
        Company company = companyRepository.getReferenceById(companyId);
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

    @Override
    public void addCompany(String name, String email, String password) throws SecuritySystemException {
        if (companyRepository.existsByEmail(email)) {
            throw new SecuritySystemException(SecMsg.EMAIL_ALREADY_EXIST);
        }
        if (companyRepository.existsByName(name)) {
            throw new SecuritySystemException(SecMsg.COMPANY_NANE_ALREADY_EXIST);
        }
        Company company = Company.builder()
                .name(name)
                .email(email)
                .password(password)
                .userType(UserType.COMPANY)
                .build();
        System.out.println(company);
        companyRepository.save(company);
    }

    @Override
    public LoginResDto getCompany(String email, String password) throws SecuritySystemException {
        if (!companyRepository.existsByEmailAndPassword(email, password)) {
            throw new SecuritySystemException(SecMsg.INVALID_EMAIL_OR_PASSWORD);
        }
        Company company = companyRepository.findTop1ByEmailAndPassword(email, password);
        UUID token = tokenService.addCompany(company);
        LoginResDto res = LoginResDto.builder().token(token).email(email).userType(UserType.COMPANY).build();
        return res;
    }


}
