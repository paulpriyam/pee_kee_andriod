package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nav_activity.data.entity.CartDetails;
import com.example.nav_activity.data.viewModel.CartDetailsViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailActivity extends AppCompatActivity {
    TextView productName;
    TextView productDescription;
    Button addToCart;
    Button buyNow;
    ImageView productimage;
    TextView productRating;


    CartDetailsViewModel cartDetailsViewModel;
    int count=0;
    String productId;
        //TODO:change it to dynamic

    List<Merchant> merchants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        final boolean loginStatus = sharedPreferences.getBoolean("loginStatus", false);
        System.out.println(loginStatus);
        final String token=sharedPreferences.getString("token","");
        productName = findViewById(R.id.productname);
        productDescription = findViewById(R.id.productdetailslinearprod_des);
        addToCart = findViewById(R.id.productdetailaddtocart);
        buyNow = findViewById(R.id.productdetailbuynow);
        productimage =findViewById(R.id.productdeatilpreview);
        productRating=findViewById(R.id.productratings);
        productId=getIntent().getStringExtra("productId");
        System.out.println("productId in create:"+productId);
        String url=getIntent().getStringExtra("image");
        Glide.with(this).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)).load(url).into(productimage);


        productName.setText(getIntent().getStringExtra("name"));
        productDescription.setText(getIntent().getStringExtra("desc"));




        App.getRetrofit().create(RetroInterface.class).getMerchentList(productId).enqueue(new Callback<ResponseMerchant>() {
            @Override
            public void onResponse(Call<ResponseMerchant> call, Response<ResponseMerchant> response) {
                ResponseMerchant responseMerchant=response.body();
                System.out.println(responseMerchant.getStatus());
                if(responseMerchant.getStatus()==1000) {
                    merchants = responseMerchant.getData();
                    //System.out.println(merchants.toString());
                    RecyclerView recyclerView = findViewById(R.id.productdetailrecyclerview);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ProductDetailActivity.this));
                    recyclerView.setAdapter(new ProductDetailAdaptor(merchants));
                }
                else{
                    System.out.println("failed");
                }

            }

            @Override
            public void onFailure(Call<ResponseMerchant> call, Throwable t) {

                Log.d("Fail","sorry , server did not connect");
            }
        });


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 //           Log.d("position:", String.valueOf(merchants.get(ProductDetailAdaptor.lastSelectedPosition)));

          //  Toast.makeText(ProductDetailActivity.this,String.valueOf(ProductDetailAdaptor.lastSelectedPosition),Toast.LENGTH_LONG).show();



                if(loginStatus)
                {
                    AddCartDetails addCartDetails=new AddCartDetails(token,productId,merchants.get(ProductDetailAdaptor.lastSelectedPosition).getMerchantId(),1);
                    App.getRetrofit().create(RetroInterface.class).addToCart(addCartDetails).enqueue(new Callback<ResponseLogIn>() {
                        @Override
                        public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {

                            Log.d("response","came");
                            ResponseLogIn responseAddtoCart=response.body();
                            System.out.println(response.body());
                            if(responseAddtoCart.getStatus()==1000) {
                                addToCart.setText("Added to Cart");
                                addToCart.setEnabled(false);
                                Toast.makeText(ProductDetailActivity.this, "Added ToCart", Toast.LENGTH_LONG).show();
                            }
                            else
                                Toast.makeText(ProductDetailActivity.this,"Failure",Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onFailure(Call<ResponseLogIn> call, Throwable t) {


                        }
                    });
                }
                else{
                    cartDetailsViewModel= ViewModelProviders.of(ProductDetailActivity.this).get(CartDetailsViewModel.class);
                    CartDetails cartDetails=setCartDetails("productName","product Description",productId,"merchant Name",merchants.get(ProductDetailAdaptor.lastSelectedPosition).getMerchantId(),23.0,1);
                    cartDetailsViewModel.insert(cartDetails);
                    addToCart.setText("Added to Cart");
                    addToCart.setEnabled(false);
                   LiveData<List<CartDetails>> cartDetailsList=cartDetailsViewModel.getCartItems();
                    cartDetailsViewModel.getCartItems().observe(ProductDetailActivity.this, new Observer<List<CartDetails>>() {
                        @Override
                        public void onChanged(List<CartDetails> cartDetailsList1) {
                            System.out.println(count++);
                            System.out.println(cartDetailsList1.toString());

                        }
                    });

                }






            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginStatus) {
                    if (addToCart.isEnabled() == false) {
                        Intent intent = new Intent(ProductDetailActivity.this, GetCartActivity.class);
                        startActivity(intent);
                    } else {
                        AddCartDetails addCartDetails = new AddCartDetails(token, "1", merchants.get(ProductDetailAdaptor.lastSelectedPosition).getMerchantId(), 1);
                        App.getRetrofit().create(RetroInterface.class).addToCart(addCartDetails).enqueue(new Callback<ResponseLogIn>() {
                            @Override
                            public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {

                                Log.d("response", "came");
                                ResponseLogIn responseAddtoCart = response.body();
                                System.out.println(response.body());
                                if (responseAddtoCart.getStatus() == 1000) {
                                    addToCart.setText("Added to Cart");
                                    addToCart.setEnabled(false);
                                    Intent intent = new Intent(ProductDetailActivity.this, GetCartActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(ProductDetailActivity.this, "Added ToCart", Toast.LENGTH_LONG).show();

                                } else
                                    Toast.makeText(ProductDetailActivity.this, "Failure", Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onFailure(Call<ResponseLogIn> call, Throwable t) {


                            }
                        });
                    }
                } else {
                    Intent intent = new Intent(ProductDetailActivity.this, SignIn.class);
                    startActivity(intent);
                }

            }
        });

    }




    CartDetails setCartDetails(String productName,String productDesc,String productId,String merchantName,String merchantId,double price,long quantity)
    {
        CartDetails cartDetails = new CartDetails();
        System.out.println("productId in set:"+productId);
        cartDetails.setQuantity((int) quantity);
        cartDetails.setProductDesc(productDesc);
        cartDetails.setPrice(price);
        cartDetails.setTotalPrice(quantity*price);
        cartDetails.setMerchantName(merchantName);
        cartDetails.setMerchantId(merchantId);
        cartDetails.setProductName(productName);
        cartDetails.setProductId(productId);
        return cartDetails;
    }
}
