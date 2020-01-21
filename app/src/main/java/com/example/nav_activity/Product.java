package com.example.nav_activity;

import java.io.Serializable;

public class Product implements Serializable {
    private String productId;
    private String productName;
    private String productDesc;
    private Double productRating;
    private String productImage;
    private long sellCount;
    private String productCategoryId;
    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public String getProductDesc() {
        return productDesc;
    }
    public Double getProductRating() {
        return productRating;
    }
    public String getProductImage() {
        return productImage;
    }
    public long getSellCount() {
        return sellCount;
    }
    public String getProductCategoryId() {
        return productCategoryId;
    }
    public Product(String productId, String productName, String productDesc, Double productRating, String productImage, long sellCount, String productCategoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productRating = productRating;
        this.productImage = productImage;
        this.sellCount = sellCount;
        this.productCategoryId = productCategoryId;
    }
}
