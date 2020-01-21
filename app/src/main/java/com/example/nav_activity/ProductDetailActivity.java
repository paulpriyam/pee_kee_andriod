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


    Retrofit retrofit;
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


        retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();

        RetroInterface retroInterface=retrofit.create(RetroInterface.class);

        Call<List<Merchant>> call=retroInterface.getMerchentList();

        call.enqueue(new Callback<List<Merchant>>() {
            @Override
            public void onResponse(Call<List<Merchant>> call, Response<List<Merchant>> response) {
                if(!response.isSuccessful()){

                }

              //  Merchant m1=new Merchant(1,"n","21","1",2.0);
                //Merchant m2=new Merchant(1,"n","21","1",2.0);


                List<Merchant> merchants=response.body();
             //   merchants.add(m1);
               // merchants.add(m2);
              //  System.out.println(merchants.toString());
                RecyclerView recyclerView=findViewById(R.id.productdetailrecyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(ProductDetailActivity.this));
                recyclerView.setAdapter(new ProductDetailAdaptor(merchants));

            }

            @Override
            public void onFailure(Call<List<Merchant>> call, Throwable t) {

            }
        });

    }
}