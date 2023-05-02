package com.jb.CouponSystemSpringShonRassan.dto;

import com.jb.CouponSystemSpringShonRassan.beans.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResDto {
    private UUID token;
    private String email;
    private UserType userType;
}
