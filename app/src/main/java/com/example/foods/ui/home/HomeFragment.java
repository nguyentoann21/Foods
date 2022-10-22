package com.example.foods.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foods.R;
import com.example.foods.adapters.HomeHorizontalAdapter;
import com.example.foods.adapters.HomeVerticalAdapter;
import com.example.foods.adapters.IUpdateVerticalRecycle;
import com.example.foods.models.HomeHorizontalModel;
import com.example.foods.models.HomeVerticalModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements IUpdateVerticalRecycle {

    RecyclerView homeHorizontalRecycle, homeVerticalRecycle;
    ArrayList<HomeHorizontalModel> homeHorizontalModelList;
    ArrayList<HomeVerticalModel>homeVerticalModelList;
    HomeHorizontalAdapter homeHorizontalAdapter;
    HomeVerticalAdapter homeVerticalAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);

            homeHorizontalRecycle = root.findViewById(R.id.home_recycle_horizontal);
            homeHorizontalModelList = new ArrayList<>();

            //add data static
            homeHorizontalModelList.add(new HomeHorizontalModel(R.drawable.pizza, "Pizza"));
            homeHorizontalModelList.add(new HomeHorizontalModel(R.drawable.hamburger, "Hamburger"));
            homeHorizontalModelList.add(new HomeHorizontalModel(R.drawable.ramen, "Ramen"));
            homeHorizontalModelList.add(new HomeHorizontalModel(R.drawable.bubble_tea, "Bubble Tea"));
            homeHorizontalModelList.add(new HomeHorizontalModel(R.drawable.iced_coffee, "Iced Coffee"));
            homeHorizontalModelList.add(new HomeHorizontalModel(R.drawable.cocktail, "Cocktail"));

            homeHorizontalAdapter = new HomeHorizontalAdapter(this, getActivity(), homeHorizontalModelList);
            homeHorizontalRecycle.setAdapter(homeHorizontalAdapter);

            homeHorizontalRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

            homeHorizontalRecycle.setHasFixedSize(true);
            homeHorizontalRecycle.setNestedScrollingEnabled(false);

            homeVerticalRecycle = root.findViewById(R.id.home_recycle_vertical);
            homeVerticalModelList = new ArrayList<>();
            homeVerticalAdapter = new HomeVerticalAdapter(getActivity(), homeVerticalModelList);
            homeVerticalRecycle.setAdapter(homeVerticalAdapter);

            homeVerticalRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        return root;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void callBack(int position, ArrayList<HomeVerticalModel> list) {
        homeVerticalAdapter = new HomeVerticalAdapter(getContext(), list);
        homeVerticalAdapter.notifyDataSetChanged();
        homeVerticalRecycle.setAdapter(homeVerticalAdapter);
    }
}