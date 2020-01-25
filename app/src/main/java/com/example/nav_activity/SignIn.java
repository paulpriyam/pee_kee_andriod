package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nav_activity.data.entity.CartDetails;
import com.example.nav_activity.data.viewModel.CartDetailsViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {

    SharedPreferences loginPreferences = getSharedPreferences("login",MODE_PRIVATE);
    SharedPreferences.Editor editor=loginPreferences.edit();
    CartDetailsViewModel cartDetailsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        final TextView textView=findViewById(R.id.testText);




        LoginPost loginPost=new LoginPost("abc@gmail.com","hello",0,null);

        App.getRetrofit().create(RetroInterface.class).login(loginPost).enqueue(new Callback<ResponseLogIn>() {
            @Override
            public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {

                final ResponseLogIn responseLogIn=response.body();
                if(responseLogIn.getStatus()==1000){
                    final String token=responseLogIn.getData();
                    editor.putString("token",token);
                    editor.putBoolean("loginStatus",true);
                    editor.commit();
                    cartDetailsViewModel= ViewModelProviders.of(SignIn.this).get(CartDetailsViewModel.class);
                    if(cartDetailsViewModel.getCartItems()!=null){
                        LiveData<List<CartDetails>> cartDetails=cartDetailsViewModel.getCartItems();
                        cartDetailsViewModel.getCartItems().observe(SignIn.this, new Observer<List<CartDetails>>() {
                            @Override
                            public void onChanged(List<CartDetails> cartDetails) {
                                for(int i=0;i<cartDetails.size();i++){
                                    AddCartDetails addCartDetails=new AddCartDetails(token,cartDetails.get(i).getProductId(),cartDetails.get(i).getMerchantId(),cartDetails.get(i).getQuantity());
                                    App.getRetrofit().create(RetroInterface.class).addToCart(addCartDetails).enqueue(new Callback<ResponseLogIn>() {
                                        @Override
                                        public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {
                                            ResponseLogIn responseLogInCart=response.body();
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseLogIn> call, Throwable t) {

                                        }
                                    });
                                }
                            }
                        });
                    }
                    Intent intent=new Intent(SignIn.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(SignIn.this,responseLogIn.getMessage(),Toast.LENGTH_LONG).show();


                }




              //  textView.setText(responseLogIn.getData());

            }

            @Override
            public void onFailure(Call<ResponseLogIn> call, Throwable t) {
                Log.d("Fail","failed to connect");
            }
        });



    }
}
