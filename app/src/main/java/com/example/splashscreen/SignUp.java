package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    private  EditText name;
    private EditText email;
    private  EditText mobile;
    private  EditText address1;
    private EditText age;
    private EditText password;
    private EditText conformPassword;
    private ImageView profilepic;
    private Button signup;


    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
         name=findViewById(R.id.signupname);
         email=findViewById(R.id.signuemail);
         mobile=findViewById(R.id.signupmobile);
         address1=findViewById(R.id.signupaddress1);
         age=findViewById(R.id.signupage);
         password=findViewById(R.id.signuppassword);
         conformPassword=findViewById(R.id.signuppasswordconf);
         profilepic=findViewById(R.id.signuppic);
         signup=findViewById(R.id.signupsignup);



         final SignupDetails signupDetails=new SignupDetails("","","",5,"","","");

         signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 if (!validateEmail() | !validateUsername() | !validatePassword()) {
                     return;
                 }

                 String input = "Email: " + email.getText().toString();
                 input += "\n";
                 input += "Username: " + name.getText().toString();
                 input += "\n";
                 input += "Password: " + password.getText().toString();

                 Toast.makeText(SignUp.this, input, Toast.LENGTH_SHORT).show();


                 App.getRetrofit().create(RetroInterface.class).customerSignUp(signupDetails).enqueue(new Callback<ResponseLogIn>() {
                     @Override
                     public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {

                         if(!response.isSuccessful())
                         {
                             Log.d("signup","error... in getting response");
                             return;
                         }
                         ResponseLogIn responseLogIn=response.body();
                         String message=responseLogIn.getMessage();
                         String token=responseLogIn.getData();//store it in shared preferences.
                         if(message.equals("login successful"))
                         {
                             Toast.makeText(SignUp.this,"login Successful",Toast.LENGTH_SHORT).show();
                             Intent intent=new Intent(SignUp.this,SignIn.class);
                             startActivity(intent);
                         }
                         else
                             Toast.makeText(SignUp.this,"login unsuccessful",Toast.LENGTH_SHORT).show();



                     }

                     @Override
                     public void onFailure(Call<ResponseLogIn> call, Throwable t) {

                         Log.d("fail","failure");
                     }
                 });
             }
         });


    }

    private boolean validateEmail() {
        String emailInput = email.getText().toString().trim();

        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = name.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            name.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            name.setError("Username too long");
            return false;
        } else {
            name.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

}
