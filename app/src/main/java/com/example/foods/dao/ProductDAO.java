package com.example.foods.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.foods.database.FoodDBContext;
import com.example.foods.models.Products;

import java.util.ArrayList;

public class ProductDAO {
    /*public static ArrayList<Products> getAllProducts(Context context){
        ArrayList<Products> listProducts = new ArrayList<>();
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Products", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String _productId, _productName, _quantity, _category, _unitPrice, _description;
            _productId = cursor.getString(0);
            _productName = cursor.getString(1);
            _quantity = cursor.getString(2);
            _category = cursor.getString(3);
            _unitPrice = cursor.getString(4);
            _description = cursor.getString(5);
            Products _product = new Products(_productId, _productName, _quantity, _categoryId, _unitPrice, _description);
            listProducts.add(_product);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return listProducts;
    }


    public static boolean insertProduct(Context context, String productId,
                                    String productName, String quantity,
                                    String category, String unitPrice,
                                    String description){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("productId", productId);
        values.put("productName", productName);
        values.put("quantity", quantity);
        values.put("category", category);
        values.put("unitPrice", unitPrice);
        values.put("description", description);

        long result = db.insert("Products", null, values);
        return (result > 0);
    }


    public static boolean updateProduct(Context context, Products product){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("productName", product.getProductName());
        values.put("quantity", product.getQuantity());
        values.put("category", product.getCategory());
        values.put("unitPrice", product.getUnitPrice());
        values.put("description", product.getDescription());

        int result = db.update("Products", values, "productId=?", new String[]{product.getProductId()});
        return (result > 0);
    }


    public static boolean deleteProduct(Context context, String idProduct){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        int result = db.delete("Products", "productId=?", new String[]{idProduct});
        return (result > 0);
    }*/
}
