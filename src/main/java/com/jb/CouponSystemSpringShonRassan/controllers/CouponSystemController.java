package com.jb.CouponSystemSpringShonRassan.controllers;

import com.jb.CouponSystemSpringShonRassan.Exceptions.CouponSystemException;
import com.jb.CouponSystemSpringShonRassan.beans.Category;
import com.jb.CouponSystemSpringShonRassan.beans.Company;
import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import com.jb.CouponSystemSpringShonRassan.beans.Customer;
import com.jb.CouponSystemSpringShonRassan.services.admin.AdminService;
import com.jb.CouponSystemSpringShonRassan.services.company.CompanyService;
import com.jb.CouponSystemSpringShonRassan.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/CouponSystem")
public class CouponSystemController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;

    // ============ ADMIN CONTROLLER ============
    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) throws CouponSystemException {
        adminService.addCompany(company);
    }

    @PutMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@PathVariable int companyId, @RequestBody Company company) throws CouponSystemException {
        adminService.updateCompany(companyId, company);
    }

    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int companyId) throws CouponSystemException {
        adminService.deleteCompany(companyId);
    }

    @GetMapping("companies")
    public List<Company> getAllCompanies() {
        return adminService.getAllCompanies();
    }

    @GetMapping("companies/{companyId}")
    public Company getSingleCompany(@PathVariable int companyId) throws CouponSystemException {
        return adminService.getSingleCompany(companyId);
    }
    // =========================
    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) throws CouponSystemException {
        adminService.addCustomer(customer);
    }

    @PutMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) throws CouponSystemException {
        adminService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int customerId) throws CouponSystemException {
        adminService.deleteCustomer(customerId);
    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    @GetMapping("customers/{customerId}")
    public Customer getSingleCustomer(@PathVariable int customerId) throws CouponSystemException {
        return adminService.getSingleCustomer(customerId);
    }

    // ============ COMPANY CONTROLLER ============
    public void companyLogin(String email, String password) {
        // TODO: 16/12/2022
    }

    @PostMapping("companies/{companyId}/coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@PathVariable int companyId, @RequestBody Coupon coupon) throws CouponSystemException {
        companyService.addCoupon(coupon, companyId);
    }

    @PutMapping("companies/{companyId}/coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCoupon(@PathVariable int couponId, @RequestBody Coupon coupon ,@RequestBody Company company) throws CouponSystemException {
        companyService.updateCoupon(couponId, coupon, company);
        // TODO: 17/12/2022  
    }

    @DeleteMapping("companies/{companyId}/coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@PathVariable int couponId, @RequestBody Company company) throws CouponSystemException {
        companyService.deleteCoupon(couponId, company);
    }

    @GetMapping("companies/{companyId}/coupons/{couponId}/category")
    public void getAllCompanyCouponsByCategory(@RequestBody Company company, @RequestBody Category category) throws CouponSystemException {
        companyService.getAllCompanyCouponsByCategory(company, category);
    }

    @GetMapping("companies/{companyId}/coupons")
    public List<Coupon> getAllCompanyCoupons(@PathVariable int companyId) throws CouponSystemException {
        return companyService.getAllCompanyCoupons(companyId);
    }

    @GetMapping("companies/{companyId}/coupons/{couponId}")
    public Coupon getSingleLoggedCompanyCoupon(@PathVariable int companyId, @PathVariable int couponId) throws CouponSystemException {
        return companyService.getSingleLoggedCompanyCoupon(companyId, couponId);
        // TODO: 17/12/2022
    }

    @GetMapping("companies/{companyId}/coupons/price")
    public List<Coupon> getAllCompanyCouponsByMaxPrice(@RequestBody Company company, @PathVariable double price) throws CouponSystemException {
        return companyService.getAllCompanyCouponsByMaxPrice(company, price);
    }

//    @GetMapping("companies/{companyId}")
//    public Company getLoggedCompany(@PathVariable int companyId) throws CouponSystemException {
//        return companyService.getLoggedCompany(companyId);
//    }

    // ============ CUSTOMER CONTROLLER ============
    public void customerLogin(String email, String password) {
        // TODO: 16/12/2022
    }

    @PostMapping("customers/{customerId}/purchasedCoupons")
    public void purchaseCoupon(@RequestBody Coupon coupon, @RequestBody Customer customer) throws CouponSystemException {
        customerService.purchaseCoupon(coupon, customer);
    }

//    @GetMapping("companies/{companyId}")
//    public Customer getLoggedCustomer(@PathVariable int customerId) throws CouponSystemException {
//        return customerService.getLoggedCustomer(customerId);
//    }
}
