package com.example.foods.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foods.R;
import com.example.foods.models.HomeVerticalModel;

import java.util.ArrayList;

public class HomeVerticalAdapter extends RecyclerView.Adapter<HomeVerticalAdapter.ViewHolder> {

    Context ctx;
    ArrayList<HomeVerticalModel> list;

    public HomeVerticalAdapter(Context ctx, ArrayList<HomeVerticalModel> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeVerticalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeVerticalAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_bar_vertical, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeVerticalAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.img_vertical);
            name = itemView.findViewById(R.id.txt_vertical_name);
            price = itemView.findViewById(R.id.txt_vertical_price);

        }
    }
}
