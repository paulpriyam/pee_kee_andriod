package com.example.nav_activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nav_activity.data.entity.CartDetails;

import java.util.List;

public class GetCartGuestAdaptor extends RecyclerView.Adapter<GetCartGuestAdaptor.GetCartGuestHolder> {


    List<CartDetails> mCart;
    public GetCartGuestAdaptor(List<CartDetails> cartDetails) {
        this.mCart = cartDetails;
    }

    @NonNull
    @Override
    public GetCartGuestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.get_cart_layout, parent, false);


        return new GetCartGuestAdaptor.GetCartGuestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GetCartGuestHolder holder, final int position) {
        holder.productName.setText(mCart.get(position).getProductName());
        holder.price.setText(String.valueOf(mCart.get(position).getPrice()));
        holder.quantity.setText(String.valueOf(mCart.get(position).getQuantity()));
       holder.merchantName.setText(String.valueOf(mCart.get(position).getMerchantName()));
        Glide.with(holder.productImage.getContext()).load(mCart.get(position).getProductImage()).into(holder.productImage);
        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetCartActivity getCartActivity=new GetCartActivity();
                mCart.get(position).setQuantity(mCart.get(position).getQuantity()+1);
                getCartActivity.qtyChange(mCart.get(position).getCartId(),mCart.get(position).getQuantity());
            }
        });
        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((mCart.get(position).getQuantity()-1)!=0) {
                    GetCartActivity getCartActivity=new GetCartActivity();
                    mCart.get(position).setQuantity(mCart.get(position).getQuantity()-1);
                    getCartActivity.qtyChange(mCart.get(position).getCartId(),mCart.get(position).getQuantity());
                }
                else
                {
                    GetCartActivity getCartActivity=new GetCartActivity();
                    getCartActivity.remove(mCart.get(position),position);
                    //also remove from view
                }
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetCartActivity getCartActivity=new GetCartActivity();
                getCartActivity.remove(mCart.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCart.size();
    }
    public class GetCartGuestHolder extends RecyclerView.ViewHolder{


        TextView quantity,price,merchantName,productName;
        ImageView productImage;
        Button plusButton,minusButton,deleteButton;
        public GetCartGuestHolder(@NonNull View itemView) {
            super(itemView);
            price=itemView.findViewById(R.id.cart_layout_price);
            productImage=itemView.findViewById(R.id.cart_layout_image);
           merchantName=itemView.findViewById(R.id.cart_layout_merchantName);
            productName=itemView.findViewById(R.id.cart_layout_name);
            quantity=itemView.findViewById(R.id.cart_layout_quantity);
            plusButton=itemView.findViewById(R.id.increase);
            minusButton=itemView.findViewById(R.id.decrease);
            deleteButton=itemView.findViewById(R.id.delete);

        }
    }
    public interface QuantityChange{
        public void qtyChange(int cartId,int qty);
        public void remove(CartDetails cartDetails,int position);
    }
}


