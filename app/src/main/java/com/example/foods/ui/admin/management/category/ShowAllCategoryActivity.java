package com.example.foods.ui.admin.management.category;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foods.R;
import com.example.foods.adapters.ShowCategoryAdapter;
import com.example.foods.dao.CategoryDAO;
import com.example.foods.interfaces.ICategoryClick;
import com.example.foods.models.Categories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowAllCategoryActivity extends AppCompatActivity implements ICategoryClick {

    RecyclerView recycler;
    ShowCategoryAdapter adapter;
    List<Categories> list = null;
    Button goToAdd;
    ImageView img;
    EditText nameImg;
    Button editCategory, removeCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_category);
        goToAdd = findViewById(R.id.btn_go_add_category);
        recycler = findViewById(R.id.list_category_show);
        list = new ArrayList<>();
        list = CategoryDAO.getAllCategories(ShowAllCategoryActivity.this);
        if(list.size() == 0){
            Toast.makeText(ShowAllCategoryActivity.this, "No data to display", Toast.LENGTH_SHORT).show();
        }else {
            adapter = new ShowCategoryAdapter(ShowAllCategoryActivity.this, list, this);
            recycler.setAdapter(adapter);
            recycler.setLayoutManager(new LinearLayoutManager(ShowAllCategoryActivity.this, RecyclerView.VERTICAL, false));
            recycler.setHasFixedSize(true);
        }
        goToAdd.setOnClickListener(v -> startActivity(new Intent(ShowAllCategoryActivity.this, AddCategoryActivity.class)));
    }

    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Override
    public void onClickCategoryItem(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowAllCategoryActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_category, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        Categories categories = list.get(position);
        img = view.findViewById(R.id.image_category_detail);
        nameImg = view.findViewById(R.id.category_detail_name);
        editCategory = view.findViewById(R.id.button_edit_category_detail);
        removeCategory = view.findViewById(R.id.button_delete_category_detail);
        img.setImageBitmap(categories.getCategoryImage());
        nameImg.setText(categories.getCategoryName());
        editCategory.setOnClickListener(v -> {
            categories.setCategoryName(nameImg.getText().toString());
            if(CategoryDAO.updateCategory(ShowAllCategoryActivity.this, categories)){
                Toast.makeText(ShowAllCategoryActivity.this, "Editing category is successful", Toast.LENGTH_SHORT).show();
                list.clear();
                list.addAll(Objects.requireNonNull(CategoryDAO.getAllCategories(ShowAllCategoryActivity.this)));
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }else {
                Toast.makeText(ShowAllCategoryActivity.this, "Editing category is failed", Toast.LENGTH_SHORT).show();
            }
        });

        removeCategory.setOnClickListener(v1 -> {
            AlertDialog.Builder message = new AlertDialog.Builder(ShowAllCategoryActivity.this);
            message.setTitle("Delete message");
            message.setMessage("Do you want to delete "+categories.getCategoryName()+", right?");
            message.setIcon(R.drawable.ic_remove);
            message.setPositiveButton("OK", (dialogInterface, i) -> {
                if(CategoryDAO.deleteCategory(ShowAllCategoryActivity.this, categories.getCategoryId())){
                    Toast.makeText(ShowAllCategoryActivity.this, "Deleted category is successful", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(Objects.requireNonNull(CategoryDAO.getAllCategories(this)));
                    if(list.size() == 0){
                        Toast.makeText(this, "Remove all of categories, Please enter a new category!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ShowAllCategoryActivity.this, AddCategoryActivity.class));
                        finish();
                    }
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else{
                    Toast.makeText(ShowAllCategoryActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            });
            message.setNegativeButton("Cancel", (dialogInterface, i) -> {

            });
            AlertDialog dialogSub = message.create();
            dialogSub.show();
        });
    }
}