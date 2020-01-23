package com.example.nav_activity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetroInterface {


    @GET("/solrsearch/popular")
    Call<List<Product>> popularProduct();


    @POST("/cartOrder/addToCart")
    Call<Demo> addToCart(
            @Field("userId") String userId,
            @Field("productId")String productId,
            @Field("merchantId")String merchant_id,
            @Field("quantity") long quantity
            );
    @GET("/cartOrder/cart/{userId}")
    Call<List<Cart>>  getCart(@Path("userId")String userId);



  @POST("/login/user")
   Call<ResponseLogIn> login(@Body LoginPost loginPost);


  @POST("/login/saveCustomer")
  Call<ResponseLogIn> customerSignUp(@Body SignupDetails signupDetails);


@POST("/solrsearch/search")
Call<List<Search>> getSearch(@Body SearchString searchString);

      @GET("/merchant/listOfMerchant/{productId}")
      Call<ResponseMerchant> getMerchentList(@Path("productId")String productId);

      @GET("/cartOrder/orderHistory/{userId}")
    Call<List<OrderHistory>> orderHistory(@Path("userId")String userId);



}
