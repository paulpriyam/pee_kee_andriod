package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddToCartActivity extends AppCompatActivity {


    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);





App.getRetrofit().create(RetroInterface.class).addToCart("a","a","q",1).enqueue(new Callback<Demo>() {
    @Override
    public void onResponse(Call<Demo> call, Response<Demo> response) {

    }

    @Override
    public void onFailure(Call<Demo> call, Throwable t) {

    }
});



    }
}
