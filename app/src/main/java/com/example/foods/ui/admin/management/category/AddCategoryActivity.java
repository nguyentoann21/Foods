package com.example.foods.ui.admin.management.category;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foods.R;
import com.example.foods.dao.CategoryDAO;

public class AddCategoryActivity extends AppCompatActivity {

    ImageView categoryImage;
    EditText categoryName;
    Button addCategory, addCategoryCancel, addToShow;
    private static final int PICK_IMAGE = 1111;
    private Bitmap imageToStore;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        categoryImage = findViewById(R.id.img_category);
        categoryName = findViewById(R.id.category_input_name);
        addCategory = findViewById(R.id.btn_add_category);
        addToShow = findViewById(R.id.btn_add_to_show);
        addCategoryCancel = findViewById(R.id.add_category_cancel_button);

        categoryImage.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE);
        });

        addCategory.setOnClickListener(v -> {
            try {
                if(!categoryName.getText().toString().isEmpty() &&
                        categoryImage.getDrawable() != null &&
                        imageToStore != null){
                    boolean checkInsert = CategoryDAO.insertCategory(AddCategoryActivity.this, imageToStore, categoryName.getText().toString());
                    if(checkInsert)
                        Toast.makeText(AddCategoryActivity.this, "Add Category is successful", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(AddCategoryActivity.this, "Add Category is failed", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddCategoryActivity.this, "Please select image or input name", Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                Toast.makeText(AddCategoryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        addToShow.setOnClickListener(v -> startActivity(new Intent(AddCategoryActivity.this, ShowAllCategoryActivity.class)));

        addCategoryCancel.setOnClickListener(v -> startActivity(new Intent(AddCategoryActivity.this, CategoryManagementActivity.class)));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == PICK_IMAGE &&
                    resultCode == RESULT_OK &&
                    data != null && data.getData() != null){
                Uri imagePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                categoryImage.setImageBitmap(imageToStore);
            }

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}