package com.jb.CouponSystemSpringShonRassan.clr;

import com.jb.CouponSystemSpringShonRassan.beans.Category;
import com.jb.CouponSystemSpringShonRassan.beans.Company;
import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import com.jb.CouponSystemSpringShonRassan.beans.Customer;
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
public class CouponSystemServiceTesting implements CommandLineRunner {
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
        PrintUtils.title("===== CouponSystemTesting =====");
        PrintUtils.title("±±± Admin ±±±");
        PrintUtils.title("Add 3 Companies");
        Company c1 = Company.builder()
                .name("company1")
                .email("company1@gmail.com")
                .password("1234")
                .build();
        Company c2 = Company.builder()
                .name("company2")
                .email("company2@gmail.com")
                .password("1234")
                .build();
        Company c3 = Company.builder()
                .name("company3")
                .email("company3@gmail.com")
                .password("1234")
                .build();
        adminService.addCompany(c1);
        adminService.addCompany(c2);
        adminService.addCompany(c3);
        adminService.getAllCompanies().forEach(System.out::println);

        PrintUtils.title("Add 6 Coupons");
        Coupon co1 = Coupon.builder()
                .category(Category.MUSIC)
                .title("musicCoupon1")
                .description("description...")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(1)))
                .amount(5)
                .price(3.99)
                .image("img1")
                .build();
        Coupon co2 = Coupon.builder()
                .category(Category.MUSIC)
                .title("musicCoupon2")
                .description("description...")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(1)))
                .amount(4)
                .price(4.99)
                .image("img2")
                .build();
        Coupon co3 = Coupon.builder()
                .category(Category.ELECTRICITY)
                .title("electricityCoupon1")
                .description("description...")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(1)))
                .amount(6)
                .price(8.99)
                .image("img3")
                .build();
        Coupon co4 = Coupon.builder()
                .category(Category.ELECTRICITY)
                .title("electricityCoupon2")
                .description("description...")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(1)))
                .amount(6)
                .price(4.99)
                .image("img4")
                .build();
        Coupon co5 = Coupon.builder()
                .category(Category.FOOD)
                .title("foodCoupon1")
                .description("description...")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(1)))
                .amount(2)
                .price(2.99)
                .image("img5")
                .build();
        Coupon co6 = Coupon.builder()
                .category(Category.FOOD)
                .title("foodCoupon2")
                .description("description...")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(1)))
                .amount(5)
                .price(7.99)
                .image("img6")
                .build();
        co1.setCompany(c1);
        co2.setCompany(c1);
        co3.setCompany(c2);
        co4.setCompany(c2);
        co5.setCompany(c3);
        co6.setCompany(c3);
        companyService.addCoupon(co1, c1.getId());
        companyService.addCoupon(co2, c1.getId());
        companyService.addCoupon(co3, c2.getId());
        companyService.addCoupon(co4, c2.getId());
        companyService.addCoupon(co5, c3.getId());
        companyService.addCoupon(co6, c3.getId());
        couponRepository.findAll().forEach(System.out::println);

        PrintUtils.title("Update Company");
        Company toUpdate = adminService.getSingleCompany(1);
        toUpdate.setEmail("cocacola@walla.com");
        adminService.updateCompany(1, toUpdate);
        System.out.println(adminService.getSingleCompany(1));

        PrintUtils.title("Delete Company");
        adminService.deleteCompany(6);
        companyRepository.findAll().forEach(System.out::println);

        PrintUtils.title("Add 3 Customers");
        Customer cu1 = Customer.builder()
                .firstName("firstName1")
                .lastName("lastName1")
                .email("aaaaa@gmail.com")
                .password("1234")
                .build();
        Customer cu2 = Customer.builder()
                .firstName("firstName2")
                .lastName("lastName2")
                .email("bbbbbb@gmail.com")
                .password("1234")
                .build();
        Customer cu3 = Customer.builder()
                .firstName("firstName3")
                .lastName("lastName3")
                .email("cccccc@gmail.com")
                .password("1234")
                .build();
        adminService.addCustomer(cu1);
        adminService.addCustomer(cu2);
        adminService.addCustomer(cu3);
        adminService.getAllCustomers().forEach(System.out::println);

        PrintUtils.title("Update Customer");
        Customer toUpdateCustomer = adminService.getSingleCustomer(1);
        toUpdateCustomer.setEmail("dddddd@gmail.com");
        adminService.updateCustomer(1, toUpdateCustomer);
        System.out.println(adminService.getSingleCustomer(1));

        PrintUtils.title("Delete Customer");
        adminService.deleteCustomer(3);
        adminService.getAllCustomers().forEach(System.out::println);

        PrintUtils.title("±±± Company ±±±");
        PrintUtils.title("Add Coupon");
        Company getComp = adminService.getSingleCompany(1);
        Coupon cocaColaCoupon = Coupon.builder()
                .category(Category.FOOD)
                .company(getComp)
                .title("Free Cola!!!")
                .description("Free!!!!")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(1)))
                .amount(6)
                .price(2.99)
                .image("img19")
                .build();
        companyService.addCoupon(cocaColaCoupon, c1.getId());
        Company getCompanyCoup = adminService.getSingleCompany(1);
