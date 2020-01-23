package com.example.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener ,ProgrammingAdapter.ProductCommunication{


    private AppBarConfiguration mAppBarConfiguration;

    private List<Product> productList=new ArrayList<>();
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



//        RecyclerView recyclerView=findViewById(R.id.mainproductrecycler);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        PopularAdapter popularAdapter=new PopularAdapter(productList);
//        recyclerView.setAdapter(popularAdapter);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
//                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//
//
        ImageView toolbaricon=findViewById(R.id.toolbaricon);
        String url="https://cdn4.iconfinder.com/data/icons/passions/154/martini-alcohol-drink-party-app-passion-512.png";
        Glide.with(getBaseContext()).load(url).into(toolbaricon);

//        if(savedInstanceState==null) {
//            navigationView.setCheckedItem(R.id.nav_account);
//        }

//

        App.getRetrofit().create(RetroInterface.class).popularProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(!response.isSuccessful())
                {
                    Log.d("product","response is not comming");
                    return;
                }
                productList=response.body();
                System.out.println(response.body());
                Log.d("product","response is comming");
                RecyclerView recyclerView=findViewById(R.id.recycle_call);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(new ProgrammingAdapter(productList,MainActivity.this));

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

                Log.d("fail","failure"+t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.toolbatnotification)
        {

            Intent intent=new Intent(MainActivity.this,SearchActivity.class);
            startActivity(intent);
        }

        else if(id==R.id.toolbarcart)
        {

//            Intent intent=new Intent(MainActivity.this,GetCartActivity.class);
//            startActivity(intent);
        }



        return true;
    }




    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id=menuItem.getItemId();
        if(id==R.id.nav_account)
        {
            Intent intent=new Intent(MainActivity.this,MyProfileActivity.class);
            startActivity(intent);
        }
        if(id==R.id.nav_cart)
        {
            Intent intent=new Intent(MainActivity.this,GetCartActivity.class);
            startActivity(intent);
        }
        if(id==R.id.nav_order)
        {
            Intent intent=new Intent(MainActivity.this,GetCartActivity.class);
            startActivity(intent);
        }
        if(id==R.id.nav_logout)
        {
            Toast.makeText(this,"logout",Toast.LENGTH_SHORT).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }


    // on clicking the product ->go to product detail page
    @Override
    public void onItemClick(Product position) {
        System.out.println(position.getProductName());
        Intent intent=new Intent(MainActivity.this,ProductDetailActivity.class);
        intent.putExtra("name",position.getProductName());
        intent.putExtra("rating",position.getProductRating());
        intent.putExtra("productId",position.getProductId());
        startActivity(intent);
    }
}
