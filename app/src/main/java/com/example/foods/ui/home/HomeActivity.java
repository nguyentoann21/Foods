package com.example.foods.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import com.example.foods.R;
import com.example.foods.ui.cart.CartFragment;
import com.example.foods.ui.favorite.FavoriteFragment;
import com.example.foods.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class HomeActivity extends AppCompatActivity {

    BottomNavigationView _bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        replaceFragment(new HomeFragment());
        _bottomNavigationView = findViewById(R.id.nav_bar);
        _bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.nav_profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.nav_favorite:
                    replaceFragment(new FavoriteFragment());
                    break;
                case R.id.nav_cart:
                    replaceFragment(new CartFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_content_navigation, fragment);
        fragmentTransaction.commit();
    }

}