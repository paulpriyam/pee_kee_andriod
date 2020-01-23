package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        final TextView textView=findViewById(R.id.testText);




        LoginPost loginPost=new LoginPost("abc@gmail.com","hello",0,null);

        App.getRetrofit().create(RetroInterface.class).login(loginPost).enqueue(new Callback<ResponseLogIn>() {
            @Override
            public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {

                ResponseLogIn responseLogIn=response.body();


              textView.setText(responseLogIn.getData());



              //  textView.setText(responseLogIn.getData());

            }

            @Override
            public void onFailure(Call<ResponseLogIn> call, Throwable t) {
                Log.d("Fail","failed to connect");
            }
        });



    }
}
