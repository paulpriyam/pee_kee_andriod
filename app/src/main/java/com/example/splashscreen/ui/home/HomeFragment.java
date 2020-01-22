package com.example.splashscreen.ui.home;

import android.os.Bundle;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.splashscreen.PopularAdapter;
import com.example.splashscreen.Product;
import com.example.splashscreen.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    private List<Product> productList=new ArrayList<>();
    RecyclerView recyclerView;
    private void productdata()
    {
        Product product=new Product("1","aaa","hjkfd",4.00,"https://images.pexels.com/photos/1304540/pexels-photo-1304540.jpeg",12,"1","2",12,480.00);
        productList.add(product);
        product=new Product("2","aaa","hjkfd",4.00,"https://images.pexels.com/photos/1304540/pexels-photo-1304540.jpeg",12,"1","2",12,480.00);
        productList.add(product);
        product=new Product("3","aaa","hjkfd",4.00,"https://images.pexels.com/photos/1672304/pexels-photo-1672304.jpeg",12,"1","2",12,480.00);
        productList.add(product);
        product=new Product("4","aaa","hjkfd",4.00,"https://images.pexels.com/photos/1672304/pexels-photo-1672304.jpeg",12,"1","2",12,480.00);
        productList.add(product);
        product=new Product("5","aaa","hjkfd",4.00,"https://images.pexels.com/photos/1672304/pexels-photo-1672304.jpeg",12,"1","2",12,480.00);
        productList.add(product);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productdata();
        recyclerView = view.findViewById(R.id.mainproductrecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(new PopularAdapter(productList));

    }
    }
