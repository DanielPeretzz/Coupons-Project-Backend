package com.example.couponsproject.error.excpetion;

import com.example.couponsproject.beans.Coupon;

public class FailedToPurchaseException extends ApplicationException {
    public FailedToPurchaseException(Coupon coupon) {
        super("Failed to purchase Coupon id : " + coupon.getId());
    }
}
