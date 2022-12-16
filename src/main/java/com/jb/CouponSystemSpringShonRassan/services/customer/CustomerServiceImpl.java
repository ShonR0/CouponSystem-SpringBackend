package com.jb.CouponSystemSpringShonRassan.services.customer;

import com.jb.CouponSystemSpringShonRassan.Exceptions.CouponSystemException;
import com.jb.CouponSystemSpringShonRassan.Exceptions.ErrorMsg;
import com.jb.CouponSystemSpringShonRassan.beans.Category;
import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import com.jb.CouponSystemSpringShonRassan.beans.Customer;
import com.jb.CouponSystemSpringShonRassan.services.ClientService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {
    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!this.customerRepository.existsByEmailAndPassword(email, password)) {
            throw new CouponSystemException(ErrorMsg.CUSTOMER_NOT_FOUND);
        }
        return true;
    }

    @Override
    public void purchaseCoupon(Coupon coupon, Customer customer) throws CouponSystemException {
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
//        customer.setCoupons(List.of(coupon));
//        couponRepository.saveAndFlush(coupon);
//        customerRepository.saveAndFlush(customer);
        couponRepository.purchaseCoupon(customer.getId(), coupon.getId());
    }


    @Override
    public List<Coupon> getAllLoggedCustomerCoupons(Customer customer) throws CouponSystemException {
        int id = customer.getId();
//        if (!this.customerRepository.existsById(id)) {
//            throw new CouponSystemException(ErrorMsg.ID_NOT_FOUND);
//        }
        Customer getCoup = customerRepository.getReferenceById(id);
        List<Coupon> loggedCustomerCoupons = new ArrayList<>();
        loggedCustomerCoupons = getCoup.getCoupons();
//        return customerRepository.findByCoupons(customer);
        return loggedCustomerCoupons;
    }


    @Override
    public List<Coupon> getAllLoggedCustomerCouponsByCategory(int customerId, Category category) throws CouponSystemException {
        Customer getCoup = customerRepository.getReferenceById(customerId);
        if (!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrorMsg.ID_NOT_FOUND);
        }
        List<Coupon> loggedCustomerCoupons = new ArrayList<>();
        for (Coupon c : getCoup.getCoupons()){
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
        if (price < 0){
            throw new CouponSystemException(ErrorMsg.PRICE_NOT_VALID);
        }
        List<Coupon> loggedCustomerCoupons = new ArrayList<>();
        for (Coupon c : getCoup.getCoupons()){
            if (c.getPrice() <= price) {
                loggedCustomerCoupons.add(c);
            }
        }
        return loggedCustomerCoupons;
//        return customerRepository.findByCouponAndPriceLessThan(customerId, price);
    }

    @Override
    public Customer getLoggedCustomer(int customerId) throws CouponSystemException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.ID_NOT_FOUND));
    }
}