package com.jb.CouponSystemSpringShonRassan.job;

import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import com.jb.CouponSystemSpringShonRassan.repos.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
public class DeleteExpired {
    @Autowired
    private CouponRepository couponRepository;

    @Scheduled(fixedRate = 1000*60*60*24)
    public void deleteExpiredCoupons() {
        List<Coupon> expiredCoupons = couponRepository.findByEndDate(Date.valueOf(LocalDate.now()));
        couponRepository.deleteAll(expiredCoupons);
        if (expiredCoupons.isEmpty()) {
            System.out.println("not expired coupons found");
        } else {
            couponRepository.deleteAll(expiredCoupons);
            System.out.println("this coupons has been expired: " + expiredCoupons);
        }
    }
}
