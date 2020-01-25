package com.example.nav_activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class OrderHistoryAdaptor extends RecyclerView.Adapter<OrderHistoryAdaptor.OrderHistoryHolder> {

    List<OrderHistory> mOrderHistories;
    public OrderHistoryAdaptor(List<OrderHistory> orderHistories) {
        this.mOrderHistories=orderHistories;
    }

    @NonNull
    @Override
    public OrderHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.order_history_layout, parent, false);

        return new OrderHistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryHolder holder, int position) {

        holder.orderqty.setText(String.valueOf(mOrderHistories.get(position).getQuantity()));
        Glide.with(holder.orderpic.getContext()).load(mOrderHistories.get(position).getProductImage()).into(holder.orderpic);
        holder.orderPrice.setText(String.valueOf(mOrderHistories.get(position).getTotalPrice()));
        holder.orderdate.setText(mOrderHistories.get(position).getOrderDate());
        holder.productname.setText(mOrderHistories.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        return mOrderHistories.size();
    }

    public class OrderHistoryHolder extends RecyclerView.ViewHolder {

        ImageView orderpic;
        TextView orderPrice;
        TextView orderdate,orderqty,productname;


        public OrderHistoryHolder(@NonNull View itemView) {
            super(itemView);
            orderdate=itemView.findViewById(R.id.dateoforder);
            orderPrice=itemView.findViewById(R.id.cart_layout_price);
            orderpic=itemView.findViewById(R.id.cart_layout_image);
            orderqty=itemView.findViewById(R.id.cart_layout_quantity);
            productname=itemView.findViewById(R.id.cart_layout_name);

        }
    }
}
