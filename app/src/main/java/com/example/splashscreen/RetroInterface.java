package com.example.splashscreen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetroInterface {


    @GET("/solrsearch/popular")
    Call<List<Product>> popularProduct();


    @POST("/cartOrder/addToCart")
    Call<ResponseLogIn> addToCart(@Body AddCartDetails addCartDetails);
    @GET("/cartOrder/cart/{userId}")
    Call<List<Cart>>  getCart(@Path("userId") String userId);



  @POST("/login/user")
   Call<ResponseLogIn> login(@Body LoginPost loginPost);


  @POST("/login/saveCustomer")
  Call<ResponseLogIn> customerSignUp(@Body SignupDetails signupDetails);


@POST("/solrsearch/search")
Call<List<Search>> getSearch(@Body SearchString searchString);

      @GET("/merchant/listOfMerchant/{productId}")
      Call<ResponseMerchant> getMerchentList(@Path("productId") String productId);

      @GET("/cartOrder/orderHistory/{userId}")
    Call<List<OrderHistory>> orderHistory(@Path("userId") String userId);

      @POST("/login/getFacebookDetails")
    Call<ResponseLogIn> sendFacebookToken(@Body AccessToken accessToken);

      @POST("")
    Call<ResponseLogIn>sendGoogleToken(@Body AccessToken accessToken);





}
