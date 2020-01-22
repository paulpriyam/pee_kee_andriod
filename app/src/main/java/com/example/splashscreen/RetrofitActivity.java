package com.example.splashscreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity implements ProgrammingAdapter.ProductCommunication{

    List<Movies> movies = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);





        final Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.androidhive.info/").addConverterFactory(GsonConverterFactory.create()).build();

        RetroInterface retroInterface=retrofit.create(RetroInterface.class);

        Call<List<Movies>> call=retroInterface.getmovie();

        call.enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {


                 movies=response.body();


                System.out.println(response.body());
               RecyclerView recyclerView=findViewById(R.id.recycle_call);
               recyclerView.setLayoutManager(new LinearLayoutManager(RetrofitActivity.this));
               recyclerView.setAdapter(new ProgrammingAdapter(movies,RetrofitActivity.this));

            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {

            }
        });
    }


    @Override
    public void onItemClick(Movies position) {
        System.out.println(position.getName());
        Intent intent=new Intent(RetrofitActivity.this,ProductDetailActivity.class);
        intent.putExtra("name",position.getName());
        intent.putExtra("url",position.getUrl().getMedium());
        startActivity(intent);
    }
}
