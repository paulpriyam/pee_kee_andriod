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

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailAdaptor.MerchantCommunication {
    TextView productName;
    TextView productDescription;
    Button addToCart;
    Button buyNow;
    ImageView productimage;
    SharedPreferences sharedPreferences;

    CartDetailsViewModel cartDetailsViewModel;
    String productId;
    //TODO:change it to dynamic

    List<Merchant> merchants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        productName = findViewById(R.id.productname);
        productDescription = findViewById(R.id.productdetailslinearprod_des);
        addToCart = findViewById(R.id.productdetailaddtocart);
        buyNow = findViewById(R.id.productdetailbuynow);
        productimage = findViewById(R.id.productdeatilpreview);


        productId = getIntent().getStringExtra("productId");

        String url = getIntent().getStringExtra("image");
        Glide.with(this).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)).load(url).into(productimage);


        // productName.setText(getIntent().getStringExtra("name"));
        // Glide.with(this).load(getIntent().getStringExtra("url")).into(productimage);
        //    Glide.with(this).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)).load(getIntent().getStringExtra("url")).into(productimage);


        App.getRetrofit().create(RetroInterface.class).getMerchentList("1").enqueue(new Callback<ResponseMerchant>() {
            @Override
            public void onResponse(Call<ResponseMerchant> call, Response<ResponseMerchant> response) {
                ResponseMerchant responseMerchant = response.body();
                merchants = responseMerchant.getData();

                RecyclerView recyclerView = findViewById(R.id.productdetailrecyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(ProductDetailActivity.this));
                recyclerView.setAdapter(new ProductDetailAdaptor(merchants, ProductDetailActivity.this));

            }

            @Override
            public void onFailure(Call<ResponseMerchant> call, Throwable t) {

                Log.d("Fail", "sorry , server did not connect");
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCartDetails addCartDetails = new AddCartDetails("eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOiI1ZjNjMTJkNS1iMTAzLTQ1YTctYjBhMS1hZTIwZGVmYzFlZWIiLCJyb2xlIjoidXNlciJ9.CMu2xsWQETSgNFr9I-zD7nA7Gmf21nx1Iah3PRt695ONnx5tB78SjI-c0e15Cn4-PVF__vXuyEcnXo89hGV6gw", "1", merchants.get(ProductDetailAdaptor.lastSelectedPosition).getMerchantId().toString(), 1);
                App.getRetrofit().create(RetroInterface.class).addToCart(addCartDetails).enqueue(new Callback<ResponseLogIn>() {
                    @Override
                    public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {

                        Log.d("response", "came");
                        ResponseLogIn responseAddtoCart = response.body();
                        System.out.println(response.body());
                        if (responseAddtoCart.getStatus() == 1000)
                            Toast.makeText(ProductDetailActivity.this, "Added ToCart", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ProductDetailActivity.this, "Failure", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<ResponseLogIn> call, Throwable t) {


                    }
                });

            }


        });

    }

    @Override
    public void onAddClick(Merchant position) {

    }
}




