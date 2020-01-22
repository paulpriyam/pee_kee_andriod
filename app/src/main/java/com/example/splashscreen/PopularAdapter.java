package com.example.splashscreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.MyViewHolder> {

    List<Product> products;

    public PopularAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.popular_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

//        holder.productPrice.setText( (int)products.get(position).getProductPrice());
        holder.prouctName.setText(products.get(position).getProductName());
        Glide.with(holder.productPhoto.getContext()).load(products.get(position).getProductImage()).into(holder.productPhoto);
    }

    @Override
    public int getItemCount() {
        return products.size() ;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

      ImageView productPhoto;
        TextView prouctName;
        TextView productPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productPhoto=itemView.findViewById(R.id.product_pic);
//            productPrice=itemView.findViewById(R.id.product_price);
            prouctName=itemView.findViewById(R.id.product_name);
        }
    }
}
