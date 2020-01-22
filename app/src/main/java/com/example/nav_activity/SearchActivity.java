package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {


    SearchView searchView;
    ListView listView;

    ArrayList<String> list;
    ArrayAdapter<String > adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        searchView=findViewById(R.id.searchView);
        listView=findViewById(R.id.listView);

        list=new ArrayList<String>();





        adapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,list);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {

                SearchString searchString=new SearchString(newText);


                App.getRetrofit().create(RetroInterface.class).getSearch(searchString).enqueue(new Callback<List<Search>>() {






                    @Override
                    public void onResponse(Call<List<Search>> call, Response<List<Search>> response) {
                       List<Search> searches=response.body();
                       List<String> list1=new ArrayList<String>();

                        Log.d("inResponse","response");

                        for(Search search:searches)
                        {
                            list1.add(search.getProductName());
                        }

                        list.addAll(list1);
                        adapter.notifyDataSetChanged();









                    }

                    @Override
                    public void onFailure(Call<List<Search>> call, Throwable t) {
                        Log.d("failure","failure");
                    }
                });



return false;

            }
        });


    }
}
