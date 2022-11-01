package com.example.foods.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.foods.database.FoodDBContext;
import com.example.foods.models.Products;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDAO {
    public static List<Products> getAllProducts(Context context) {
        List<Products> listProducts = new ArrayList<>();
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM Products", null);
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()) {
                int productId = cursor.getInt(0);
                byte[] productImage = cursor.getBlob(1);
                String productName = cursor.getString(2);
                int quantity = cursor.getInt(3);
                int price = cursor.getInt(4);
                int productCategory = cursor.getInt(5);
                Bitmap bitmap = BitmapFactory.decodeByteArray(productImage, 0, productImage.length);
                Products m = new Products(productId, bitmap, productName, quantity, price, productCategory);
                listProducts.add(m);
            }
            return listProducts;
        }else{
            return Collections.emptyList();
        }
    }


    public static boolean insertProduct(Context context, Bitmap image, String name, int quantity, int price, int productCategory){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageIn = stream.toByteArray();
        ContentValues values = new ContentValues();
        values.put("productImage", imageIn);
        values.put("productName", name);
        values.put("quantity", quantity);
        values.put("price", price);
        values.put("productCategory", productCategory);
        long result = db.insert("Products", null, values);
        return (result > 0);
    }


    public static boolean updateProduct(Context context, Products product){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        Bitmap img = product.getProductImage();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] image = stream.toByteArray();
        ContentValues values = new ContentValues();
        values.put("productImage", image);
        values.put("productName", product.getProductName());
        values.put("quantity", product.getQuantity());
        values.put("price", product.getPrice());
        values.put("productCategory", product.getProductCategory());
        int result = db.update("Products", values, "productId=?", new String[]{product.getProductId()+""});
        return (result > 0);
    }


    public static boolean deleteProduct(Context context, int id){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        int result = db.delete("Products", "productId=?", new String[]{id + ""});
        return (result > 0);
    }
}
