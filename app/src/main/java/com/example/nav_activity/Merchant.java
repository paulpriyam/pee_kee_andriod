package com.example.nav_activity;

import java.io.Serializable;

public class Merchant implements Serializable {

    int merchant_id;
    String merchant_name;
    String price;
    String quantity;
    double merchant_rating;

    public Merchant(int merchant_id, String merchant_name, String price, String quantity, double merchant_rating) {
        this.merchant_id = merchant_id;
        this.merchant_name = merchant_name;
        this.price = price;
        this.quantity = quantity;
        this.merchant_rating = merchant_rating;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "merchant_id=" + merchant_id +
                ", merchant_name='" + merchant_name + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", merchant_rating=" + merchant_rating +
                '}';
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getMerchant_rating() {
        return merchant_rating;
    }

    public void setMerchant_rating(double merchant_rating) {
        this.merchant_rating = merchant_rating;
    }
}
