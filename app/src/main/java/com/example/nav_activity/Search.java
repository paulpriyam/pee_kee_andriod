package com.example.nav_activity;

import java.io.Serializable;

public class Search implements Serializable {

    private String productId;
    private String productName;
    private String productDesc;
    private String productCategoryId;
    private double productRating;
    private String productImage;
    private long sellCount;
    private String merchantId;
    private long quantity;
    private double price;
    public Search(String productId, String productName, String productDesc, String productCategoryId, double productRating, String productImage, long sellCount, String merchantId, long quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productCategoryId = productCategoryId;
        this.productRating = productRating;
        this.productImage = productImage;
        this.sellCount = sellCount;
        this.merchantId = merchantId;
        this.quantity = quantity;
        this.price = price;
    }
    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public String getProductDesc() {
        return productDesc;
    }
    public String getProductCategoryId() {
        return productCategoryId;
    }
    public double getProductRating() {
        return productRating;
    }
    public String getProductImage() {
        return productImage;
    }
    public long getSellCount() {
        return sellCount;
    }
    public String getMerchantId() {
        return merchantId;
    }
    public long getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }

}
