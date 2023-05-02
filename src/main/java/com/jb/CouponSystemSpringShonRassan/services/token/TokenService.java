package com.jb.CouponSystemSpringShonRassan.services.token;

import com.jb.CouponSystemSpringShonRassan.Exceptions.SecuritySystemException;
import com.jb.CouponSystemSpringShonRassan.beans.Company;
import com.jb.CouponSystemSpringShonRassan.beans.Customer;
import com.jb.CouponSystemSpringShonRassan.beans.UserType;

import java.util.UUID;

public interface TokenService {
    UUID addCustomer(Customer customer);
    UUID addCompany(Company company);
    void clearTokens();
    boolean isValid(UUID token, UserType type);
    int getCustomerId(UUID token) throws SecuritySystemException;
}
