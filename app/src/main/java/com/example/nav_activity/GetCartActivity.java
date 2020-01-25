package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.util.Log;

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


        System.out.println("position 1");


        final String token="eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOiI1ZjNjMTJkNS1iMTAzLTQ1YTctYjBhMS1hZTIwZGVmYzFlZWIiLCJyb2xlIjoidXNlciJ9.CMu2xsWQETSgNFr9I-zD7nA7Gmf21nx1Iah3PRt695ONnx5tB78SjI-c0e15Cn4-PVF__vXuyEcnXo89hGV6gw";

        App.getRetrofit().create(RetroInterface.class).getCart("eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOiI1ZjNjMTJkNS1iMTAzLTQ1YTctYjBhMS1hZTIwZGVmYzFlZWIiLCJyb2xlIjoidXNlciJ9.CMu2xsWQETSgNFr9I-zD7nA7Gmf21nx1Iah3PRt695ONnx5tB78SjI-c0e15Cn4-PVF__vXuyEcnXo89hGV6gw").enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {
                System.out.println("position 2");
                ResponseCart responseCart=response.body();

                if(responseCart.getStatus()==1000)
                {
                    System.out.println("in if");
                    List<Cart> list=responseCart.getData();
                    System.out.println("your list size"+list.size());
                    for(int i=0;i<list.size();i++){
                        Log.d("cartId",String.valueOf(list.get(i).getCartId()));

                    }

                    System.out.println(list.toString());
                    RecyclerView recyclerView=findViewById(R.id.get_cart_recycle);
                    recyclerView.setLayoutManager(new LinearLayoutManager(GetCartActivity.this));
                    recyclerView.setAdapter(new GetCartAdaptor(list,token,GetCartActivity.this));



                }

            //    System.out.println(response.body().toString());


            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {

                Log.d("Fail","failure");
            }
        });



    }
}