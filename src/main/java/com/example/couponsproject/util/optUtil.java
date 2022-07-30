package com.example.couponsproject.util;

import com.example.couponsproject.beans.Company;
import com.example.couponsproject.beans.Coupon;
import com.example.couponsproject.beans.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class optUtil {
    // this class take care of <optional> checking if the object are return form db not null


   public static Company optionalCompany(Optional<Company> company) {
        if (company.isEmpty()) {
            return null;
        }
        return company.get();
    }

    public static Coupon optionalCoupon(Optional<Coupon> coupon) {
        if (coupon.isEmpty()) {
            return null;
        }
        return coupon.get();
    }

    public static Customer optionalCustomer(Optional<Customer> customer) {
        if (customer.isEmpty()) {
            return null;
        }
        return customer.get();
    }
}
