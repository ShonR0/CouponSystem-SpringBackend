package com.jb.CouponSystemSpringShonRassan.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrDetails {
    private final String key = "CouponSystem";
    private String value;
}
