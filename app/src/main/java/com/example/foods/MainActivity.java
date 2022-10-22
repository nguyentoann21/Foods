package com.example.foods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.ads.mediationtestsuite.activities.HomeActivity;


public class MainActivity extends AppCompatActivity {
    TextView txtStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtStart = findViewById(R.id.txt_start);
        txtStart.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HomeActivity.class)));
    }
}