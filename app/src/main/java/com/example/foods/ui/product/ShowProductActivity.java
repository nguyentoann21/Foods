package com.example.foods.ui.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.foods.R;
import com.example.foods.adapters.ProductDetailAdapter;
import com.example.foods.models.ProductDetailModel;

import java.util.ArrayList;
import java.util.List;

public class ShowProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ProductDetailModel> list;
    ProductDetailAdapter productDetailAdapter;
    ImageView imageView;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.detail_item);
        imageView = findViewById(R.id.img_list_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        productDetailAdapter = new ProductDetailAdapter(list);
        recyclerView.setAdapter(productDetailAdapter);
        if(type != null && type.equalsIgnoreCase("Pizza") ){
            list.add(new ProductDetailModel(R.drawable.pizza_item, "Pizza 10", "50"));
            list.add(new ProductDetailModel(R.drawable.pizza_item, "Pizza 11", "150"));
            list.add(new ProductDetailModel(R.drawable.pizza_item, "Pizza 12", "250"));
            productDetailAdapter.notifyDataSetChanged();
        }

        if(type != null && type.equalsIgnoreCase("Hamburger") ){
            imageView.setImageResource(R.drawable.hamburger_item);
            list.add(new ProductDetailModel(R.drawable.hamburger_item, "Hamburger 10", "50"));
            list.add(new ProductDetailModel(R.drawable.hamburger_item, "Hamburger 11", "150"));
            list.add(new ProductDetailModel(R.drawable.hamburger_item, "Hamburger 12", "250"));
            productDetailAdapter.notifyDataSetChanged();
        }

        if(type != null && type.equalsIgnoreCase("Ramen") ){
            imageView.setImageResource(R.drawable.ramen_item);
            list.add(new ProductDetailModel(R.drawable.ramen_item, "Ramen 10", "50"));
            list.add(new ProductDetailModel(R.drawable.ramen_item, "Ramen 11", "150"));
            list.add(new ProductDetailModel(R.drawable.ramen_item, "Ramen 12", "250"));
            productDetailAdapter.notifyDataSetChanged();
        }

        if(type != null && type.equalsIgnoreCase("Bubble Tea") ){
            imageView.setImageResource(R.drawable.bubble_tea_item);
            list.add(new ProductDetailModel(R.drawable.bubble_tea_item, "Bubble Tea 10", "50"));
            list.add(new ProductDetailModel(R.drawable.bubble_tea_item, "Bubble Tea 11", "150"));
            list.add(new ProductDetailModel(R.drawable.bubble_tea_item, "Bubble Tea 12", "250"));
            productDetailAdapter.notifyDataSetChanged();
        }

        if(type != null && type.equalsIgnoreCase("Iced Coffee") ){
            imageView.setImageResource(R.drawable.coffee);
            list.add(new ProductDetailModel(R.drawable.coffee, "Coffee 10", "50"));
            list.add(new ProductDetailModel(R.drawable.coffee, "Coffee 11", "150"));
            list.add(new ProductDetailModel(R.drawable.coffee, "Coffee 12", "250"));
            productDetailAdapter.notifyDataSetChanged();
        }

        if(type != null && type.equalsIgnoreCase("Cocktail") ){
            imageView.setImageResource(R.drawable.cocktail_item);
            list.add(new ProductDetailModel(R.drawable.cocktail_item, "Cocktail 10", "50"));
            list.add(new ProductDetailModel(R.drawable.cocktail_item, "Cocktail 11", "150"));
            list.add(new ProductDetailModel(R.drawable.cocktail_item, "Cocktail 12", "250"));
            productDetailAdapter.notifyDataSetChanged();
        }

    }
}