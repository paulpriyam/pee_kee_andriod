package com.example.nav_activity;

import com.example.nav_activity.data.entity.CartDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetroInterface {


    @GET("/solsearchservice/solrsearch/popular")
    Call<List<Product>> popularProduct();


    @POST("/cartorderservice/cartOrder/remove")
    Call<ResponseLogIn> deleteCartData(@Body AddCartDetails addCartDetails);


    @POST("/cartorderservice/cartOrder/addToCart")
    Call<ResponseLogIn> addToCart(@Body AddCartDetails addCartDetails);
    @GET("/cartorderservice/cartOrder/cart/{token}")
    Call<ResponseCart>  getCart(@Path("token")String token);

  @POST("/cartorderservice/cartOrder/updateQuantity")
  Call<ResponseLogIn> updateCartQuantity(@Body AddCartDetails addCartDetails);



  @POST("/loginservice/login/user")
   Call<ResponseLogIn> login(@Body LoginPost loginPost);


  @POST("/loginservice/login/saveCustomer")
  Call<ResponseLogIn> customerSignUp(@Body SignupDetails signupDetails);


@POST("/solrsearchservice/solrsearch/search")
Call<List<Search>> getSearch(@Body SearchString searchString);

      @GET("/merchantservice/merchant/listOfMerchant/{productId}")
      Call<ResponseMerchant> getMerchentList(@Path("productId")String productId);

      @GET("/cartorderservice/cartOrder/orderHistory/{token}")
    Call<List<OrderHistory>> orderHistory(@Path("token")String token);



}
