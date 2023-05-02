package com.jb.CouponSystemSpringShonRassan.services.token;

import com.jb.CouponSystemSpringShonRassan.Exceptions.SecMsg;
import com.jb.CouponSystemSpringShonRassan.Exceptions.SecuritySystemException;
import com.jb.CouponSystemSpringShonRassan.Security.Information;
import com.jb.CouponSystemSpringShonRassan.beans.Company;
import com.jb.CouponSystemSpringShonRassan.beans.Customer;
import com.jb.CouponSystemSpringShonRassan.beans.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{
    private final Map<UUID, Information> map;

    @Override
    public UUID addCustomer(Customer customer) {
        UUID token = UUID.randomUUID();
        Information information = Information.builder()
                .id(customer.getId())
                .type(customer.getUserType())
                .time(LocalDateTime.now())
                .build();
        map.put(token, information);
        return token;
    }

    @Override
    public UUID addCompany(Company company) {
        UUID token = UUID.randomUUID();
        Information information = Information.builder()
                .id(company.getId())
                .type(company.getUserType())
                .time(LocalDateTime.now())
                .build();
        map.put(token, information);
        return token;
    }

    @Override
    public void clearTokens() {
        map.values().removeIf(obj -> obj.getTime().isBefore(LocalDateTime.now().minusMinutes(30)));
    }

    @Override
    public boolean isValid(UUID token, UserType type) {
        Information info = map.get(token);
        if (info != null) {
            return info.getType().equals(type);
        }
        return false;
    }

    @Override
    public int getCustomerId(UUID token) throws SecuritySystemException {
        Information info = map.get(token);
        if (info != null) {
            return info.getId();
        }
        throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
    }
}
