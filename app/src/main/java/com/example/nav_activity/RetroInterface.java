package com.example.nav_activity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetroInterface {


    @GET("solrsearchservice/solrsearch/popular")
    Call<List<Product>> popularProduct();


    @POST("cartorderservice/cartOrder/addToCart")
    Call<ResponseLogIn> addToCart(@Body AddCartDetails addCartDetails);

    @GET("/cartorderservice/cartOrder/cart/{token}")
    Call<ResponseCart>  getCart(@Path("token")String token);

  @POST("loginservice/user")
   Call<ResponseLogIn> login(@Body LoginPost loginPost);

    @POST("loginservice/getGoogleDetailsFromAndroid")
    Call<ResponseLogIn> sendGoogleToken(@Body AccessToken accessToken);

    @POST("loginservice/getFacebookDetails")
    Call<ResponseLogIn> sendFacebookToken(@Body AccessToken accessToken);


    @POST("loginservice/saveCustomer")
  Call<ResponseLogIn> customerSignUp(@Body SignupDetails signupDetails);

    @POST("/cartorderservice/cartOrder/remove")
    Call<ResponseLogIn> deleteCartData(@Body AddCartDetails addCartDetails);

    @POST("/cartorderservice/cartOrder/updateQuantity")
    Call<ResponseLogIn> updateCartQuantity(@Body AddCartDetails addCartDetails);

@POST("solrsearchservice/solrsearch/search")
Call<List<Search>> getSearch(@Body SearchString searchString);

@GET("cartorderservice/cartOrder/checkout/{token}")
Call<ResponseLogIn>checkout(@Path("token") String token);

      @GET("merchantservice/merchant/listOfMerchant/{productId}")
      Call<ResponseMerchant> getMerchentList(@Path("productId")String productId);

      @GET("cartorderservice/cartOrder/orderHistory/{token}")
    Call<ResponseOrder> orderHistory(@Path("token")String token);



}
