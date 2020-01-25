package com.example.nav_activity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCartUpdateDetails {
    private String userId;
    private String productId;
    private String merchantId;
    private long quantity;

    public GetCartUpdateDetails(String userId, String productId, String merchantId, long quantity) {
        this.userId = userId;
        this.productId = productId;
        this.merchantId = merchantId;
        this.quantity = quantity;
    }
}
