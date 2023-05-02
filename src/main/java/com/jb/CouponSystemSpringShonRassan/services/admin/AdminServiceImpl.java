package com.jb.CouponSystemSpringShonRassan.services.admin;

import com.jb.CouponSystemSpringShonRassan.Exceptions.CouponSystemException;
import com.jb.CouponSystemSpringShonRassan.Exceptions.ErrorMsg;
import com.jb.CouponSystemSpringShonRassan.beans.Company;
import com.jb.CouponSystemSpringShonRassan.beans.Coupon;
import com.jb.CouponSystemSpringShonRassan.beans.Customer;
import com.jb.CouponSystemSpringShonRassan.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ClientService implements AdminService{
    @Override
    public void addCompany(Company company) throws CouponSystemException {
        int companyId = company.getId();
        if (this.companyRepository.existsById(companyId)){
            throw new CouponSystemException(ErrorMsg.ID_ALREADY_EXIST);
        }
        if (this.companyRepository.existsByName(company.getName())){
            throw new CouponSystemException(ErrorMsg.EMAIL_ALREADY_EXISTS);
        }
        if (this.companyRepository.existsByEmail(company.getEmail())){
            throw new CouponSystemException(ErrorMsg.COMPANY_NAME_ALREADY_EXISTS);
        }
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponSystemException {
        if (!this.companyRepository.existsById(companyId)){
            throw new CouponSystemException(ErrorMsg.ID_NOT_FOUND);
        }
        companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int companyId) throws CouponSystemException {
        if (!this.companyRepository.existsById(companyId)){
            throw new CouponSystemException(ErrorMsg.ID_NOT_FOUND);
        }
        companyRepository.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getSingleCompany(int companyId) throws CouponSystemException {
        return this.companyRepository.findById(companyId)
                .orElseThrow(()->new CouponSystemException(ErrorMsg.ID_NOT_FOUND));
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        String email = customer.getEmail();
        if (this.companyRepository.existsByEmail(email)){
            throw new CouponSystemException(ErrorMsg.EMAIL_ALREADY_EXISTS);
        }
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponSystemException {
        if (!this.customerRepository.existsById(customerId)){
            throw new CouponSystemException(ErrorMsg.ID_NOT_FOUND);
        }
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws CouponSystemException {
        if (!this.customerRepository.existsById(customerId)){
            throw new CouponSystemException(ErrorMsg.ID_NOT_FOUND);
        }
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(int customerId) throws CouponSystemException {
        return this.customerRepository.findById(customerId)
                .orElseThrow(()-> new CouponSystemException(ErrorMsg.ID_NOT_FOUND));
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!this.companyRepository.existsByName(email)){
            throw new CouponSystemException(ErrorMsg.EMAIL_NOT_FOUND);
        }
        if (!this.companyRepository.existsByEmail(password)){
            throw new CouponSystemException(ErrorMsg.COMPANY_NAME_NOT_FOUND);
        }
        return true;
    }
}
