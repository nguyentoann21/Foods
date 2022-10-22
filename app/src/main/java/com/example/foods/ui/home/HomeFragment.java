package com.example.foods.ui.home;

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
import com.example.foods.models.HomeHorizontalModel;
import com.example.foods.models.HomeVerticalModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    RecyclerView homeHorizontalRecycle, homeVerticalRecycle;
    List<HomeHorizontalModel> homeHorizontalModelList;
    List<HomeVerticalModel>homeVerticalModelList;
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

            homeHorizontalAdapter = new HomeHorizontalAdapter(getActivity(), homeHorizontalModelList);
            homeHorizontalRecycle.setAdapter(homeHorizontalAdapter);

            homeHorizontalRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

            homeHorizontalRecycle.setHasFixedSize(true);
            homeHorizontalRecycle.setNestedScrollingEnabled(false);




            homeVerticalRecycle = root.findViewById(R.id.home_recycle_vertical);
            homeVerticalModelList = new ArrayList<>();

            //add data static
            homeVerticalModelList.add(new HomeVerticalModel(R.drawable.pizza_item, "Pizza", "100"));
            homeVerticalModelList.add(new HomeVerticalModel(R.drawable.hamburger_item, "Hamburger","120"));
            homeVerticalModelList.add(new HomeVerticalModel(R.drawable.ramen_item, "Ramen", "80"));
            homeVerticalModelList.add(new HomeVerticalModel(R.drawable.bubble_tea_item, "Bubble Tea", "50"));
            homeVerticalModelList.add(new HomeVerticalModel(R.drawable.coffee, "Iced Coffee", "55"));
            homeVerticalModelList.add(new HomeVerticalModel(R.drawable.cocktail_item, "Cocktail","60"));

            homeVerticalAdapter = new HomeVerticalAdapter(getActivity(), homeVerticalModelList);
            homeVerticalRecycle.setAdapter(homeVerticalAdapter);

            homeVerticalRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

            homeVerticalRecycle.setHasFixedSize(true);
            homeVerticalRecycle.setNestedScrollingEnabled(false);

        return root;
    }
}