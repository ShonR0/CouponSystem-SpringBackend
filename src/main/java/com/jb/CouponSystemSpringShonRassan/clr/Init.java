package com.jb.CouponSystemSpringShonRassan.clr;

import com.jb.CouponSystemSpringShonRassan.beans.*;
import com.jb.CouponSystemSpringShonRassan.repos.CompanyRepository;
import com.jb.CouponSystemSpringShonRassan.repos.CouponRepository;
import com.jb.CouponSystemSpringShonRassan.repos.CustomerRepository;
import com.jb.CouponSystemSpringShonRassan.services.admin.AdminService;
import com.jb.CouponSystemSpringShonRassan.services.company.CompanyService;
import com.jb.CouponSystemSpringShonRassan.services.customer.CustomerService;
import com.jb.CouponSystemSpringShonRassan.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@Order(2)
public class Init implements CommandLineRunner {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    AdminService adminService;
    @Autowired
    CompanyService companyService;
    @Autowired
    CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n@@@@@@@@@@ Adding Coupons @@@@@@@@@@");
        Coupon co1 = Coupon.builder()
                .category(Category.FOOD)
                .title("1+1")
                .description("on Coca Cola drink")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(1)))
                .amount(5)
                .price(3.90)
                .image("img1")
                .build();
        Coupon co2 = Coupon.builder()
                .category(Category.FOOD)
                .title("free six pack")
                .description("on Coca Cola drink")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(1)))
                .amount(4)
                .price(4.90)
                .image("img2")
                .build();
        Coupon co3 = Coupon.builder()
                .category(Category.ELECTRICITY)
                .title("200$ Voucher")
                .description("on Electricity devices")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(1)))
                .amount(10)
                .price(199.90)
                .image("img3")
                .build();
        Coupon co4 = Coupon.builder()
                .category(Category.MUSIC)
                .title("10% on Guitar Strings")
                .description("10% off on all Elixir Strings")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(1)))
                .amount(3)
                .price(10.90)
                .image("img4")
                .build();
        couponRepository.findAll().forEach(System.out::println);

        System.out.println("@@@@@@@@@@ Adding Companies @@@@@@@@@@");
        Company c1 = Company.builder()
                .name("Coca-Cola")
                .email("cocacola@gmail.com")
                .password("1234")
                .coupons(List.of(co1,co2))
                .userType(UserType.COMPANY)
                .build();
        Company c2 = Company.builder()
                .name("KSP")
                .email("ksp@gmail.com")
                .password("1234")
                .coupons(List.of(co3))
                .userType(UserType.COMPANY)
                .build();
        Company c3 = Company.builder()
                .name("Kley-Zemer")
                .email("kelyzemer@gmail.com")
                .password("1234")
                .coupons(List.of(co4))
                .userType(UserType.COMPANY)
                .build();
        Company c4 = Company.builder()
                .name("toDelete")
                .email("SSS@SSS.com")
                .userType(UserType.COMPANY)
                .build();
        co1.setCompany(c1);
        co2.setCompany(c1);
        co3.setCompany(c2);
        co4.setCompany(c3);
//        co4.setCompany(c4);
        companyRepository.saveAll(List.of(c1,c2,c3));
        companyRepository.findAll().forEach(System.out::println);

        PrintUtils.title("adding Users");
        Customer admin = Customer.builder()
                .userType(UserType.ADMIN)
                .firstName("Admin")
                .lastName("Admin")
                .email("admin@admin.com")
                .password("1234")
                .build();
        Customer user1 = Customer.builder()
                .userType(UserType.CUSTOMER)
                .firstName("Adir")
                .lastName("first")
                .email("adir@gmail.com")
                .password("1234")
//                .coupons(List.of(co1, co2))
                .build();
        Customer user2 = Customer.builder()
                .userType(UserType.CUSTOMER)
                .firstName("Niv")
                .lastName("Second")
                .email("niv@gmail.com")
                .password("1234")
//                .coupons(List.of(co3,co4))
                .build();
//        co1.setCustomer(user1);
//        co2.setCustomer(user1);
//        co3.setCustomer(user2);
//        co4.setCustomer(user2);
        customerRepository.saveAll(List.of(admin, user1, user2));
//        customerService.purchaseCoupon(user1.getId(), co1.getId());
//        customerService.purchaseCoupon(user1.getId(), co2.getId());
//        System.out.println(customerRepository.findAll());

//        PrintUtils.title("get all logged company's coupons");
//        System.out.println(companyService.getSingleLoggedCompanyCoupon(2, 3));
//
//        PrintUtils.title("get logged company's coupons by category");
//        System.out.println(companyService.getAllCompanyCouponsByCategory(1, Category.FOOD));
//
//        PrintUtils.title("get logged customer's coupons");
//        System.out.println(customerService.getAllLoggedCustomerCoupons(1));
//        customerService.purchaseCoupon(1, 1);
//
//        PrintUtils.title("update coupon as company");
//        Company company = companyRepository.findById(1).orElseThrow();
//        co1.setAmount(1000);
//        companyService.updateCoupon(1, 1, co1);
//
//        System.out.println("GET ALL COUPONS");
//        adminService.getAllCoupons().forEach(System.out::println);
    }
}
