package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textView=findViewById(R.id.testsignup);

        SignupDetails signupDetails=new SignupDetails("naman","1234","1133445566",20,"asdghjkjhgfd","ffgfdsds","12341234");

        App.getRetrofit().create(RetroInterface.class).customerSignUp(signupDetails).enqueue(new Callback<ResponseLogIn>() {
            @Override
            public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {

                ResponseLogIn responseLogIn=response.body();
                textView.setText(responseLogIn.getMessage());


            }

            @Override
            public void onFailure(Call<ResponseLogIn> call, Throwable t) {

                Log.d("fail","failure");
            }
        });
    }
}
