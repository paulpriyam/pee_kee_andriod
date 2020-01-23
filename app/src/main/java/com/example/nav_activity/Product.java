package com.example.nav_activity;

import java.io.Serializable;

public class Product implements Serializable {
    private String productId;
    private String productName;
    private String productDesc;
    private double productRating;
    private String productImage;
    private long sellCount;
    private String productCategoryId;
    private String merchantId;
    private long quantity;
    private double price;


    public Product() {
    }

    public Product(String productId, String productName, String productDesc, Double productRating, String productImage, long sellCount, String productCategoryId, String merchantId, long quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productRating = productRating;
        this.productImage = productImage;
        this.sellCount = sellCount;
        this.productCategoryId = productCategoryId;
        this.merchantId = merchantId;
        this.quantity = quantity;
        this.price = price;
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

    public Double getProductRating() {
        return productRating;
    }

    public void setProductRating(Double productRating) {
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

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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
}
