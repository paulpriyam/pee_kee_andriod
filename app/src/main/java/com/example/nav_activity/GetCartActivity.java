package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetCartActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cart);


        App.getRetrofit().create(RetroInterface.class).getCart("1").enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                List<Cart> list=response.body();
                RecyclerView recyclerView=findViewById(R.id.get_cart_recycle);
                recyclerView.setLayoutManager(new LinearLayoutManager(GetCartActivity.this));
                recyclerView.setAdapter(new GetCartAdaptor(list));

            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });



    }
}