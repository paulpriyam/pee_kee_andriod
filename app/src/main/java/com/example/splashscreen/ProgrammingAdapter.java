package com.example.splashscreen;

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

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {
    private List<Movies> data;
    private ProductCommunication mproductCommunication;

    public ProgrammingAdapter(List<Movies> data, ProductCommunication productCommunication) {
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
        holder.name.setText(String.valueOf(data.get(position).getName()));
        Glide.with(holder.imageView.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)).load(data.get(position).getUrl().getMedium()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        ConstraintLayout constraintLayout;

        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.constraintLayout);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            name = (TextView) itemView.findViewById(R.id.recycle_name);
            price = (TextView) itemView.findViewById(R.id.recycle_price);
            rating = (TextView) itemView.findViewById(R.id.recycle_rating);

        }

    }

    public interface ProductCommunication {
        public void onItemClick(Movies position);
    }
}
