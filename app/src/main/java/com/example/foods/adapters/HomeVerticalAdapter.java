package com.example.foods.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foods.R;
import com.example.foods.models.HomeVerticalModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class HomeVerticalAdapter extends RecyclerView.Adapter<HomeVerticalAdapter.ViewHolder> {

    private BottomSheetDialog bottomSheetDialog;
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

        final int mImage = list.get(position).getImage();
        final String mName = list.get(position).getName();
        final String mPrice = list.get(position).getPrice();

        holder.image.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(ctx, R.style.BottomSheetTheme);
                View sheetView = LayoutInflater.from(ctx).inflate(R.layout.bottom_sheet_layout, null);
                sheetView.findViewById(R.id.bottom_att_to_cart).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ctx, "Added to cart", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });

                ImageView bottomImg = sheetView.findViewById(R.id.img_show_bottom);
                TextView bottomName = sheetView.findViewById(R.id.bottom_item_name);
                TextView bottomPrice = sheetView.findViewById(R.id.bottom_item_price);

                bottomName.setText(mName);
                bottomPrice.setText(mPrice);

                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
            }
        });
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
