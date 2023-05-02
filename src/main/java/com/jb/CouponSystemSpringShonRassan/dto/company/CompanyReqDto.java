package com.jb.CouponSystemSpringShonRassan.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyReqDto {
    private String name;
    private String email;
    private String password;
}
