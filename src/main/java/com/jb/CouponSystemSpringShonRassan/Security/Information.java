package com.jb.CouponSystemSpringShonRassan.Security;

import com.jb.CouponSystemSpringShonRassan.beans.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Information {
    private int id;
    private UserType type;
    private LocalDateTime time;
}
