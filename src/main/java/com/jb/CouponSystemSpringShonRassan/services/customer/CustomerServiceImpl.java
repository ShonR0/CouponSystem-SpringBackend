package com.jb.CouponSystemSpringShonRassan.services.customer;

import com.jb.CouponSystemSpringShonRassan.Exceptions.CouponSystemException;
import com.jb.CouponSystemSpringShonRassan.Exceptions.ErrorMsg;
import com.jb.CouponSystemSpringShonRassan.Exceptions.SecMsg;
import com.jb.CouponSystemSpringShonRassan.Exceptions.SecuritySystemException;
import com.jb.CouponSystemSpringShonRassan.Security.Information;
import com.jb.CouponSystemSpringShonRassan.beans.Category;
import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import com.jb.CouponSystemSpringShonRassan.beans.Customer;
import com.jb.CouponSystemSpringShonRassan.beans.UserType;
import com.jb.CouponSystemSpringShonRassan.dto.CustomerReqDto;
import com.jb.CouponSystemSpringShonRassan.dto.LoginResDto;
import com.jb.CouponSystemSpringShonRassan.services.ClientService;
import com.jb.CouponSystemSpringShonRassan.services.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!this.customerRepository.existsByEmailAndPassword(email, password)) {
            throw new CouponSystemException(ErrorMsg.CUSTOMER_NOT_FOUND);
        }
        return true;
    }
//
    @Override
    public void purchaseCoupon(int customerId, int couponId) throws CouponSystemException {
        Coupon coupon = couponRepository.getReferenceById(couponId);
        Customer customer = customerRepository.getReferenceById(customerId);
        int amount = coupon.getAmount();
        Date endDate = coupon.getEndDate();
        if (customerRepository.existsByCoupons(coupon)) {
            throw new CouponSystemException(ErrorMsg.COUPON_ALREADY_PURCHASED);
        }
        if (amount == 0) {
            throw new CouponSystemException(ErrorMsg.COUPON_AMOUNT);
        }
        if (Objects.equals(endDate, Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException(ErrorMsg.COUPON_EXPIRED);
        }
        amount = amount - 1;
        coupon.setAmount(amount);
        couponRepository.purchaseCoupon(customer.getId(), coupon.getId());
    }


    @Override
    public List<Coupon> getAllLoggedCustomerCoupons(int customerId) throws CouponSystemException {
        if (!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrorMsg.ID_NOT_FOUND);
        }
        Customer customer = customerRepository.getReferenceById(customerId);
        List<Coupon> loggedCustomerCoupons = new ArrayList<>();
        loggedCustomerCoupons = customer.getCoupons();
        return loggedCustomerCoupons;
    }


    @Override
    public List<Coupon> getAllLoggedCustomerCouponsByCategory(int customerId, Category category) throws CouponSystemException {
        Customer getCoup = customerRepository.getReferenceById(customerId);
        if (!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrorMsg.ID_NOT_FOUND);
        }
        List<Coupon> loggedCustomerCoupons = new ArrayList<>();
        for (Coupon c : getCoup.getCoupons()) {
            if (c.getCategory() == category) {
                loggedCustomerCoupons.add(c);
            }
        }
        return loggedCustomerCoupons;
//      return customerRepository.findByCouponAndCategory(customerId, category);
    }

    @Override
    public List<Coupon> getAllLoggedCustomerCouponsByMaxPrice(int customerId, double price) throws CouponSystemException {
        Customer getCoup = customerRepository.getReferenceById(customerId);
        if (!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrorMsg.ID_NOT_FOUND);
        }
        if (price < 0) {
            throw new CouponSystemException(ErrorMsg.PRICE_NOT_VALID);
        }
        List<Coupon> loggedCustomerCoupons = new ArrayList<>();
        for (Coupon c : getCoup.getCoupons()) {
            if (c.getPrice() <= price) {
                loggedCustomerCoupons.add(c);
            }
        }
        return loggedCustomerCoupons;
//        return customerRepository.findByCouponAndPriceLessThan(customerId, price);
    }

//    @Override
//    public Customer getLoggedCustomer(int customerId) throws CouponSystemException {
//        return customerRepository.findById(customerId)
//                .orElseThrow(() -> new CouponSystemException(ErrorMsg.ID_NOT_FOUND));
//    }


    @Override
    public void addCustomer(String email, String password, String firstName, String lastName) throws SecuritySystemException {
        if (customerRepository.existsByEmail(email)) {
            throw new SecuritySystemException(SecMsg.EMAIL_ALREADY_EXIST);
        }
        Customer customer = Customer.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .userType(UserType.CUSTOMER)
                .build();
        customerRepository.save(customer);
    }

    //    @Override
//    public LoginResDto getCustomer(String email, String password) throws SecuritySystemException {
//        if (!customerRepository.existsByEmailAndPassword(email, password)){
//            throw new SecuritySystemException(SecMsg.INVALID_EMAIL_OR_PASSWORD);
//        }
//        if (Objects.equals(email, "admin@admin.com") && Objects.equals(password, "1234")) {
//            Customer customer = customerRepository.findTop1ByEmailAndPassword(email, password);
//            UUID token = tokenService.addCustomer(customer);
//            LoginResDto res = LoginResDto.builder().token(token).email(email).userType(UserType.ADMIN).build();
//            return res;
//        }
//        Customer customer = customerRepository.findTop1ByEmailAndPassword(email, password);
//        UUID token = tokenService.addCustomer(customer);
//        LoginResDto res = LoginResDto.builder().token(token).email(email).userType(UserType.CUSTOMER).build();
//        return res;
//    }
    @Override
    public LoginResDto getCustomer(String email, String password) throws SecuritySystemException {
        if (!customerRepository.existsByEmailAndPassword(email, password)) {
            throw new SecuritySystemException(SecMsg.INVALID_EMAIL_OR_PASSWORD);
        }
        Customer customer = customerRepository.findTop1ByEmailAndPassword(email, password);
        UUID token = tokenService.addCustomer(customer);
        LoginResDto res;
        if (Objects.equals(email, "admin@admin.com") && Objects.equals(password, "1234")) {
            res = LoginResDto.builder().token(token).email(email).userType(UserType.ADMIN).build();
        } else {
            res = LoginResDto.builder().token(token).email(email).userType(UserType.CUSTOMER).build();
        }
        return res;
    }


}