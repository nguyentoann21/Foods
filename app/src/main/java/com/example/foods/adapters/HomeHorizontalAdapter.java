package com.example.foods.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foods.R;
import com.example.foods.interfaces.IUpdateVerticalRecycle;
import com.example.foods.models.HomeHorizontalModel;
import com.example.foods.models.HomeVerticalModel;

import java.util.ArrayList;

public class HomeHorizontalAdapter extends RecyclerView.Adapter<HomeHorizontalAdapter.ViewHolder> {
    IUpdateVerticalRecycle updateVerticalRecycle;
    Activity activity;
    ArrayList<HomeHorizontalModel> list;

    boolean check = true;
    boolean select = true;
    int row_index = -1;

    public HomeHorizontalAdapter(IUpdateVerticalRecycle updateVerticalRecycle, Activity activity, ArrayList<HomeHorizontalModel> list) {
        this.updateVerticalRecycle = updateVerticalRecycle;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_bar_horizontal, parent, false));
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.image.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        if(check) {
            ArrayList<HomeVerticalModel> listVertical = new ArrayList<>();
                listVertical.add(new HomeVerticalModel(R.drawable.pizza_item, "Pizza 1", "70"));
                listVertical.add(new HomeVerticalModel(R.drawable.pizza_item, "Pizza 2", "80"));
                listVertical.add(new HomeVerticalModel(R.drawable.pizza_item, "Pizza 3", "90"));
                listVertical.add(new HomeVerticalModel(R.drawable.pizza_item, "Pizza 4", "100"));
                listVertical.add(new HomeVerticalModel(R.drawable.pizza_item, "Pizza 5", "110"));
            updateVerticalRecycle.callBack(position, listVertical);
            check = false;
        }
        holder.cardView.setOnClickListener(v -> {
            row_index = position;
            notifyDataSetChanged();
            if(position == 0){
                ArrayList<HomeVerticalModel> listVertical = new ArrayList<>();
                listVertical.add(new HomeVerticalModel(R.drawable.pizza_item, "Pizza 1", "70"));
                listVertical.add(new HomeVerticalModel(R.drawable.pizza_item, "Pizza 2", "80"));
                listVertical.add(new HomeVerticalModel(R.drawable.pizza_item, "Pizza 3", "90"));
                listVertical.add(new HomeVerticalModel(R.drawable.pizza_item, "Pizza 4", "100"));
                listVertical.add(new HomeVerticalModel(R.drawable.pizza_item, "Pizza 5", "110"));
                updateVerticalRecycle.callBack(position, listVertical);
            }else if(position == 1){
                ArrayList<HomeVerticalModel> listVertical = new ArrayList<>();
                    listVertical.add(new HomeVerticalModel(R.drawable.hamburger_item, "Hamburger 1", "70"));
                    listVertical.add(new HomeVerticalModel(R.drawable.hamburger_item, "Hamburger 2", "80"));
                    listVertical.add(new HomeVerticalModel(R.drawable.hamburger_item, "Hamburger 3", "90"));
                    listVertical.add(new HomeVerticalModel(R.drawable.hamburger_item, "Hamburger 4", "100"));
                    listVertical.add(new HomeVerticalModel(R.drawable.hamburger_item, "Hamburger 5", "110"));
                updateVerticalRecycle.callBack(position, listVertical);
            }else if(position == 2){
                ArrayList<HomeVerticalModel> listVertical = new ArrayList<>();
                    listVertical.add(new HomeVerticalModel(R.drawable.ramen_item, "Ramen 1", "70"));
                    listVertical.add(new HomeVerticalModel(R.drawable.ramen_item, "Ramen 2", "80"));
                    listVertical.add(new HomeVerticalModel(R.drawable.ramen_item, "Ramen 3", "90"));
                    listVertical.add(new HomeVerticalModel(R.drawable.ramen_item, "Ramen 4", "100"));
                    listVertical.add(new HomeVerticalModel(R.drawable.ramen_item, "Ramen 5", "110"));
                updateVerticalRecycle.callBack(position, listVertical);
            }else if(position == 3) {
                ArrayList<HomeVerticalModel> listVertical = new ArrayList<>();
                    listVertical.add(new HomeVerticalModel(R.drawable.bubble_tea_item, "Bubble Tea 1", "70"));
                    listVertical.add(new HomeVerticalModel(R.drawable.bubble_tea_item, "Bubble Tea 2", "80"));
                    listVertical.add(new HomeVerticalModel(R.drawable.bubble_tea_item, "Bubble Tea 3", "90"));
                    listVertical.add(new HomeVerticalModel(R.drawable.bubble_tea_item, "Bubble Tea 4", "100"));
                    listVertical.add(new HomeVerticalModel(R.drawable.bubble_tea_item, "Bubble Tea 5", "110"));
                updateVerticalRecycle.callBack(position, listVertical);
            }else if(position == 4) {
                ArrayList<HomeVerticalModel> listVertical = new ArrayList<>();
                    listVertical.add(new HomeVerticalModel(R.drawable.coffee, "Coffee 1", "70"));
                    listVertical.add(new HomeVerticalModel(R.drawable.coffee, "Coffee 2", "80"));
                    listVertical.add(new HomeVerticalModel(R.drawable.coffee, "Coffee 3", "90"));
                    listVertical.add(new HomeVerticalModel(R.drawable.coffee, "Coffee 4", "100"));
                    listVertical.add(new HomeVerticalModel(R.drawable.coffee, "Coffee 5", "110"));
                updateVerticalRecycle.callBack(position, listVertical);
            }else if(position == 5) {
                ArrayList<HomeVerticalModel> listVertical = new ArrayList<>();
                    listVertical.add(new HomeVerticalModel(R.drawable.cocktail_item, "Cocktail 1", "70"));
                    listVertical.add(new HomeVerticalModel(R.drawable.cocktail_item, "Cocktail 2", "80"));
                    listVertical.add(new HomeVerticalModel(R.drawable.cocktail_item, "Cocktail 3", "90"));
                    listVertical.add(new HomeVerticalModel(R.drawable.cocktail_item, "Cocktail 4", "100"));
                    listVertical.add(new HomeVerticalModel(R.drawable.cocktail_item, "Cocktail 5", "110"));
                updateVerticalRecycle.callBack(position, listVertical);
            }
        });
        if(select) {
            if (position == 0) {
                holder.cardView.setBackgroundResource(R.drawable.change_background);
                select = false;
            }
        }else{
            if(row_index == position){
                holder.cardView.setBackgroundResource(R.drawable.change_background);
            }else{
                holder.cardView.setBackgroundResource(R.drawable.default_background);
            }
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.img_horizontal);
            name = itemView.findViewById(R.id.txt_horizontal);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
