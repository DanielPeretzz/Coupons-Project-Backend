package com.example.couponsproject.excpetion;




public class CouponExpirationDateArrived extends ApplicationException {

    public CouponExpirationDateArrived() {
        super("This coupon is expired!");
    }
}
