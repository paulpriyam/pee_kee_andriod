package com.example.nav_activity;

import java.io.Serializable;

public class Merchant implements Serializable {

    private String merchantId;
    private String merchantName;
    private double price;
    private long quantity;
    private double merchantRating;

    public String getMerchantId() {
        return merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public double getPrice() {
        return price;
    }

    public long getQuantity() {
        return quantity;
    }

    public double getMerchantRating() {
        return merchantRating;
    }

    public Merchant(String merchantId, String merchantName, double price, long quantity, double merchantRating) {
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.price = price;
        this.quantity = quantity;
        this.merchantRating = merchantRating;


    }
}
