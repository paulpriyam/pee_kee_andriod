package com.example.nav_activity;

import java.io.Serializable;

public class Merchant implements Serializable {

    private int merchantId;
    private String merchantName;
    private double price;
    private long quantity;
    private double merchantRating;

    @Override
    public String toString() {
        return "Merchant{" +
                "merchantId=" + merchantId +
                ", merchantName='" + merchantName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", merchantRating=" + merchantRating +
                '}';
    }

    public Merchant(int merchantId, String merchantName, double price, long quantity, double merchantRating) {
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.price = price;
        this.quantity = quantity;
        this.merchantRating = merchantRating;
    }

    public int getMerchantId() {
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
}
