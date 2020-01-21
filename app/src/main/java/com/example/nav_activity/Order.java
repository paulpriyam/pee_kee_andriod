package com.example.nav_activity;

import java.io.Serializable;

public class Order implements Serializable {

    private int  orderId;
    private String userId;
    private String merchantId;
    private String productId;
    private long quantity;
    private double price;
    private String orderDate;
    private double totalPrice;
    public Order(int orderId, String userId, String merchantId, String productId, long quantity, double price, String orderDate, double totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.merchantId = merchantId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }
    public int getOrderId() {
        return orderId;
    }
    public String getUserId() {
        return userId;
    }
    public String getMerchantId() {
        return merchantId;
    }
    public String getProductId() {
        return productId;
    }
    public long getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
}
