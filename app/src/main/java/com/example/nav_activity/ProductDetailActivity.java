package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
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


        //String name=getIntent().getStringExtra("product");
        //productName.setText(name);

        productName.setText(getIntent().getStringExtra("name"));
       // Glide.with(this).load(getIntent().getStringExtra("url")).into(productimage);
        Glide.with(this).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)).load(getIntent().getStringExtra("url")).into(productimage);




        App.getRetrofit().create(RetroInterface.class).getMerchentList().enqueue(new Callback<List<Merchant>>() {
            @Override
            public void onResponse(Call<List<Merchant>> call, Response<List<Merchant>> response) {
                if(!response.isSuccessful()){

                }

              if(response.body()==null)
                  System.out.println("empty string");
              else{
                  List<Merchant> merchants=response.body();

                  //  System.out.println(merchants.toString());
                  RecyclerView recyclerView=findViewById(R.id.productdetailrecyclerview);
                  recyclerView.setLayoutManager(new LinearLayoutManager(ProductDetailActivity.this));
                  recyclerView.setAdapter(new ProductDetailAdaptor(merchants));


              }



            }

            @Override
            public void onFailure(Call<List<Merchant>> call, Throwable t) {

            }
        });

    }
}