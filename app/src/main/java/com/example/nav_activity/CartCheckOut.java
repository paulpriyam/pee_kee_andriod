package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class CartCheckOut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_check_out);
        final Intent intent=new Intent(CartCheckOut.this,MainActivity.class);
        Thread timer=new Thread()
        {
            public void run()
            {
                try{
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finally {
                    startActivity(intent);
                    finish();
                }
            }


        };
        timer.start();

    }
}
