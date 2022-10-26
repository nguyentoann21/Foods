package com.example.foods.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.foods.R;
import com.example.foods.ui.admin.management.category.CategoryManagementActivity;
import com.example.foods.ui.admin.management.product.ProductManagementActivity;
import com.example.foods.ui.admin.management.user.UserManagementActivity;
import com.example.foods.ui.home.HomeActivity;

public class AdminActivity extends AppCompatActivity {

    LinearLayout user, product, category, change, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        user = findViewById(R.id.manage_user);
        product = findViewById(R.id.manage_product);
        category = findViewById(R.id.manage_category);
        change = findViewById(R.id.change_password);
        home = findViewById(R.id.admin_to_home);

        user.setOnClickListener(v -> startActivity(new Intent(AdminActivity.this, UserManagementActivity.class)));
        product.setOnClickListener(v -> startActivity(new Intent(AdminActivity.this, ProductManagementActivity.class)));
        category.setOnClickListener(v -> startActivity(new Intent(AdminActivity.this, CategoryManagementActivity.class)));
        change.setOnClickListener(v -> startActivity(new Intent(AdminActivity.this, UserManagementActivity.class)));
        home.setOnClickListener(v -> startActivity(new Intent(AdminActivity.this, HomeActivity.class)));
    }
}