package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText name=findViewById(R.id.signupname);
        EditText email=findViewById(R.id.signuemail);
        EditText mobile=findViewById(R.id.signupmobile);
        EditText address1=findViewById(R.id.signupaddress1);
        EditText address2=findViewById(R.id.signupaddress2);
        EditText password=findViewById(R.id.signuppassword);
        EditText conformPassword=findViewById(R.id.signuppasswordconf);






    }
}
