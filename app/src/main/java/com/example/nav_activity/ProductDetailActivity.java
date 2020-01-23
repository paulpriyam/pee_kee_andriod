package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        productName = findViewById(R.id.productname);
        productDescription = findViewById(R.id.productdetailslinearprod_des);
        addToCart = findViewById(R.id.productdetailaddtocart);
        buyNow = findViewById(R.id.productdetailbuynow);
        productimage =findViewById(R.id.productdeatilpreview);




       // productName.setText(getIntent().getStringExtra("name"));
       // Glide.with(this).load(getIntent().getStringExtra("url")).into(productimage);
    //    Glide.with(this).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)).load(getIntent().getStringExtra("url")).into(productimage);


        App.getRetrofit().create(RetroInterface.class).getMerchentList("1").enqueue(new Callback<ResponseMerchant>() {
            @Override
            public void onResponse(Call<ResponseMerchant> call, Response<ResponseMerchant> response) {
                ResponseMerchant responseMerchant=response.body();
                List<Merchant> merchants=responseMerchant.getData();

                RecyclerView recyclerView=findViewById(R.id.productdetailrecyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(ProductDetailActivity.this));
                recyclerView.setAdapter(new ProductDetailAdaptor(merchants));

            }

            @Override
            public void onFailure(Call<ResponseMerchant> call, Throwable t) {

                Log.d("Fail","sorry , server did not connect");
            }
        });


        }




}
