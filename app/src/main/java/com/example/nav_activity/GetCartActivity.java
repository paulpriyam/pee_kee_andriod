package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.nav_activity.data.entity.CartDetails;
import com.example.nav_activity.data.viewModel.CartDetailsViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetCartActivity extends AppCompatActivity implements GetCartGuestAdaptor.QuantityChange {

    CartDetailsViewModel cartDetailsViewModel;
    GetCartGuestAdaptor getCartGuestAdaptor;
    Button checkout,gotohome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cart);
        checkout=findViewById(R.id.checkout);
        gotohome=findViewById(R.id.homepage);
        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        boolean loginStatus=sharedPreferences.getBoolean("loginStatus",false);
        final String token=sharedPreferences.getString("token","");
        if (loginStatus) {
            App.getRetrofit().create(RetroInterface.class).getCart(token).enqueue(new Callback<ResponseCart>() {
                @Override
                public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {
                    System.out.println("position 2");
                    ResponseCart responseCart=response.body();
                    if(responseCart.getStatus()==1000)
                    {
                        List<Cart> list=responseCart.getData();
                        System.out.println(list.toString());
                        RecyclerView recyclerView=findViewById(R.id.get_cart_recycle);
                        recyclerView.setLayoutManager(new LinearLayoutManager(GetCartActivity.this));
                        recyclerView.setAdapter(new GetCartAdaptor(list,token,GetCartActivity.this));
                    }
                }
                @Override
                public void onFailure(Call<ResponseCart> call, Throwable t) {
                    Log.d("Fail","failure");
                }
            });

        }
        else
        {
            cartDetailsViewModel= ViewModelProviders.of(GetCartActivity.this).get(CartDetailsViewModel.class);
            cartDetailsViewModel.getCartItems().observe(GetCartActivity.this, new Observer<List<CartDetails>>() {
                @Override
                public void onChanged(List<CartDetails> cartDetails) {
                    RecyclerView recyclerView = findViewById(R.id.get_cart_recycle);
                    recyclerView.setLayoutManager(new LinearLayoutManager(GetCartActivity.this));
                    recyclerView.setAdapter(new GetCartGuestAdaptor(cartDetails));
                }
            });
        }
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getRetrofit().create(RetroInterface.class).checkout(token).enqueue(new Callback<ResponseLogIn>() {
                    @Override
                    public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {
                        ResponseLogIn responseLogIn=response.body();
                        if(responseLogIn==null){
                            System.out.println("response checkout null");
                        }
                        if(responseLogIn.getStatus()==1000)
                        {
                            Intent intent=new Intent(GetCartActivity.this,CartCheckOut.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogIn> call, Throwable t) {

                    }
                });
            }
        });
        gotohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GetCartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void qtyChange(int cartId,int qty) {
//        cartDetailsViewModel=ViewModelProviders.of(this).get(CartDetailsViewModel.class);
        cartDetailsViewModel.update(cartId,qty);
        getCartGuestAdaptor.notifyDataSetChanged();


    }

    @Override
    public void remove(CartDetails cartDetails,int position) {
//        cartDetailsViewModel=ViewModelProviders.of(this).get(CartDetailsViewModel.class);
        cartDetailsViewModel.delete(cartDetails);
        getCartGuestAdaptor.notifyItemRemoved(position);
    }



    /*List<CartDetails> items;
    CartDetails cartDetails=items.get(position);
    cartDetails.setQty(cartDetails.getQty()+1);
    items.set(position,cartDetails);
    adapter.notifyDataSetChaged();*/


}