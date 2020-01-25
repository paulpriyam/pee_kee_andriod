package com.example.nav_activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductDetailAdaptor extends RecyclerView.Adapter<ProductDetailAdaptor.ProductViewHolder>{

    List<Merchant> merchants;
    public  static int lastSelectedPosition = 0;


    public int getLastSelectedPosition(){
        return lastSelectedPosition;
    }


    public ProductDetailAdaptor() {
    }

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
        holder.merchant_name.setText(String.valueOf(merchants.get(position).getMerchantName()));
        holder.merchant_rating.setText(String.valueOf(merchants.get(position).getMerchantRating()));
        holder.merchant_price.setText(String.valueOf(merchants.get(position).getPrice()));
        holder.quantity.setText(String.valueOf(merchants.get(position).getQuantity()));

        //since only one radio button is allowed to be selected,
        // this condition un-checks previous selections

        holder.selectionstate.setChecked(lastSelectedPosition == position);


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
       public RadioButton selectionstate;




        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);


            merchant_name=(TextView) itemView.findViewById(R.id.merchantname);
            merchant_rating=(TextView)itemView.findViewById(R.id.merchantrating);
            merchant_price=(TextView)itemView.findViewById(R.id.merchant_price);
            quantity=(TextView)itemView.findViewById(R.id.merchantitemcount);
            selectionstate = (RadioButton) itemView.findViewById(R.id.offer_select);

            selectionstate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    lastSelectedPosition = getAdapterPosition();
                    Log.d("position ", String.valueOf(lastSelectedPosition));
                    notifyDataSetChanged();



                }
            });

        }
    }

    public interface MerchantCommunication{

        //public void onAddClick(Merchant position);
    }



}
