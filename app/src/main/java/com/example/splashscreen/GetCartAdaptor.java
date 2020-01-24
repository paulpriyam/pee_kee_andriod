package com.example.splashscreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GetCartAdaptor extends RecyclerView.Adapter<GetCartAdaptor.GetCartHolder> {

List<Cart> mCart;
//Cartcommunication cartcommunication;
    public GetCartAdaptor(List<Cart> carts) {
        this.mCart=carts;
//        this.cartcommunication=cartcommunication;
    }

    @NonNull
    @Override
    public GetCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.get_cart_layout, parent, false);
        return new GetCartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetCartHolder holder, final int position) {

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cartcommunication.onClick(mCart.get(position));
//            }
//        });
        holder.productName.setText(mCart.get(position).getProductName());
        holder.productPrice.setText((int) mCart.get(position).getPrice());
        Glide.with(holder.productImage.getContext()).load(mCart.get(position).getProductImage()).into(holder.productImage);

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class GetCartHolder extends RecyclerView.ViewHolder{

        TextView productName;
        TextView productPrice;
        TextView productQuantity;
        Button addProduct;
        Button removeProduct;
        Button deleteProduct;
        ImageView productImage;
        public GetCartHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.cart_layout_image);
            productName=itemView.findViewById(R.id.cart_layout_name);
            productPrice=itemView.findViewById(R.id.cart_layout_price);
            productQuantity=itemView.findViewById(R.id.cart_layout_quantity);
            addProduct=itemView.findViewById(R.id.cart_layout_add);
            removeProduct=itemView.findViewById(R.id.cart_layout_remove);
            deleteProduct=itemView.findViewById(R.id.cart_layout_delete);


        }
    }

//    public interface Cartcommunication
//    {
//        void onClick(Cart cart);
//    }


}
