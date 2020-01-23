package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity implements ProgrammingAdapter.ProductCommunication{

    List<Product> movies = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        App.getRetrofit().create(RetroInterface.class).popularProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                movies=response.body();

                System.out.println(response.body());
                RecyclerView recyclerView=findViewById(R.id.recycle_call);
                recyclerView.setLayoutManager(new LinearLayoutManager(RetrofitActivity.this));
                recyclerView.setAdapter(new ProgrammingAdapter(movies,RetrofitActivity.this));

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });





    }


    @Override
    public void onItemClick(Product position) {
        System.out.println(position.getProductName());
        Intent intent=new Intent(RetrofitActivity.this,ProductDetailActivity.class);
        intent.putExtra("name",position.getProductName());
        intent.putExtra("rating",position.getProductRating());
        intent.putExtra("productId",position.getProductId());
        intent.putExtra("image",position.getProductImage());

       // intent.putExtra("url",position.getUrl().getMedium());
        startActivity(intent);
    }

}
