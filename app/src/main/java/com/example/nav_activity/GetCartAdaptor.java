package com.example.nav_activity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nav_activity.AddCartDetails;
import com.example.nav_activity.Cart;
import com.example.nav_activity.R;
import com.example.nav_activity.ResponseLogIn;
import com.example.nav_activity.data.entity.CartDetails;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class GetCartAdaptor extends RecyclerView.Adapter<GetCartAdaptor.GetCartHolder> {
    private Context context;
    List<Cart> mCart;
    String token;
    public GetCartAdaptor(List<Cart> carts, String token, Context context) {
        this.mCart=carts;
        this.token=token;
        this.context=context;
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
        holder.merchantName.setText(String.valueOf(mCart.get(position).getMerchantName()));
        holder.productName.setText(String.valueOf(mCart.get(position).getProductName()));
        Glide.with(holder.productImage.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)).load(mCart.get(position).getProductImage()).into(holder.productImage);
        holder.quantity.setText(String.valueOf(mCart.get(position).getQuantity()));
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCartDetails addCartDetails=new AddCartDetails();
                addCartDetails.setQuantity(mCart.get(position).getQuantity()+1);
                addCartDetails.setMerchantId(mCart.get(position).getMerchantId());
                addCartDetails.setProductId(mCart.get(position).getProductId());
                addCartDetails.setToken(token);
                App.getRetrofit().create(RetroInterface.class).updateCartQuantity(addCartDetails).enqueue(new Callback<ResponseLogIn>() {
                    @Override
                    public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {
                        ((Activity)context).finish();
                        context.startActivity(new Intent(context, GetCartActivity.class));
                    }
                    @Override
                    public void onFailure(Call<ResponseLogIn> call, Throwable t) {
                    }
                });
            }
        });
        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long x=mCart.get(position).getQuantity()-1;
                if(x==0)
                {
                    AddCartDetails addCartDetails=new AddCartDetails();
                    addCartDetails.setQuantity(mCart.get(position).getQuantity());
                    addCartDetails.setMerchantId(mCart.get(position).getMerchantId());
                    addCartDetails.setProductId(mCart.get(position).getProductId());
                    addCartDetails.setToken(token);
                    App.getRetrofit().create(RetroInterface.class).deleteCartData(addCartDetails).enqueue(new Callback<ResponseLogIn>() {
                        @Override
                        public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {
                            ((Activity)context).finish();
                            context.startActivity(new Intent(context, GetCartActivity.class));
                        }
                        @Override
                        public void onFailure(Call<ResponseLogIn> call, Throwable t) {
                        }
                    });
                }
                else
                {
                    AddCartDetails addCartDetails=new AddCartDetails();
                    addCartDetails.setQuantity(mCart.get(position).getQuantity()-1);
                    addCartDetails.setMerchantId(mCart.get(position).getMerchantId());
                    addCartDetails.setProductId(mCart.get(position).getProductId());
                    addCartDetails.setToken(token);
                    App.getRetrofit().create(RetroInterface.class).updateCartQuantity(addCartDetails).enqueue(new Callback<ResponseLogIn>() {
                        @Override
                        public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {
                            ((Activity)context).finish();
                            context.startActivity(new Intent(context, GetCartActivity.class));
                        }
                        @Override
                        public void onFailure(Call<ResponseLogIn> call, Throwable t) {
                        }
                    });
                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCartDetails addCartDetails=new AddCartDetails();
                addCartDetails.setQuantity(mCart.get(position).getQuantity());
                addCartDetails.setMerchantId(mCart.get(position).getMerchantId());
                addCartDetails.setProductId(mCart.get(position).getProductId());
                addCartDetails.setToken(token);
                App.getRetrofit().create(RetroInterface.class).deleteCartData(addCartDetails).enqueue(new Callback<ResponseLogIn>() {
                    @Override
                    public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {
                        ((Activity)context).finish();
                        context.startActivity(new Intent(context, GetCartActivity.class));
                    }
                    @Override
                    public void onFailure(Call<ResponseLogIn> call, Throwable t) {
                    }
                });
            }
        });
    }
    @Override
    public int getItemCount() {
        return mCart.size();
    }
    public class GetCartHolder extends RecyclerView.ViewHolder{
        Button increase;
        Button decrease;
        Button delete;
        TextView quantity,price,merchantName,productName;
        ImageView productImage;
        public GetCartHolder(@NonNull View itemView) {
            super(itemView);
            delete=itemView.findViewById(R.id.delete);
            increase=itemView.findViewById(R.id.increase);
            decrease=itemView.findViewById(R.id.decrease);
            price=itemView.findViewById(R.id.cart_layout_price);
            productImage=itemView.findViewById(R.id.cart_layout_image);
            merchantName=itemView.findViewById(R.id.cart_layout_merchantName);
            productName=itemView.findViewById(R.id.cart_layout_name);
            quantity=itemView.findViewById(R.id.cart_layout_quantity);
        }
    }
}