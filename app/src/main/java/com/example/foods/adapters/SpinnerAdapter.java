package com.example.foods.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foods.R;
import com.example.foods.models.Categories;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<Categories> {

    private Context context;
    private int layout;
    private  List<Categories> categories;

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Categories> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.categories = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Categories category = categories.get(position);
        View v = LayoutInflater.from(context).inflate(layout, null);
        TextView name = v.findViewById(R.id.category_display_option);
        name.setText(category.getCategoryName());
        return v;
    }

}
