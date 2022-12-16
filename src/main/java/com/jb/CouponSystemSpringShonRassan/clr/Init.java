package com.jb.CouponSystemSpringShonRassan.clr;

import com.jb.CouponSystemSpringShonRassan.beans.Category;
import com.jb.CouponSystemSpringShonRassan.beans.Company;
import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import com.jb.CouponSystemSpringShonRassan.repos.CompanyRepository;
import com.jb.CouponSystemSpringShonRassan.repos.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@Order(1)
public class Init implements CommandLineRunner {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {

//        System.out.println("\n@@@@@@@@@@ Adding Customers @@@@@@@@@@");
//        Customer cu1 = Customer.builder()
//                .firstName("Shon")
//                .lastName("Rassan")
//                .email("shonr012@gmail.com")
//                .password("1234")
//                .build();
//        Customer cu2 = Customer.builder()
//                .firstName("Mika")
//                .lastName("Rassan")
//                .email("mikahen@gmail.com")
//                .password("1234")
//                .build();
//        Customer cu3 = Customer.builder()
//                .firstName("Liam")
//                .lastName("Rassan")
//                .email("shonr0123@gmail.com")
//                .password("1234")
//                .build();
//        Customer cu4 = Customer.builder()
//                .firstName("Shirley")
//                .lastName("Rassan")
//                .email("rassan@walla.com")
//                .password("1234")
//                .build();
//        Customer cu5 = Customer.builder()
//                .firstName("Aviram")
//                .lastName("Rassan")
//                .email("aviramR@gmail.com")
//                .password("1234")
//                .build();
//        customerService.addCustomer(cu1);
//        customerService.addCustomer(cu2);
//        customerService.addCustomer(cu3);
//        customerService.addCustomer(cu4);
//        customerService.addCustomer(cu5);
//        customerService.getAllCustomers().forEach(System.out::println);

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
                .build();
        Company c2 = Company.builder()
                .name("KSP")
                .email("ksp@gmail.com")
                .password("1234")
                .coupons(List.of(co3))
                .build();
        Company c3 = Company.builder()
                .name("Kley-Zemer")
                .email("kelyzemer@gmail.com")
                .password("1234")
                .coupons(List.of(co4))
                .build();
        co1.setCompany(c1);
        co2.setCompany(c1);
        co3.setCompany(c2);
        co4.setCompany(c3);
        companyRepository.saveAll(List.of(c1,c2,c3));
        companyRepository.findAll().forEach(System.out::println);
    }
}
