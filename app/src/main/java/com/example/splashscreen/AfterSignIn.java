package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AfterSignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sign_in);


        TextView aftersignin=findViewById(R.id.afterSignin);
        Intent intent=getIntent();
        String str=intent.getStringExtra("token");
        aftersignin.setText(str);
        System.out.println(str);
    }


}
