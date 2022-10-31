package com.example.foods.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.foods.database.FoodDBContext;
import com.example.foods.models.Categories;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryDAO {
    public static List<Categories> getAllCategories(Context context) {
        List<Categories> listCategories = new ArrayList<>();
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM Categories", null);
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()) {
                int categoryId = cursor.getInt(0);
                byte[] categoryImage = cursor.getBlob(1);
                String categoryName = cursor.getString(2);
                Bitmap bitmap = BitmapFactory.decodeByteArray(categoryImage, 0, categoryImage.length);
                Categories m = new Categories(categoryId, bitmap, categoryName);
                listCategories.add(m);
            }
            return listCategories;
        }else{
            return Collections.emptyList();
        }
    }

    public static boolean insertCategory(Context context, Bitmap image, String name){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageIn = stream.toByteArray();
        ContentValues values = new ContentValues();
        values.put("categoryImage", imageIn);
        values.put("categoryName", name);
        long result = db.insert("Categories", null, values);
        return (result > 0);
    }


   public static boolean updateCategory(Context context, Categories category){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        Bitmap img = category.getCategoryImage();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] image = stream.toByteArray();
        ContentValues values = new ContentValues();
        values.put("categoryImage", image);
        values.put("categoryName", category.getCategoryName());

        int result = db.update("Categories", values, "categoryId=?", new String[]{category.getCategoryId()+""});
        return (result > 0);
    }


    public static boolean deleteCategory(Context context, int id){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        int result = db.delete("Categories", "categoryId=?", new String[]{id + ""});
        return (result > 0);
    }
}
