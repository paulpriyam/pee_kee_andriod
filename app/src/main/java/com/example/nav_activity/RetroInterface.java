package com.example.nav_activity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetroInterface {


    @GET("/json/glide.json")
    Call<List<Movies>> getmovie();


    @FormUrlEncoded
    @POST("posts")
    Call<Demo> addToCart(
            @Field("cart_id") int cartId,
            @Field("user_id")String userId,
            @Field("merchant_id")String merchant_id,
            @Field("quantity")String quantity
            );


      @GET("posts")
      Call<List<Merchant>> getMerchentList();



}
