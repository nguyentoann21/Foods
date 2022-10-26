package com.example.foods.ui.admin.management.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.foods.R;
import com.example.foods.ui.admin.AdminActivity;
import com.example.foods.ui.admin.management.user.AddUserActivity;
import com.example.foods.ui.admin.management.user.ShowAllUserActivity;
import com.example.foods.ui.admin.management.user.UserManagementActivity;

public class ProductManagementActivity extends AppCompatActivity {

    LinearLayout add, show, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);
        add = findViewById(R.id.layout_add_product);
        show = findViewById(R.id.layout_show_product);
        back = findViewById(R.id.layout_product_back);

        add.setOnClickListener(v -> startActivity(new Intent(ProductManagementActivity.this, AddUserActivity.class)));
        show.setOnClickListener(v -> startActivity(new Intent(ProductManagementActivity.this, ShowAllUserActivity.class)));
        back.setOnClickListener(v -> startActivity(new Intent(ProductManagementActivity.this, AdminActivity.class)));
    }
}