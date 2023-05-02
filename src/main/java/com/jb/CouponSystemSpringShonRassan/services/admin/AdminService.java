package com.jb.CouponSystemSpringShonRassan.services.admin;

import com.jb.CouponSystemSpringShonRassan.Exceptions.CouponSystemException;
import com.jb.CouponSystemSpringShonRassan.beans.Company;
import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import com.jb.CouponSystemSpringShonRassan.beans.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    void addCompany(Company company) throws CouponSystemException;
    void updateCompany(int companyId, Company company) throws CouponSystemException;
    void deleteCompany(int companyId) throws CouponSystemException;
    List<Company> getAllCompanies();
    Company getSingleCompany(int companyId) throws CouponSystemException;
    // ===================================================================
    void addCustomer(Customer customer) throws CouponSystemException;
    void updateCustomer(int customerId, Customer customer) throws CouponSystemException;
    void deleteCustomer(int customerId) throws CouponSystemException;
    List<Customer> getAllCustomers();
    Customer getSingleCustomer(int customerId) throws CouponSystemException;
    List<Coupon> getAllCoupons();
}