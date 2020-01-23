package com.example.nav_activity;

import java.util.List;

public class ResponseMerchant {

    private int status;
    private String message;
    private List<Merchant> data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Merchant> getData() {
        return data;
    }
}
