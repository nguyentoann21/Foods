package com.example.foods.ui.admin.management.category;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.foods.R;
import com.example.foods.ui.admin.AdminActivity;


public class CategoryManagementActivity extends AppCompatActivity {
    LinearLayout addC, showC, backC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);

        addC = findViewById(R.id.layout_add_category);
        showC = findViewById(R.id.layout_show_category);
        backC = findViewById(R.id.layout_category_back);

        addC.setOnClickListener(v -> startActivity(new Intent(CategoryManagementActivity.this, AddCategoryActivity.class)));
        showC.setOnClickListener(v -> startActivity(new Intent(CategoryManagementActivity.this, ShowAllCategoryActivity.class)));
        backC.setOnClickListener(v -> startActivity(new Intent(CategoryManagementActivity.this, AdminActivity.class)));
    }
}