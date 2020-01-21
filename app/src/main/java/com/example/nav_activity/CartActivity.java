package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class CartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        TextView price=findViewById(R.id.cartprice);
        Button checkout=findViewById(R.id.cartcheckoutbutton);
        TextView address=findViewById(R.id.cartaddress);
     /*   RecyclerView recyclerView=findViewById(R.id.cart_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(CartActivity.this,2));
        recyclerView.setAdapter(new CartAdapter());
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

      */
    }
}
