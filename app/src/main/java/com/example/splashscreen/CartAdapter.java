package com.example.splashscreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyCartViewHolder> {

    List<Cart> mcart;
    CartCommunication cartCommunication;

    public CartAdapter(List<Cart> mcart, CartCommunication cartCommunication) {
        this.mcart = mcart;
        this.cartCommunication = cartCommunication;
    }

    @NonNull
    @Override
    public MyCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.get_cart_layout,parent,false);
        return new MyCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyCartViewHolder holder, final int position) {

        holder.productName.setText(mcart.get(position).getProductName());
        holder.productPrice.setText((int) mcart.get(position).getPrice());
        holder.quantity.setText("1");
        holder.merchantName.setText(mcart.get(position).getMerchantName());
        Glide.with(holder.productImage.getContext()).load(mcart.get(position).getProductImage()).into(holder.productImage);
        Glide.with(holder.addProduct.getContext()).load(R.drawable.ic_add).into(holder.addProduct);
        Glide.with(holder.removeProduct.getContext()).load(R.drawable.ic_remove).into(holder.removeProduct);
        Glide.with(holder.deleteProduct.getContext()).load(R.drawable.ic_delete).into(holder.deleteProduct);

        holder.addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long quantity=cartCommunication.onRemove(mcart.get(position).getQuantity());
                quantity++;
                holder.quantity.setText(String.valueOf(quantity));
                notifyItemChanged(position);
            }
        });
        holder.removeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long quantity=cartCommunication.onRemove(mcart.get(position).getQuantity());
                if(quantity<0)
                {

                }
                quantity--;
                holder.quantity.setText(String.valueOf(quantity));
                notifyItemChanged(position);


            }
        });
        holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              cartCommunication.onDelete(mcart.get(position));
              mcart.remove(position);
              notifyItemRemoved(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mcart.size();
    }


    public class MyCartViewHolder extends RecyclerView.ViewHolder
    {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        TextView merchantName;
        TextView quantity;
        ImageView addProduct;
        ImageView removeProduct;
        ImageView deleteProduct;

        public MyCartViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage=itemView.findViewById(R.id.cart_layout_image);
            productName=itemView.findViewById(R.id.cart_layout_name);
            productPrice=itemView.findViewById(R.id.cart_layout_price);
            merchantName=itemView.findViewById(R.id.cart_layout_merchantName);
            addProduct=itemView.findViewById(R.id.cart_layout_add);
            deleteProduct=itemView.findViewById(R.id.cart_layout_delete);
            removeProduct=itemView.findViewById(R.id.cart_layout_remove);
            quantity=itemView.findViewById(R.id.cart_layout_quantity);
        }
    }

    public interface CartCommunication{
        public void onClick(Cart cart);
        public void onDelete(Cart cart);
        public void onAdd(Cart cart);
        public long onRemove(long quantity);
    }
}
