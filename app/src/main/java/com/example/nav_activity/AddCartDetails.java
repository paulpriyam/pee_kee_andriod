package com.example.nav_activity;

public class AddCartDetails {



    private String token;
    private String productId;
    private String merchantId;
    private long quantity;

    public AddCartDetails(String token, String productId, String merchantId, long quantity) {
        this.token = token;
        this.productId = productId;
        this.merchantId = merchantId;
        this.quantity = quantity;
    }
}
