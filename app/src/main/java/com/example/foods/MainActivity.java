package com.example.foods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foods.ui.home.HomeActivity;


public class MainActivity extends AppCompatActivity {
    private String[] PERMISSIONS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtStart = findViewById(R.id.txt_start);

        PERMISSIONS = new String[] {
                Manifest.permission.INTERNET,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CALL_PHONE
        };
        txtStart.setOnClickListener(v -> {
            if(!hasPermissions(MainActivity.this, PERMISSIONS)){
                ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS, 1234);
            }else{
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }

    private boolean hasPermissions(Context context, String... PERMISSIONS){
        if(context != null && PERMISSIONS != null){
            for(String permission : PERMISSIONS){
                if(ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1234){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "INTERNET Permission is granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "INTERNET Permission is denied", Toast.LENGTH_SHORT).show();
            }

            if(grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "READ EXTERNAL STORAGE Permission is granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "READ EXTERNAL STORAGE Permission is denied", Toast.LENGTH_SHORT).show();
            }

            if(grantResults[2] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "WRITE EXTERNAL STORAGE Permission is granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "WRITE EXTERNAL STORAGE Permission is denied", Toast.LENGTH_SHORT).show();
            }

            if(grantResults[3] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "CALL PHONE Permission is granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "CALL PHONE Permission is denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}