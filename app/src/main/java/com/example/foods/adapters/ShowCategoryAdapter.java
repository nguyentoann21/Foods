package com.example.foods.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foods.R;
import com.example.foods.interfaces.ICategoryClick;
import com.example.foods.models.Categories;
import java.util.List;

public class ShowCategoryAdapter extends RecyclerView.Adapter<ShowCategoryAdapter.ViewHolder> {
    Context ctx;
    List<Categories> list;
    ICategoryClick clickItem;

    public ShowCategoryAdapter(Context ctx, List<Categories> list, ICategoryClick clickItem) {
        this.ctx = ctx;
        this.list = list;
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public ShowCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_single_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ShowCategoryAdapter.ViewHolder holder, int position) {
        holder._categoryImage.setImageBitmap(list.get(position).getCategoryImage());
        holder._categoryId.setText("ID: "+list.get(position).getCategoryId());
        holder._categoryName.setText("Name: "+list.get(position).getCategoryName());
        holder._cardView.setOnClickListener(v -> clickItem.onClickCategoryItem(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView _cardView;
        ImageView _categoryImage;
        TextView _categoryId, _categoryName;

        public ViewHolder(@NonNull View inflate) {
            super(inflate);
            _cardView = inflate.findViewById(R.id.category_list_item);
            _categoryImage = inflate.findViewById(R.id.category_item_image_show);
            _categoryId = inflate.findViewById(R.id.txt_category_item_id);
            _categoryName = inflate.findViewById(R.id.txt_category_item_name);
        }
    }
}
