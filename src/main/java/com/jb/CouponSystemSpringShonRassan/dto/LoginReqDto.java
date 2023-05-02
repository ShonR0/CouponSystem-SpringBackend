package com.jb.CouponSystemSpringShonRassan.dto;

import com.jb.CouponSystemSpringShonRassan.beans.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginReqDto {
    private String email;
    private String password;
    private UserType userType;
}
