package com.jb.CouponSystemSpringShonRassan.services;

import com.jb.CouponSystemSpringShonRassan.Exceptions.CouponSystemException;
import com.jb.CouponSystemSpringShonRassan.repos.CompanyRepository;
import com.jb.CouponSystemSpringShonRassan.repos.CouponRepository;
import com.jb.CouponSystemSpringShonRassan.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientService {
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CompanyRepository companyRepository;
    public abstract boolean login(String email, String password) throws CouponSystemException;
}
