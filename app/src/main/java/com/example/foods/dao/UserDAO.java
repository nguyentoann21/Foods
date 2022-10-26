package com.example.foods.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.foods.database.FoodDBContext;
import com.example.foods.models.Users;

import java.util.ArrayList;

public class UserDAO {
    public static ArrayList<Users> getAllUser(Context context){
        ArrayList<Users> listUsers = new ArrayList<>();
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int user_Id;
            String username, fullName, password, confirmPassword, address, email, phone, role;
            user_Id = cursor.getInt(0);
            username = cursor.getString(1);
            fullName = cursor.getString(2);
            password = cursor.getString(3);
            confirmPassword = cursor.getString(4);
            address = cursor.getString(5);
            email = cursor.getString(6);
            phone = cursor.getString(7);
            role = cursor.getString(8);
            Users _user = new Users(user_Id, username, fullName, password, confirmPassword, address, email, phone, role);
            listUsers.add(_user);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return listUsers;
    }


    public static boolean insertUser(Context context, String username,
                                     String fullName, String password,
                                     String confirm, String address,
                                     String email, String phone){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("fullName", fullName);
        values.put("password", password);
        values.put("confirmPassword", confirm);
        values.put("address", address);
        values.put("email", email);
        values.put("phone", phone);

        long result = db.insert("Users", null, values);
        return (result > 0);
    }


    public static boolean updateUser(Context context, Users user){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fullName", user.getFullName());
        values.put("password", user.getPassword());
        values.put("confirmPassword", user.getConfirmPassword());
        values.put("address", user.getAddress());
        values.put("email", user.getEmail());
        values.put("phone", user.getPhone());

        int result = db.update("Users", values, "username=?", new String[]{user.getUsername()});
        return (result > 0);
    }


    public static boolean deleteUser(Context context, String username){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        int result = db.delete("Users", "username=?", new String[]{username});
        return (result > 0);
    }

    public static boolean checkDuplicateUser(Context context, String username){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=?", new String[]{username});
        return cursor.getCount() > 0;
    }

    public static boolean checkLogin(Context context, String username, String password){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=? and password=?", new String[]{username, password});
        return cursor.getCount() > 0;
    }

    public static boolean isAdmin(Context context, String username, String password){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=? and password=? and role='admin'", new String[]{username, password});
        return (cursor.getCount() > 0);
    }
}
