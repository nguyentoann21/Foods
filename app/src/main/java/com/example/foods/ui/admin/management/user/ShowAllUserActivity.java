package com.example.foods.ui.admin.management.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.foods.R;
import com.example.foods.dao.UserDAO;
import com.example.foods.models.Users;

import java.util.ArrayList;

public class ShowAllUserActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter adapter;
    ArrayList<Users> listUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_user);
        lv = findViewById(R.id.lv_show_all_user);
        listUser = UserDAO.getAllUser(ShowAllUserActivity.this);
        adapter = new ArrayAdapter(ShowAllUserActivity.this, android.R.layout.simple_list_item_1, listUser);
        lv.setAdapter(adapter);
    }
}