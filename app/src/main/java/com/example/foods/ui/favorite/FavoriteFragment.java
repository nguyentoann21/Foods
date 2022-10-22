package com.example.foods.ui.favorite;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foods.R;
import com.example.foods.adapters.ProductAdapter;
import com.example.foods.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    RecyclerView recyclerView;
    List<ProductModel> productModelList;
    ProductAdapter productAdapter;

    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);

        recyclerView = root.findViewById(R.id.product_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productModelList = new ArrayList<>();
        productModelList.add(new ProductModel(R.drawable.pizza_item, "Pizza", "Pizza"));
        productModelList.add(new ProductModel(R.drawable.hamburger_item, "Hamburger", "Hamburger"));
        productModelList.add(new ProductModel(R.drawable.ramen_item, "Ramen", "Ramen"));
        productModelList.add(new ProductModel(R.drawable.bubble_tea_item, "Bubble Tea", "Bubble Tea"));
        productModelList.add(new ProductModel(R.drawable.coffee, "Coffee", "Iced Coffee"));
        productModelList.add(new ProductModel(R.drawable.cocktail_item, "Cocktail", "Cocktail"));

        productAdapter = new ProductAdapter(getContext(), productModelList);
        recyclerView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
        return root;
    }
}