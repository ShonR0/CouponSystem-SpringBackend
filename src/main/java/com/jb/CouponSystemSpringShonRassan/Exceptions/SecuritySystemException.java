package com.jb.CouponSystemSpringShonRassan.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class SecuritySystemException extends Exception {
    private HttpStatus status;
    public SecuritySystemException(SecMsg secMsg) {
        super(secMsg.getMessage());
        this.status = secMsg.getStatus();
    }
}
