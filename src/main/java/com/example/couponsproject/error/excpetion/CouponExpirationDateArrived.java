package com.example.couponsproject.error.excpetion;




public class CouponExpirationDateArrived extends ApplicationException {

    public CouponExpirationDateArrived() {
        super("This coupon is expired!");
    }
}
