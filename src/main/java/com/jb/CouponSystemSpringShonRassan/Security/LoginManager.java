package com.jb.CouponSystemSpringShonRassan.Security;

import com.jb.CouponSystemSpringShonRassan.Exceptions.CouponSystemException;
import com.jb.CouponSystemSpringShonRassan.Exceptions.ErrorMsg;
import com.jb.CouponSystemSpringShonRassan.repos.CompanyRepository;
import com.jb.CouponSystemSpringShonRassan.repos.CouponRepository;
import com.jb.CouponSystemSpringShonRassan.repos.CustomerRepository;
import com.jb.CouponSystemSpringShonRassan.services.ClientService;
import com.jb.CouponSystemSpringShonRassan.services.admin.AdminService;
import com.jb.CouponSystemSpringShonRassan.services.company.CompanyService;
import com.jb.CouponSystemSpringShonRassan.services.company.CompanyServiceImpl;
import com.jb.CouponSystemSpringShonRassan.services.customer.CustomerService;
import com.jb.CouponSystemSpringShonRassan.services.customer.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginManager {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private AdminService adminService;

    public void login(String email, String password) throws CouponSystemException {
        if (Objects.equals(email, "ADMIN") && Objects.equals(password, "1234")) {
            getAdmin();
        }
//        if (customerRepository.existsByEmailAndPassword(email, password)) {
//            getCustomer();
//        }
        if (companyRepository.existsByEmailAndPassword(email, password)) {
            getCompany();
        }
    }

    public AdminService getAdmin() {
        return adminService;
    }

    public CustomerService getCustomer() {
        return customerService;
    }

    public CompanyService getCompany() {
        return companyService;
    }
}