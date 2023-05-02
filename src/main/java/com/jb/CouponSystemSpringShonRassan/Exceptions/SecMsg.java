package com.jb.CouponSystemSpringShonRassan.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Getter
public enum SecMsg {
    EMAIL_ALREADY_EXIST("email already exists",HttpStatus.CONFLICT),
    COMPANY_NANE_ALREADY_EXIST("company name already exists",HttpStatus.CONFLICT),
    INVALID_EMAIL_OR_PASSWORD("invalid email or password", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN("invalid token, please login again", HttpStatus.UNAUTHORIZED);
    private String message;
    private HttpStatus status;
    SecMsg (String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
