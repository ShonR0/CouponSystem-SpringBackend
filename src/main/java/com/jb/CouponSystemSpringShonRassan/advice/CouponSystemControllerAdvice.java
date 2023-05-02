package com.jb.CouponSystemSpringShonRassan.advice;

import com.jb.CouponSystemSpringShonRassan.Exceptions.CouponSystemException;
import com.jb.CouponSystemSpringShonRassan.Exceptions.SecuritySystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponSystemControllerAdvice {
    @ExceptionHandler(value = {CouponSystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDetails handleError(Exception e) {
        return ErrDetails.builder().value(e.getMessage()).build();
    }
    @ExceptionHandler(value = {SecuritySystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleError(SecuritySystemException e) {
        ErrDetails err = ErrDetails.builder().value(e.getMessage()).build();
        HttpStatus status = e.getStatus();
        return new ResponseEntity<>(err, status);
    }
}
