package com.example.nav_activity;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {


    static Retrofit retrofit;
    static Retrofit getRetrofit(){
        if(retrofit==null){
            OkHttpClient client = new OkHttpClient.Builder().build();
            retrofit=new Retrofit.Builder().baseUrl("http://10.177.68.5:8090").addConverterFactory(GsonConverterFactory.create()).client(client).build();
        }
        return retrofit;
    }

}
