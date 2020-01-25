package com.example.nav_activity;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessToken {
    @SerializedName("accesstoken")
    private String accesstoken;
    private int role;
    private String type;
}