//        System.out.println(companyService.getAllCompanyCoupons(getCompanyCoup.getId(), getCompanyCoup));
        System.out.println(companyService.getAllCompanyCoupons(getCompanyCoup.getId()));

        PrintUtils.title("Update Coupon");
        Coupon toUpdateCoupon = companyService.getSingleLoggedCompanyCoupon(getCompanyCoup.getId(), 1);
        Company cocaCola = adminService.getSingleCompany(1);
        toUpdateCoupon.setAmount(100);
        companyService.updateCoupon(1, toUpdateCoupon, cocaCola);
//        companyService.getAllCompanyCoupons(getCompanyCoup.getId(), getCompanyCoup).forEach(System.out::println);
        companyService.getAllCompanyCoupons(getCompanyCoup.getId()).forEach(System.out::println);

        PrintUtils.title("Delete Coupon");
        companyService.deleteCoupon(11, cocaCola);
//        companyService.getAllCompanyCoupons(getCompanyCoup.getId(), getCompanyCoup).forEach(System.out::println);
        companyService.getAllCompanyCoupons(getCompanyCoup.getId()).forEach(System.out::println);

        PrintUtils.title("Get all Company Coupons by Category");
        companyService.getAllCompanyCouponsByCategory(getCompanyCoup, Category.FOOD)
                .forEach(System.out::println);

        PrintUtils.title("Get Single Logged Company Coupon");
        System.out.println(companyService.getSingleLoggedCompanyCoupon(1, 1));

        PrintUtils.title("Get all Company Coupons by max Price");
        companyService.getAllCompanyCouponsByMaxPrice(cocaCola, 4).forEach(System.out::println);

        PrintUtils.title("Get logged company info");
        System.out.println(companyService.getLoggedCompany(1));

        PrintUtils.title("±±± Customer ±±±");
        PrintUtils.title("Purchase Coupon");
        Coupon purchCoupon1 = couponRepository.getReferenceById(6);
        Coupon purchCoupon2 = couponRepository.getReferenceById(7);
        customerService.purchaseCoupon(purchCoupon1, cu1);
        customerService.purchaseCoupon(purchCoupon2, cu1);

        PrintUtils.title("Get logged customer coupons");
        int id = cu1.getId();
        customerService.getAllLoggedCustomerCoupons(cu1).forEach(System.out::println);

        PrintUtils.title("Get logged customer coupons by category");
        customerService.getAllLoggedCustomerCouponsByCategory(cu1.getId(), Category.MUSIC)
                .forEach(System.out::println);

        PrintUtils.title("Get logged customer coupons by price");
        customerService.getAllLoggedCustomerCouponsByMaxPrice(cu1.getId(), 5)
                .forEach(System.out::println);

    }
}
