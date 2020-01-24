package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartCommunication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ImageView add=findViewById(R.id.cart_layout_add);
        ImageView remove=findViewById(R.id.cart_layout_remove);
        ImageView delete=findViewById(R.id.cart_layout_delete);
        TextView productName=findViewById(R.id.cart_layout_name);
        TextView producrPrice=findViewById(R.id.cart_layout_price);
        final TextView productQuantity=findViewById(R.id.cart_layout_quantity);
        TextView merchantName=findViewById(R.id.cart_layout_merchantName);

        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        String token=sharedPreferences.getString("token","");


        RecyclerView recyclerView=findViewById(R.id.cart_recycleView);

        App.getRetrofit().create(RetroInterface.class).getCart(token).enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                List<Cart> list=response.body();
                RecyclerView recyclerView=findViewById(R.id.get_cart_recycle);
                recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));
                recyclerView.setAdapter(new GetCartAdaptor(list));

            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });



        productQuantity.setText("1");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity=Integer.parseInt(productQuantity.getText().toString());
                quantity++;
                String value=String.valueOf(quantity);
                productQuantity.setText(value);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity=Integer.parseInt(productQuantity.getText().toString());
                if(quantity<=0)
                {
                    Toast.makeText(CartActivity.this,"minimum 1 item need to be selected",Toast.LENGTH_LONG).show();
                }
                else
                {
                    quantity--;
                    String value=String.valueOf(quantity);
                    productQuantity.setText(value);
                }

            }
        });




    }


    @Override
    public void onClick(Cart cart) {

    }

    @Override
    public void onDelete(Cart cart) {

    }

    @Override
    public void onAdd(Cart cart) {

    }

    @Override
    public long onRemove(long quantity) {
        return 1;

    }
}
