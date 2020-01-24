package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_cart_layout);
        Button add=findViewById(R.id.cart_layout_add);
        Button delete=findViewById(R.id.cart_layout_delete);
//       add.setLayoutParams (new LayoutParams(50, LayoutParams.WRAP_CONTENT));
        TextView productName=findViewById(R.id.cart_layout_name);
        TextView producrPrice=findViewById(R.id.cart_layout_price);
        final TextView productQuantity=findViewById(R.id.cart_layout_quantity);
        TextView merchantName=findViewById(R.id.cart_layout_merchantName);


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

        delete.setOnClickListener(new View.OnClickListener() {
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
}
