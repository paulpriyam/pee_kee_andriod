package com.example.nav_activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import retrofit2.Callback;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {
    private List<Product> data;
    private ProductCommunication mproductCommunication;

    public ProgrammingAdapter(List<Product> data, ProductCommunication productCommunication) {
        this.data = data;
        this.mproductCommunication = productCommunication;
    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycle_view, parent, false);

        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, final int position) {

        // String title=data[position];
        //     holder.name.setText(title);
        holder.name.setText(String.valueOf(data.get(position).getProductName()));
        holder.price.setText(String.valueOf(data.get(position).getPrice()));
        holder.rating.setText(String.valueOf(data.get(position).getProductRating()));
       Glide.with(holder.imageView.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)).load(data.get(position).getProductImage()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity mainActivity=new MainActivity();
//                mainActivity.onItemClick(data.get(position));
                mproductCommunication.onItemClick(data.get(position));
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder {



        ImageView imageView;
        TextView name;
        TextView price;
        TextView rating;


        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            name = (TextView) itemView.findViewById(R.id.recycle_name);
            price = (TextView) itemView.findViewById(R.id.recycle_price);
            rating = (TextView) itemView.findViewById(R.id.recycle_rating);

        }

    }

    public interface ProductCommunication {
         void onItemClick(Product position);
    }
}
