package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText mobileNo;
    EditText age;
    EditText address;
    ImageView image;
    EditText password;

//TODO:image receive
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobileNo=findViewById(R.id.mobile);
        age=findViewById(R.id.age);
        address=findViewById(R.id.address);
        password=findViewById(R.id.password);



        SignupDetails signupDetails=new SignupDetails("naman","hyasddd","1133445566",97,"asdghjkjhgfd","ffgfdsds","12341234");

        App.getRetrofit().create(RetroInterface.class).customerSignUp(signupDetails).enqueue(new Callback<ResponseLogIn>() {
            @Override
            public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {

                ResponseLogIn logIn=response.body();



                Toast.makeText(SignUp.this,logIn.getMessage(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ResponseLogIn> call, Throwable t) {

                Log.d("fail","failure");
            }
        });
    }
}
