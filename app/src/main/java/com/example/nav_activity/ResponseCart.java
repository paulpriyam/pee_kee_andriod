package com.example.nav_activity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ResponseCart {
    private int status;
    private String message;
    private List<Cart> data;
}