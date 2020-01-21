package com.example.nav_activity;

import java.io.Serializable;

public class Demo implements Serializable {
    int cart_Id;
    String user_id;
    String merchent_id;
    String quantity;

    public int getCart_Id() {
        return cart_Id;
    }

    public void setCart_Id(int cart_Id) {
        this.cart_Id = cart_Id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMerchent_id() {
        return merchent_id;
    }

    public void setMerchent_id(String merchent_id) {
        this.merchent_id = merchent_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
