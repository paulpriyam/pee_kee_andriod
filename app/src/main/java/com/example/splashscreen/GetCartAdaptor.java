package com.example.splashscreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GetCartAdaptor extends RecyclerView.Adapter<GetCartAdaptor.GetCartHolder> {

List<Cart> mCart;
    public GetCartAdaptor(List<Cart> carts) {
        this.mCart=carts;

    }

    @NonNull
    @Override
    public GetCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.get_cart_layout, parent, false);


        return new GetCartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetCartHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class GetCartHolder extends RecyclerView.ViewHolder{

        public GetCartHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
