package com.example.foods.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.foods.database.FoodDBContext;
import com.example.foods.models.Users;

import java.util.ArrayList;

public class UserDAO {
    /*
    @NonNull
    public static ArrayList<Users> getAllUser(Context context){
        ArrayList<Users> listUsers = new ArrayList<>();
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String _username, _fullName, _password, _confirm, _address, _email, _phone, _role;
            _username = cursor.getString(0);
            _fullName = cursor.getString(1);
            _password = cursor.getString(2);
            _confirm = cursor.getString(3);
            _address = cursor.getString(4);
            _email = cursor.getString(5);
            _phone = cursor.getString(6);
            _role = cursor.getString(7);
            Users _user = new Users(_username, _fullName, _password, _confirm, _address, _email, _phone, _role);
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

     */
}
