package com.example.foods.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodDBContext extends SQLiteOpenHelper {

    public static final String DB_NAME = "FoodDBContext";
    public static final int DB_VERSION = 1;

    public FoodDBContext(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        //DROP DATABASE
//        context.deleteDatabase(DB_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Users(userId INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, fullName TEXT NOT NULL, password TEXT NOT NULL, confirmPassword TEXT NOT NULL, address TEXT NOT NULL, email TEXT NOT NULL, phone TEXT NOT NULL, role TEXT NOT NULL DEFAULT 'user')");
        db.execSQL("CREATE TABLE Categories(categoryId INTEGER PRIMARY KEY AUTOINCREMENT, categoryImage BLOB NOT NULL, categoryName TEXT NOT NULL)");
        db.execSQL("CREATE TABLE Products(productId INTEGER PRIMARY KEY AUTOINCREMENT, productImage BLOB NOT NULL, productName TEXT NOT NULL, quantity INTEGER NOT NULL, price INTEGER NOT NULL, productCategory INTEGER NOT NULL, FOREIGN KEY(productCategory) REFERENCES Categories(categoryId))");
        String createAdmin = "INSERT INTO Users (userId, username, fullName, password, confirmPassword, address, email, phone, role) \n" +
                "VALUES (null, 'admin','Nguyen Toan', '123456', '123456', 'vietnam', 'nguyenvantoan28082001@gmail.com', '0762871115', 'admin')";
        db.execSQL(createAdmin);
        db.execSQL("INSERT INTO Users (userId, username, fullName, password, confirmPassword, address, email, phone)\n" +
                "VALUES (null, 'nguyentoan','Nguyen Toan', '123456', '123456', 'vietnam', 'nguyenvantoan28082001@gmail.com', '0762871115')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Categories");
        db.execSQL("DROP TABLE IF EXISTS Products");
        onCreate(db);
    }
}