package com.example.splashscreen;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCartActivity extends AppCompatActivity {


    SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
    String token=sharedPreferences.getString("token","");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cart);


        App.getRetrofit().create(RetroInterface.class).getCart(token).enqueue(new Callback<List<Cart>>() {
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