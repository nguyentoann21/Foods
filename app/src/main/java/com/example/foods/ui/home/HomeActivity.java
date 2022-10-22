package com.example.foods.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.foods.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {

    BottomNavigationView _bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        _bottomNavigationView = findViewById(R.id.nav_bar);
        _bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_home:
                    Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_profile:
                    Toast.makeText(HomeActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_favorite:
                    Toast.makeText(HomeActivity.this, "Favorite", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_cart:
                    Toast.makeText(HomeActivity.this, "Cart", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        });
    }
}