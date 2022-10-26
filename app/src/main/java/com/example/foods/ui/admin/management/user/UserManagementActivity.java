package com.example.foods.ui.admin.management.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.foods.R;
import com.example.foods.ui.admin.AdminActivity;

public class UserManagementActivity extends AppCompatActivity {

    LinearLayout _add, _show, _back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        _add = findViewById(R.id.layout_add_user);
        _show = findViewById(R.id.layout_show_user);
        _back = findViewById(R.id.layout_back_admin);

        _add.setOnClickListener(v -> startActivity(new Intent(UserManagementActivity.this, AddUserActivity.class)));
        _show.setOnClickListener(v -> startActivity(new Intent(UserManagementActivity.this, ShowAllUserActivity.class)));
        _back.setOnClickListener(v -> startActivity(new Intent(UserManagementActivity.this, AdminActivity.class)));
    }
}