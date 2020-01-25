package com.example.nav_activity.data.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity(tableName = "CARTDETAILS")
public class CartDetails {
    @PrimaryKey(autoGenerate = true)
    private int cartId;
    private String productId;
    private String productName;
    private String productDesc;
    //    private String productCategoryId;
//    private double productRating;
    private String productImage;
    //    private long sellCount;
    private String merchantId;
    private String merchantName;
    private long quantity;
    private double price;
    private double totalPrice;
//    private String orderDate;
}
