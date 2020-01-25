package com.example.nav_activity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCartDetails {



    private String token;
    private String productId;
    private String merchantId;
    private long quantity;


    public AddCartDetails() {
    }

    public AddCartDetails(String token, String productId, String merchantId, long quantity) {
        this.token = token;
        this.productId = productId;
        this.merchantId = merchantId;
        this.quantity = quantity;
    }

}
