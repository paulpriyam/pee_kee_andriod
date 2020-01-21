package com.example.nav_activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductDetailAdaptor extends RecyclerView.Adapter<ProductDetailAdaptor.ProductViewHolder>{

    List<Merchant> merchants;

    public ProductDetailAdaptor(List<Merchant> merchants) {
        this.merchants = merchants;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.merchant_layout, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.merchant_name.setText(String.valueOf(merchants.get(position).getMerchant_name()));
        holder.merchant_rating.setText(String.valueOf(merchants.get(position).getMerchant_rating()));
        holder.merchant_price.setText(String.valueOf(merchants.get(position).getPrice()));
        holder.quantity.setText(String.valueOf(merchants.get(position).getQuantity()));

    }

    @Override
    public int getItemCount() {
        return merchants.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView merchant_name;
        TextView merchant_rating;
        TextView merchant_price;
        TextView quantity;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            merchant_name=(TextView) itemView.findViewById(R.id.merchantname);
            merchant_rating=(TextView)itemView.findViewById(R.id.merchantrating);
            merchant_price=(TextView)itemView.findViewById(R.id.merchant_price);
            quantity=(TextView)itemView.findViewById(R.id.merchantitemcount);

        }
    }



}
