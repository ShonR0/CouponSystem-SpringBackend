package com.jb.CouponSystemSpringShonRassan.Exceptions;


import org.springframework.http.HttpStatus;

public enum ErrorMsg {
    ID_NOT_FOUND("id not found"),
    ID_ALREADY_EXIST("id already exist"),
    EMAIL_NOT_EXIST("email not exist"),
    EMAIL_ALREADY_EXISTS("email already exists"),
    COMPANY_NAME_ALREADY_EXISTS("company name already exists"),
    EMAIL_NOT_FOUND("email not found"),
    COMPANY_NOT_FOUND("email or password are incorrect"),
    CUSTOMER_NOT_FOUND("email or password are incorrect"),
    PASSWORD_NOT_FOUND("password is incorrect"),
    COUPON_AMOUNT("cannot buy coupon with 0 amount"),
    COUPON_EXPIRED("You cannot buy an expired coupon"),
    COMPANY_NAME_NOT_FOUND("company name not exists"),
    COUPON_NAME_EXISTS("your company already using that coupon name"),
    COUPON_ALREADY_PURCHASED("can't buy coupon more than one time "),
    NOT_COMPANY_COUPON_DELETE("you cannot delete another company's coupon"),
    NOT_COMPANY_COUPON_("you cannot update another company's coupon"),
    COMPANY_NOT_EXISTS("company not exists"),
    PRICE_NOT_VALID("price must be higher than 0"),
    COUPON_NOT_BELONG("this coupon id not belong to this company"),
    INVALID_EMAIL_OR_PASSWORD("Invalid email or password");
    private String message;
    ErrorMsg (String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}