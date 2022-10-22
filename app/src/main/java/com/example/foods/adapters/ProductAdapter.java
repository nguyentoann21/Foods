package com.example.foods.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foods.R;
import com.example.foods.models.ProductModel;
import com.example.foods.ui.product.ShowProductActivity;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context ctx;
    List<ProductModel> list;

    public ProductAdapter(Context ctx, List<ProductModel> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productImage.setImageResource(list.get(position).getImage());
        holder.productName.setText(list.get(position).getName());
        holder.productImage.setOnClickListener(v -> {
            Intent intent = new Intent(ctx, ShowProductActivity.class);
            intent.putExtra("type", list.get(position).getType());
            ctx.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.img_product_menu);
            productName = itemView.findViewById(R.id.txt_product_item);
        }
    }
}
