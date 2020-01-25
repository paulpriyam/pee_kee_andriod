package com.example.nav_activity;

import java.io.Serializable;

public class OrderHistory implements Serializable {

    private String productId;
    private String productName;
    private String productDesc;
    private String productCategoryId;
    private double productRating;
    private String productImage;
    private long sellCount;
    private String merchantName;
    private long quantity;
    private double price;
    private double totalPrice;
    private String orderDate;

    public OrderHistory(String productId, String productName, String productDesc, String productCategoryId, double productRating, String productImage, long sellCount, String merchantName, long quantity, double price, double totalPrice, String orderDate) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productCategoryId = productCategoryId;
        this.productRating = productRating;
        this.productImage = productImage;
        this.sellCount = sellCount;
        this.merchantName = merchantName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public double getProductRating() {
        return productRating;
    }

    public void setProductRating(double productRating) {
        this.productRating = productRating;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public long getSellCount() {
        return sellCount;
    }

    public void setSellCount(long sellCount) {
        this.sellCount = sellCount;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
