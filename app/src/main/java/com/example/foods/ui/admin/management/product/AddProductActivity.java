package com.example.foods.ui.admin.management.product;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.foods.R;
import com.example.foods.adapters.SpinnerAdapter;
import com.example.foods.dao.CategoryDAO;
import com.example.foods.dao.ProductDAO;
import com.example.foods.models.Categories;
import com.example.foods.ui.admin.management.category.AddCategoryActivity;

import java.util.List;

public class AddProductActivity extends AppCompatActivity {

    ImageView productImage;
    EditText productName, quantity, price;
    Spinner spinnerCategory;
    Button addProduct, addProductCancel, addProductToShow;
    private static final int PICK_IMAGE = 2222;
    private Bitmap imageStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productImage = findViewById(R.id.img_product);
        productName = findViewById(R.id.product_input_name);
        quantity = findViewById(R.id.product_input_quantity);
        price = findViewById(R.id.product_input_price);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        addProduct = findViewById(R.id.btn_add_product);
        addProductToShow = findViewById(R.id.btn_add_product_to_show);
        addProductCancel = findViewById(R.id.add_product_cancel_button);

        loadData();

        productImage.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE);
        });

        addProduct.setOnClickListener(v -> {
            try {
                String name;
                int number, valuePrice;
                Categories spinner;
                name = productName.getText().toString();
                number = Integer.parseInt(quantity.getText().toString());
                valuePrice = Integer.parseInt(price.getText().toString());
                spinner = (Categories) spinnerCategory.getSelectedItem();
                if(!name.isEmpty() &&
                    !quantity.getText().toString().isEmpty() &&
                    !price.getText().toString().isEmpty() &&
                    productImage.getDrawable() != null &&
                    imageStore != null){

                    if(number > 0 && valuePrice > 0) {
                        boolean checkInsert = ProductDAO.insertProduct(AddProductActivity.this, imageStore, name, number, valuePrice, spinner.getCategoryId());

                        if (checkInsert) {
                            Toast.makeText(AddProductActivity.this, "Add product is successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddProductActivity.this, ProductManagementActivity.class));
                            finish();
                        } else
                            Toast.makeText(AddProductActivity.this, "Add product is failed", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddProductActivity.this, "Quantity and Price must be greater than 0", Toast.LENGTH_SHORT).show();
                    }
                }else{
                Toast.makeText(AddProductActivity.this, "Please select image and input all fields", Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                Toast.makeText(AddProductActivity.this, "Please select image and input all fields", Toast.LENGTH_SHORT).show();
            }
        });

        addProductToShow.setOnClickListener(v -> startActivity(new Intent(AddProductActivity.this, ShowAllProductActivity.class)));

        addProductCancel.setOnClickListener(v -> startActivity(new Intent(AddProductActivity.this, ProductManagementActivity.class)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == PICK_IMAGE &&
                    resultCode == RESULT_OK &&
                    data != null && data.getData() != null){
                Uri imagePath = data.getData();
                imageStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                productImage.setImageBitmap(imageStore);
            }

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData(){
        List<Categories> list = CategoryDAO.getAllCategories(AddProductActivity.this);
        if(!list.isEmpty()){
            spinnerCategory.setAdapter(new SpinnerAdapter(AddProductActivity.this, R.layout.spinner_layout, list));
        }else{
            Toast.makeText(AddProductActivity.this, "No category data", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddProductActivity.this, AddCategoryActivity.class));
        }
    }
}