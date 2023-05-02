package com.jb.CouponSystemSpringShonRassan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerReqDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
