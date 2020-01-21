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

    Retrofit retrofit;
    RetroInterface retroInterface;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);


        retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();
        retroInterface=retrofit.create(RetroInterface.class);

        Call<Demo> call=retroInterface.addToCart(1,"a","q","s");
        call.enqueue(new Callback<Demo>() {
            @Override
            public void onResponse(Call<Demo> call, Response<Demo> response) {

                textViewResult=findViewById(R.id.textViewResult);
                Demo userResponse=response.body();

                String content="";
                content +="code :"+response.code()+"\n";
                content+="Id" + userResponse.getCart_Id() + "\n";
                content+="UserId" + userResponse.getMerchent_id() + "\n";
                content+="title" +userResponse.getQuantity() + "\n\n";

                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Demo> call, Throwable t) {

            }
        });



    }
}
