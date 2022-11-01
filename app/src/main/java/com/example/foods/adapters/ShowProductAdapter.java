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
import com.example.foods.interfaces.IProductClick;
import com.example.foods.models.Products;
import java.util.List;

public class ShowProductAdapter extends RecyclerView.Adapter<ShowProductAdapter.ViewHolder> {
    Context ctx;
    List<Products> list;
    IProductClick clickProduct;

    public ShowProductAdapter(Context ctx, List<Products> list, IProductClick clickProduct) {
        this.ctx = ctx;
        this.list = list;
        this.clickProduct = clickProduct;
    }

    @NonNull
    @Override
    public ShowProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_single_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ShowProductAdapter.ViewHolder holder, int position) {
        holder._productImage.setImageBitmap(list.get(position).getProductImage());
        holder._productId.setText("ID: "+list.get(position).getProductId());
        holder._productName.setText("Name: "+list.get(position).getProductName());
        holder._quantity.setText("Quantity: "+list.get(position).getQuantity());
        holder._price.setText("Price: "+list.get(position).getPrice());
        holder._type.setText("Type: "+list.get(position).getProductCategory());
        holder._cardItem.setOnClickListener(v -> clickProduct.onClickProductItem(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView _cardItem;
        ImageView _productImage;
        TextView _productId, _productName, _quantity, _price, _type;

        public ViewHolder(@NonNull View inflate) {
            super(inflate);
            _cardItem = inflate.findViewById(R.id.product_list_item);
            _productImage = inflate.findViewById(R.id.product_item_image_show);
            _productId = inflate.findViewById(R.id.txt_product_item_id);
            _productName = inflate.findViewById(R.id.txt_product_item_name);
            _quantity = inflate.findViewById(R.id.txt_product_item_quantity);
            _price = inflate.findViewById(R.id.txt_product_item_price);
            _type = inflate.findViewById(R.id.txt_product_item_type);
        }
    }
}
