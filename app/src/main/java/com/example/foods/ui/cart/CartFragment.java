package com.example.foods.ui.cart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foods.R;
import com.example.foods.adapters.CartAdapter;
import com.example.foods.models.CartModel;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    List<CartModel> list;
    CartAdapter cartAdapter;
    RecyclerView recyclerView;

    public CartFragment(){}

    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = root.findViewById(R.id.cart_show);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        list.add(new CartModel(R.drawable.pizza_item, "Pizza 1", "50"));
        list.add(new CartModel(R.drawable.hamburger_item, "Hamburger 1", "60"));
        list.add(new CartModel(R.drawable.bubble_tea_item, "Bubble Tea 1", "70"));
        list.add(new CartModel(R.drawable.coffee, "Coffee 1", "150"));
        list.add(new CartModel(R.drawable.ramen_item, "Ramen 1", "150"));
        cartAdapter = new CartAdapter(list);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
        return root;
    }
}