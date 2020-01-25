package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements ProgrammingAdapter.ProductCommunication{


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

                Intent intent=new Intent(SearchActivity.this,SearchProductDetail.class);
                intent.putExtra("searchName",query);
                startActivity(intent);




/*
                Toast.makeText(SearchActivity.this,query,Toast.LENGTH_SHORT).show();

              SearchString searchString=new SearchString(query);


                App.getRetrofit().create(RetroInterface.class).getSearch(searchString).enqueue(new Callback<List<Search>>() {

                    @Override
                    public void onResponse(Call<List<Search>> call, Response<List<Search>> response) {
                        List<Search> searches=response.body();
                        List<Product> products=null;
                        Product p=null;
                        for(Search search:searches){


                            p.setMerchantId(search.getMerchantId());
                            p.setPrice(search.getPrice());
                            p.setProductCategoryId(search.getProductCategoryId());
                            p.setProductDesc(search.getProductDesc());
                            p.setProductId(search.getProductId());
                            p.setProductImage(search.getProductImage());
                            p.setProductName(search.getProductName());
                            p.setProductRating(search.getProductRating());
                            p.setQuantity(search.getQuantity());
                            p.setSellCount(search.getSellCount());

                            products.add(p);





                        }


                        System.out.println(response.body());
                        RecyclerView recyclerView=findViewById(R.id.recycle_call);
                        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                        recyclerView.setAdapter(new ProgrammingAdapter(products,SearchActivity.this));






                    }

                    @Override
                    public void onFailure(Call<List<Search>> call, Throwable t) {
                        Log.d("failure","failure");
                    }
                });



*/



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

                            list.clear();
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

    @Override
    public void onItemClick(Product position) {

        Intent intent=new Intent(SearchActivity.this,ProductDetailActivity.class);
        intent.putExtra("name",position.getProductName());
        intent.putExtra("rating",position.getProductRating());
        intent.putExtra("productId",position.getProductId());
        // intent.putExtra("url",position.getUrl().getMedium());
        startActivity(intent);

    }
}
