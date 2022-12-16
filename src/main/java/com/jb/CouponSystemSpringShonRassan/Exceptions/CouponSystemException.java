package com.jb.CouponSystemSpringShonRassan.Exceptions;

public class CouponSystemException extends Exception{
    public CouponSystemException(ErrorMsg errorMsg) {
        super(errorMsg.getMessage());
    }
}