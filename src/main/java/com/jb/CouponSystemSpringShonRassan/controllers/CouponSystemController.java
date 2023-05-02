package com.jb.CouponSystemSpringShonRassan.controllers;

import com.jb.CouponSystemSpringShonRassan.Exceptions.CouponSystemException;
import com.jb.CouponSystemSpringShonRassan.Exceptions.SecMsg;
import com.jb.CouponSystemSpringShonRassan.Exceptions.SecuritySystemException;
import com.jb.CouponSystemSpringShonRassan.beans.*;
import com.jb.CouponSystemSpringShonRassan.dto.CustomerReqDto;
import com.jb.CouponSystemSpringShonRassan.dto.LoginReqDto;
import com.jb.CouponSystemSpringShonRassan.dto.LoginResDto;
import com.jb.CouponSystemSpringShonRassan.dto.company.CompanyReqDto;
import com.jb.CouponSystemSpringShonRassan.repos.CompanyRepository;
import com.jb.CouponSystemSpringShonRassan.repos.CouponRepository;
import com.jb.CouponSystemSpringShonRassan.repos.CustomerRepository;
import com.jb.CouponSystemSpringShonRassan.services.admin.AdminService;
import com.jb.CouponSystemSpringShonRassan.services.company.CompanyService;
import com.jb.CouponSystemSpringShonRassan.services.coupon.CouponService;
import com.jb.CouponSystemSpringShonRassan.services.customer.CustomerService;
import com.jb.CouponSystemSpringShonRassan.services.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/CouponSystem")
public class CouponSystemController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private TokenService tokenService;
    // ============ LOGIN CONTROLLER ============
    @PostMapping("auth/login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto customerLogin(@RequestBody LoginReqDto req) throws SecuritySystemException {
        String email = req.getEmail();
        String password = req.getPassword();
        if (customerRepository.existsByEmailAndPassword(email, password)) {
            System.out.println("logged as customer");
            return customerService.getCustomer(email, password);
        } else
            System.out.println("logged as company");
            return companyService.getCompany(email, password);
    }

    // ============ ADMIN CONTROLLER ============
    @GetMapping("admin/customers")
    public List<Customer> getAllCustomers(@RequestHeader ("Authorization")UUID token) throws SecuritySystemException {
        if (!tokenService.isValid(token, UserType.ADMIN)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        return adminService.getAllCustomers();
    }

    @GetMapping("admin/coupons/count")
    public int howManyTasks(@RequestHeader ("Authorization")UUID token) throws SecuritySystemException {
        if (!tokenService.isValid(token, UserType.ADMIN)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        return couponService.count();
    }

//    @PostMapping("companies/coupons")
    @PostMapping("coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public Coupon addCoupon(@RequestHeader ("Authorization")UUID token,
                          @RequestBody Coupon coupon) throws CouponSystemException, SecuritySystemException {
        if (!tokenService.isValid(token, UserType.COMPANY)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int companyId = tokenService.getCustomerId(token);
        System.out.println(coupon);
        companyService.addCoupon(coupon, companyId);
        return coupon;
        // Working
    }

    @GetMapping("customers/coupons")
    public List<Coupon> getAllCustomerCoupons(@RequestHeader("Authorization")UUID token) throws SecuritySystemException {
        if (!tokenService.isValid(token, UserType.CUSTOMER)) {
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int customerId = tokenService.getCustomerId(token);
        return couponService.getAllCustomerCoupons(customerId);
    }

    @GetMapping("coupons")
    public List<Coupon> getAllCoupons() throws SecuritySystemException {
//        if (!tokenService.isValid(token, UserType.CUSTOMER)) {
//            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
//        }
//        if (!tokenService.isValid(token, UserType.COMPANY)) {
//            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
//        }
//        int customerId = tokenService.getCustomerId(token);
        return couponService.getAllCoupons();
    }

    @PostMapping("admin/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) throws CouponSystemException {
        adminService.addCompany(company);
        // Working
    }

    @PutMapping("admin/companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@PathVariable int companyId, @RequestBody Company company) throws CouponSystemException {
        adminService.updateCompany(companyId, company);
        // Working
    }

    @DeleteMapping("admin/companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@RequestHeader("Authorization")UUID token, @PathVariable int companyId) throws CouponSystemException, SecuritySystemException {
        if (!tokenService.isValid(token, UserType.ADMIN)) {
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        adminService.deleteCompany(companyId);
        // Working
    }
    @DeleteMapping("admin/customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@RequestHeader("Authorization")UUID token, @PathVariable int customerId) throws CouponSystemException, SecuritySystemException {
        if (!tokenService.isValid(token, UserType.ADMIN)) {
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        adminService.deleteCustomer(customerId);
        // Working
    }

    @GetMapping("admin/companies")
    public List<Company> getAllCompanies() {
        return adminService.getAllCompanies();
        // Working
    }

    @GetMapping("admin/companies/{companyId}")
    public Company getSingleCompany(@PathVariable int companyId) throws CouponSystemException {
        return adminService.getSingleCompany(companyId);
        // Working
    }

    // =========================
    @PostMapping("admin/addCustomers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) throws CouponSystemException {
        adminService.addCustomer(customer);
    }

    @PutMapping("admin/customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) throws CouponSystemException {
        adminService.updateCustomer(customerId, customer);
    }

    @GetMapping("admin/customers/{customerId}")
    public Customer getSingleCustomer(@PathVariable int customerId) throws CouponSystemException {
        return adminService.getSingleCustomer(customerId);
    }

    // ============ COMPANY CONTROLLER ============
    @PostMapping("auth/company/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void companyRegister(@RequestBody CompanyReqDto req) throws SecuritySystemException {
        String name = req.getName();
        String email = req.getEmail();
        String password = req.getPassword();
        companyService.addCompany(name, email, password);
    }

    @PostMapping("auth/company/login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto companyLogin(@RequestBody LoginReqDto req) throws SecuritySystemException {
        String email = req.getEmail();
        String password = req.getPassword();
        return companyService.getCompany(email, password);
    }

//    @PostMapping("companies/{companyId}/coupons")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addCoupon(@PathVariable int companyId, @RequestBody Coupon coupon) throws CouponSystemException {
//        companyService.addCoupon(coupon, companyId);
//        // Working
//    }

    @PutMapping("coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Coupon updateCoupon(@RequestHeader ("Authorization")UUID token, @PathVariable int couponId, @RequestBody Coupon coupon) throws CouponSystemException, SecuritySystemException {
        if (!tokenService.isValid(token, UserType.COMPANY)) {
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int companyId = tokenService.getCustomerId(token);
        companyService.updateCoupon(couponId, companyId, coupon);
        return couponRepository.getReferenceById(couponId);
        // Working
    }

    @DeleteMapping("companies/coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@RequestHeader ("Authorization")UUID token,@PathVariable int couponId) throws CouponSystemException, SecuritySystemException {
        if (!tokenService.isValid(token, UserType.COMPANY)) {
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int companyId = tokenService.getCustomerId(token);
        companyService.deleteCoupon(couponId, companyId);
        // Working
    }

    @GetMapping("companies/{companyId}/coupons/category")
    public List<Coupon> getAllCompanyCouponsByCategory(@PathVariable int companyId, @RequestParam String category) throws CouponSystemException {
        return companyService.getAllCompanyCouponsByCategory(companyId, Category.valueOf(category));
        // Working
    }

    @GetMapping("companies/coupons")
    public List<Coupon> getAllCompanyCoupons(@RequestHeader ("Authorization") UUID token) throws CouponSystemException, SecuritySystemException {
        int companyId = tokenService.getCustomerId(token);
        return companyService.getAllCompanyCoupons(companyId);
        // Working
    }

    @GetMapping("companies/{companyId}/coupons/{couponId}")
    public Coupon getSingleLoggedCompanyCoupon(@PathVariable int companyId, @PathVariable int couponId) throws CouponSystemException {
        return companyService.getSingleLoggedCompanyCoupon(companyId, couponId);
        // Working
    }

    @GetMapping("companies/{companyId}/coupons/price")
    public List<Coupon> getAllCompanyCouponsByMaxPrice(@PathVariable int companyId, @RequestParam double price) throws CouponSystemException {
        return companyService.getAllCompanyCouponsByMaxPrice(companyId, price);
        // Working
    }

    @GetMapping("companies/{companyId}")
    public Company getLoggedCompany(@PathVariable int companyId) throws CouponSystemException {
        return companyService.getLoggedCompany(companyId);
    }

    // ============ CUSTOMER CONTROLLER ============

    @PostMapping("auth/customer/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void customerRegister(@RequestBody CustomerReqDto req) throws SecuritySystemException {
        String email = req.getEmail();
        String password = req.getPassword();
        String firstName = req.getFirstName();
        String lastName = req.getLastName();
        customerService.addCustomer(email, password, firstName, lastName);
    }

    @PutMapping("customers/coupons/{couponId}")
    public void purchaseCoupon(@RequestHeader ("Authorization")UUID token, @PathVariable int couponId) throws CouponSystemException, SecuritySystemException {
        if (!tokenService.isValid(token, UserType.CUSTOMER)) {
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int customerId = tokenService.getCustomerId(token);
        customerService.purchaseCoupon(customerId, couponId);
        // Working
    }

    @GetMapping("customers/purchasedCoupons")
    public List<Coupon> getAllLoggedCustomerCoupons(@RequestHeader ("Authorization")UUID token) throws CouponSystemException, SecuritySystemException {
        if (!tokenService.isValid(token, UserType.CUSTOMER)) {
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int customerId = tokenService.getCustomerId(token);
        return customerService.getAllLoggedCustomerCoupons(customerId);
        // Working
    }

    @GetMapping("customers_coupons/{customerId}/category")
    public List<Coupon> getLoggedCustomerCouponsByCategory(@PathVariable int customerId, @RequestParam String category) throws CouponSystemException {
        return customerService.getAllLoggedCustomerCouponsByCategory(customerId, Category.valueOf(category));
        // Working
    }

    @GetMapping("customers_coupons/{customerId}/coupons/price")
    public List<Coupon> getLoggedCustomerCouponsByMaxPrice(@PathVariable int customerId, @RequestParam double price) throws CouponSystemException {
        return customerService.getAllLoggedCustomerCouponsByMaxPrice(customerId, price);
        // Working
    }

    @GetMapping("customer/{customerId}")
    public Customer getLoggedCustomer(@PathVariable int customerId) throws CouponSystemException {
//        return customerService.getLoggedCustomer(customerId);
        return null;
    }

//    @PostMapping("register")
//    public void register(Customer customer) throws CouponSystemException {
//        adminService.addCustomer(customer);
//    }

}
