package com.softwaretestingcentre.test_sample_shop.helpers;

public class User {
    private String customerIf;
    private String username;

    public User(String customerIf, String username) {
        this.customerIf = customerIf;
        this.username = username;
    }


    public String getCustomerIf() {
        return customerIf;
    }

    public String getUsername() {
        return username;
    }
}
