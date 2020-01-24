package com.example.splashscreen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailAdaptor.MerchantCommunication{
    TextView productName;
    TextView productDescription;
    Button addToCart;
    Button buyNow;
    ImageView productimage;
    int productId=1;     //TODO:change it to dynamic

    List<Merchant> merchants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        productName = findViewById(R.id.productname);
        productDescription = findViewById(R.id.productdetailslinearprod_des);
        addToCart = findViewById(R.id.productdetailaddtocart);
        buyNow = findViewById(R.id.productdetailbuynow);
        productimage =findViewById(R.id.productdeatilpreview);


        String url=getIntent().getStringExtra("image");
        Glide.with(this).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)).load(url).into(productimage);


       // productName.setText(getIntent().getStringExtra("name"));
       // Glide.with(this).load(getIntent().getStringExtra("url")).into(productimage);
    //    Glide.with(this).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)).load(getIntent().getStringExtra("url")).into(productimage);


        App.getRetrofit().create(RetroInterface.class).getMerchentList("1").enqueue(new Callback<ResponseMerchant>() {
            @Override
            public void onResponse(Call<ResponseMerchant> call, Response<ResponseMerchant> response) {
                ResponseMerchant responseMerchant=response.body();
                merchants=responseMerchant.getData();

                RecyclerView recyclerView=findViewById(R.id.productdetailrecyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(ProductDetailActivity.this));
                recyclerView.setAdapter(new ProductDetailAdaptor(merchants,ProductDetailActivity.this));

            }

            @Override
            public void onFailure(Call<ResponseMerchant> call, Throwable t) {

                Log.d("Fail","sorry , server did not connect");
            }
        });


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Log.d("position:", String.valueOf(merchants.get(ProductDetailAdaptor.lastSelectedPosition)));

          //  Toast.makeText(ProductDetailActivity.this,String.valueOf(ProductDetailAdaptor.lastSelectedPosition),Toast.LENGTH_LONG).show();

                AddCartDetails addCartDetails=new AddCartDetails("eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOiJmaGQiLCJ1c2VyRW1haWwiOiJqeW90aGlwMDA5QGdtYWlsLmNvbSJ9.2ktkrE0pIjsO3ZA0_ZFRHWccq13-Zcf1C-8mGs1SVE4NJc8_t8Sz5HooL5uY3gaS85j5ivh7bCeRfhjCH3jvXg","1",merchants.get(ProductDetailAdaptor.lastSelectedPosition).getMerchantId().toString(),1);
                App.getRetrofit().create(RetroInterface.class).addToCart(addCartDetails).enqueue(new Callback<ResponseLogIn>() {
                    @Override
                    public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {

                        Log.d("response","came");
                        ResponseLogIn responseAddtoCart=response.body();
                        System.out.println(response.body());
                        if(responseAddtoCart.getStatus()==1000)
                            Toast.makeText(ProductDetailActivity.this,"Added ToCart",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ProductDetailActivity.this,"Failure",Toast.LENGTH_LONG).show();

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
