package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        String token=sharedPreferences.getString("token","");
        boolean loginStatus=sharedPreferences.getBoolean("loginStatus",false);
        if(loginStatus) {
            App.getRetrofit().create(RetroInterface.class).orderHistory(token).enqueue(new Callback<ResponseOrder>() {
                @Override
                public void onResponse(Call<ResponseOrder> call, Response<ResponseOrder> response) {

                    ResponseOrder responseOrder = response.body();
                    List<OrderHistory> list=responseOrder.getData();
                    System.out.println("list size:"+list.size());
                    RecyclerView recyclerView = findViewById(R.id.order_history_recycle);
                    recyclerView.setLayoutManager(new LinearLayoutManager(OrderHistoryActivity.this));
                    recyclerView.setAdapter(new OrderHistoryAdaptor(list));


                }

                @Override
                public void onFailure(Call<ResponseOrder> call, Throwable t) {

                }
            });
        }
        else{
            Intent intent=new Intent(OrderHistoryActivity.this,SignIn.class);
            startActivity(intent);
        }




    }
}
