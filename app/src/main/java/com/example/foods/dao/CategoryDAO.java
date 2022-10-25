package com.example.foods.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.foods.database.FoodDBContext;
import com.example.foods.models.Categories;

import java.util.ArrayList;

public class CategoryDAO {
    /*public static ArrayList<Categories> getAllCategories(Context context){
        ArrayList<Categories> listCategories = new ArrayList<>();
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Categories", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String _categoryId, _categoryName;
            _categoryId = cursor.getString(0);
            _categoryName = cursor.getString(1);

            Categories _category = new Categories(_categoryId, _categoryName);
            listCategories.add(_category);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return listCategories;
    }


    public static boolean insertCategory(Context context, String categoryId,
                                        String categoryName){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("categoryId", categoryId);
        values.put("categoryName", categoryName);

        long result = db.insert("Categories", null, values);
        return (result > 0);
    }


    public static boolean updateCategory(Context context, Categories category){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("categoryName", category.getProductName());

        int result = db.update("Products", values, "productId=?", new String[]{category.getCategoryId()});
        return (result > 0);
    }


    public static boolean deleteCategory(Context context, String idCategory){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        int result = db.delete("Categories", "categoryId=?", new String[]{idCategory});
        return (result > 0);
    }*/
}
