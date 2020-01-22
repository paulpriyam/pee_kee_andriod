package com.example.splashscreen;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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

        list.add("monday");
        list.add("tuesday");
        list.add("wednesday");
        list.add("thursday");
        list.add("friday");
        list.add("saturday");
        list.add("sunday");

        adapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,list);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }
}
