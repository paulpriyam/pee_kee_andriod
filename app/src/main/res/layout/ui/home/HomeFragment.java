package com.example.nav_activity.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splashscreen.App;
import com.example.splashscreen.MainActivity;
import com.example.splashscreen.PopularAdapter;
import com.example.splashscreen.Product;
import com.example.splashscreen.ProgrammingAdapter;
import com.example.splashscreen.R;
import com.example.splashscreen.RetroInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    private List<Product> productList=new ArrayList<>();
    RecyclerView recyclerView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        recyclerView = view.findViewById(R.id.mainproductrecycler);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
//        recyclerView.setAdapter(new PopularAdapter(productList));

//        App.getRetrofit().create(RetroInterface.class).popularProduct().enqueue(new Callback<List<Product>>() {
//            @Override
//            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
//                if(!response.isSuccessful())
//                {
//                    Log.d("product","response is not comming");
//                    return;
//                }
//                productList=response.body();
//                System.out.println(response.body());
//                Log.d("product","response is comming");
//                 recyclerView=view.findViewById(R.id.recycle_call);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                recyclerView.setAdapter(new ProgrammingAdapter(productList, (ProgrammingAdapter.ProductCommunication) HomeFragment.this));
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Product>> call, Throwable t) {
//
//            }
//        });


    }
    }
