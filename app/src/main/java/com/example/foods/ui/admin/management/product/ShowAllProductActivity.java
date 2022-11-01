package com.example.foods.ui.admin.management.product;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.CursorWindow;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.foods.R;
import com.example.foods.adapters.ShowProductAdapter;
import com.example.foods.dao.ProductDAO;
import com.example.foods.interfaces.IProductClick;
import com.example.foods.models.Products;
import com.example.foods.ui.admin.management.category.AddCategoryActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ShowAllProductActivity extends AppCompatActivity implements IProductClick {


    RecyclerView recycler;
    ShowProductAdapter adapter;
    List<Products> list = null;
    Button goToAdd;
    ImageView img;
    EditText nameImg, quantity, price;
    Button editProduct, removeProduct;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_product);
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }
        goToAdd = findViewById(R.id.btn_go_add_product);
        recycler = findViewById(R.id.list_product_show);
        list = new ArrayList<>();
        list = ProductDAO.getAllProducts(ShowAllProductActivity.this);
        if(list.size() == 0){
            Toast.makeText(ShowAllProductActivity.this, "No data to display", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ShowAllProductActivity.this, AddProductActivity.class));
            finish();
        }else {
            adapter = new ShowProductAdapter(ShowAllProductActivity.this, list, this);
            recycler.setAdapter(adapter);
            recycler.setLayoutManager(new LinearLayoutManager(ShowAllProductActivity.this, RecyclerView.VERTICAL, false));
            recycler.setHasFixedSize(true);
        }
        goToAdd.setOnClickListener(v -> startActivity(new Intent(ShowAllProductActivity.this, AddCategoryActivity.class)));
    }

    @SuppressLint({"NotifyDataSetChanged", "MissingInflatedId", "SetTextI18n"})
    @Override
    public void onClickProductItem(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowAllProductActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_product, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        Products products = list.get(position);
        img = view.findViewById(R.id.image_product_detail);
        nameImg = view.findViewById(R.id.product_detail_name);
        quantity = view.findViewById(R.id.product_detail_quantity);
        price = view.findViewById(R.id.product_detail_price);
        editProduct = view.findViewById(R.id.button_edit_product_detail);
        removeProduct = view.findViewById(R.id.button_delete_product_detail);
        img.setImageBitmap(products.getProductImage());
        nameImg.setText(products.getProductName());
        quantity.setText(products.getQuantity()+"");
        price.setText(products.getPrice()+"");
        editProduct.setOnClickListener(v -> {
            int valueQuantity = Integer.parseInt(quantity.getText().toString());
            int valuePrice = Integer.parseInt(price.getText().toString());
            products.setProductName(nameImg.getText().toString());
            products.setQuantity(valueQuantity);
            products.setPrice(valuePrice);
            if(valuePrice > 0 && valueQuantity > 0) {
                if (ProductDAO.updateProduct(ShowAllProductActivity.this, products)) {
                    Toast.makeText(ShowAllProductActivity.this, "Editing category is successful", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(Objects.requireNonNull(ProductDAO.getAllProducts(ShowAllProductActivity.this)));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(ShowAllProductActivity.this, "Editing category is failed", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(ShowAllProductActivity.this, "Value of quantity and price must be positive integer number", Toast.LENGTH_SHORT).show();
            }
        });

        removeProduct.setOnClickListener(v1 -> {
            AlertDialog.Builder message = new AlertDialog.Builder(ShowAllProductActivity.this);
            message.setTitle("Delete message");
            message.setMessage("Do you want to delete "+products.getProductName()+", right?");
            message.setIcon(R.drawable.ic_remove);
            message.setPositiveButton("OK", (dialogInterface, i) -> {
                if(ProductDAO.deleteProduct(ShowAllProductActivity.this, products.getProductId())){
                    Toast.makeText(ShowAllProductActivity.this, "Deleted product is successful", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(Objects.requireNonNull(ProductDAO.getAllProducts(this)));
                    if(list.size() == 0){
                        Toast.makeText(this, "Remove all of products, Please enter a new product!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ShowAllProductActivity.this, AddProductActivity.class));
                        finish();
                    }
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else{
                    Toast.makeText(ShowAllProductActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            });
            message.setNegativeButton("Cancel", (dialogInterface, i) -> {

            });
            AlertDialog dialogSub = message.create();
            dialogSub.show();
        });
    }
}