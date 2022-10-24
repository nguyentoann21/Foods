package com.example.foods.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodDBContext extends SQLiteOpenHelper {

    public static final String DB_NAME = "FoodDBContext.db";
    public static final int DB_VERSION = 1;

    public FoodDBContext(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        //DROP DATABASE
        //ctx.deleteDatabase(DB_Name);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Users(username TEXT primary key, fullName TEXT NOT NULL, password TEXT NOT NULL, confirmPassword TEXT NOT NULL, address TEXT NOT NULL, email TEXT NOT NULL UNIQUE, phone TEXT NOT NULL UNIQUE, role TEXT NOT NULL DEFAULT 'user')");
        String createAdmin = "INSERT INTO Users (username, fullName, password, confirmPassword, address, email, phone, role) \n" +
                "VALUES ('admin','Nguyen Toan', '123456', '123456', 'vietnam', 'nguyenvantoan28082001@gmail.com', '0762871115', 'admin')";
        db.execSQL(createAdmin);
        db.execSQL("CREATE TABLE IF NOT EXISTS Products(productId TEXT primary key, productName TEXT NOT NULL, categoryId TEXT NOT NULL, productQuantiy INT NOT NULL, productPrice LONG NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Categories(categoryId TEXT primary key, categoryName TEXT NOT NULL)");
        db.execSQL("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Products");
        db.execSQL("DROP TABLE IF EXISTS Categories");
        onCreate(db);
    }

    public Boolean addUser(String username, String fullName, String password, String confirmPassword, String address, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("fullName", fullName);
        values.put("password", password);
        values.put("confirmPassword", confirmPassword);
        values.put("address", address);
        values.put("email", email);
        values.put("phone", phone);
        long result = db.insert("Users", null, values);
        return result != -1;
    }

    public Boolean checkDuplicateUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=?", new String[]{username});
        return cursor.getCount() > 0;
    }

    public Boolean checkLogin(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=? and password=?", new String[]{username, password});
        return cursor.getCount() > 0;
    }

    public Boolean addProduct(String productId, String productName, String categoryId, int productQuantity, long productPrice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("productId", productId);
        values.put("productName", productName);
        values.put("categoryId", categoryId);
        values.put("productQuantity", productQuantity);
        values.put("productPrice", productPrice);
        long result = db.insert("Products", null, values);
        return result != -1;
    }

    public Boolean removeProduct(String productId){
        SQLiteDatabase db = this.getWritableDatabase();
        long rm = db.delete("Products", "productId=?", new String[]{productId});
        return rm != -1;
    }

    public Boolean updateProduct(String productId, String productName, String categoryId, int productQuantity, long productPrice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("productName", productName);
        values.put("categoryId", categoryId);
        values.put("productQuantity", productQuantity);
        values.put("productPrice", productPrice);
        long result = db.update("Products", values, "productId=?", new String[]{productId});
        return result != -1;
    }

    public Boolean checkDuplicateProduct(String productId, int productQuantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM Products WHERE productId=?", new String[]{productId, String.valueOf(productQuantity)});
        if(cursor.getCount() > 0){
            int newQuantity = cursor.getInt(productQuantity) + productQuantity;
            values.put("productQuantity", newQuantity);
            long result = db.update("Products", values, "productId=?", new String[]{productId, String.valueOf(productQuantity)});
            return result != -1;
        }else{
            return false;
        }
    }

    public Boolean addCategory(String categoryId, String categoryName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("categoryId", categoryId);
        values.put("categoryName", categoryName);
        long result = db.insert("Categories", null, values);
        return result != -1;
    }

    public Boolean updateCategory(String categoryId, String categoryName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("categoryName", categoryName);
        long result = db.update("Categories", values, "categoryId=?", new String[]{categoryId});
        return result != -1;
    }

    public Boolean removeCategory(String categoryId){
        SQLiteDatabase db = this.getWritableDatabase();
        long rm = db.delete("Categories", "categoryId=?", new String[]{categoryId});
        return rm != -1;
    }

}