package com.example.foods.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foods.R;
import com.example.foods.models.ProductDetailModel;


import java.util.List;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.ViewHolder> {

    List<ProductDetailModel> list;

    public ProductDetailAdapter(List<ProductDetailModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageDetail.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageDetail;
        TextView name, price;

        public ViewHolder(View itemView) {
            super(itemView);
            imageDetail = itemView.findViewById(R.id.img_detail);
            name = itemView.findViewById(R.id.detail_item_name);
            price = itemView.findViewById(R.id.detail_item_price);
        }
    }
}
