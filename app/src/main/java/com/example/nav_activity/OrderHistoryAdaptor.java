package com.example.nav_activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class OrderHistoryHolder extends RecyclerView.ViewHolder {


        public OrderHistoryHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
