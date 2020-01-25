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

    @Override
    public String toString() {
        return "Search{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productCategoryId='" + productCategoryId + '\'' +
                ", productRating=" + productRating +
                ", productImage='" + productImage + '\'' +
                ", sellCount=" + sellCount +
                ", merchantId='" + merchantId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
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
