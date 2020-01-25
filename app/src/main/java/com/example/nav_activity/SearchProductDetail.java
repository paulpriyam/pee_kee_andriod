package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProductDetail extends AppCompatActivity implements ProgrammingAdapter.ProductCommunication{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product_detail);


        String search=getIntent().getStringExtra("searchName");
        SearchString searchString=new SearchString(search);
        Log.d("search detail",search);

        App.getRetrofit().create(RetroInterface.class).getSearch(searchString).enqueue(new Callback<List<Search>>() {
            @Override
            public void onResponse(Call<List<Search>> call, Response<List<Search>> response) {


                List<Search> searches=response.body();
                List<Product> products=new ArrayList<Product>();
                Product p;
                Log.d("Retroresponse", String.valueOf(searches.hashCode()));
                for(Search search:searches){


                    p=new Product();

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
                RecyclerView recyclerView=findViewById(R.id.search_recycle);
                recyclerView.setLayoutManager(new LinearLayoutManager(SearchProductDetail.this));
                recyclerView.setAdapter(new ProgrammingAdapter(products,SearchProductDetail.this));




            }

            @Override
            public void onFailure(Call<List<Search>> call, Throwable t) {
Log.d("Fail","failure");
            }
        });

    }

    @Override
    public void onItemClick(Product position) {
        Intent intent=new Intent(SearchProductDetail.this,ProductDetailActivity.class);
        intent.putExtra("name",position.getProductName());
        intent.putExtra("rating",position.getProductRating());
        intent.putExtra("productId",position.getProductId());
        intent.putExtra("image",position.getProductImage());

        startActivity(intent);


    }
}
