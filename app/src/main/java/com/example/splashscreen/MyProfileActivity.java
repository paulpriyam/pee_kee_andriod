package com.example.splashscreen;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyProfileActivity extends AppCompatActivity {


    ImageView profile_pic;
    TextView name;
    TextView email;
    TextView address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        profile_pic=findViewById(R.id.profile_pic);
        name=findViewById(R.id.user_name);
        email=findViewById(R.id.user_email);
        address=findViewById(R.id.userAddress);
    }
}
