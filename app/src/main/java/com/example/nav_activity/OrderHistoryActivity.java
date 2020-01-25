package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderHistoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);



        App.getRetrofit().create(RetroInterface.class).orderHistory("1").enqueue(new Callback<List<OrderHistory>>() {
            @Override
            public void onResponse(Call<List<OrderHistory>> call, Response<List<OrderHistory>> response) {
                List<OrderHistory> list=response.body();
                RecyclerView recyclerView=findViewById(R.id.order_history_recycle);
                recyclerView.setLayoutManager(new LinearLayoutManager(OrderHistoryActivity.this));
                recyclerView.setAdapter(new OrderHistoryAdaptor(list));


            }

            @Override
            public void onFailure(Call<List<OrderHistory>> call, Throwable t) {

            }
        });




    }
}
