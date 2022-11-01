package com.example.foods.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.foods.database.FoodDBContext;
import com.example.foods.models.Users;

import java.util.ArrayList;

public class UserDAO {
    public static Users USER;
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

        int result = db.update("Users", values, "userId=?", new String[]{user.getUserId() + ""});
        return (result > 0);
    }


    public static boolean deleteUser(Context context, int id){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        int result = db.delete("Users", "userId=?", new String[]{id + ""});
        return (result > 0);
    }

    public static boolean checkDuplicateUser(Context context, String username){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=?", new String[]{username});
        return cursor.getCount() > 0;
    }

    public static boolean checkLogin(Context context, String username, String password){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=? and password=?", new String[]{username, password});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            int uId;
            String user, name, pwd, cPwd, add, mail, call, isAd;
            uId = cursor.getInt(0);
            user = cursor.getString(1);
            name = cursor.getString(2);
            pwd = cursor.getString(3);
            cPwd = cursor.getString(4);
            add = cursor.getString(5);
            mail = cursor.getString(6);
            call = cursor.getString(7);
            isAd = cursor.getString(8);
            USER = new Users(uId, user, name, pwd, cPwd, add, mail, call, isAd);
            return true;
        }else{
            return false;
        }
    }

    public static boolean isAdmin(Context context, String username, String password){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=? and password=? and role='admin'", new String[]{username, password});
        return (cursor.getCount() > 0);
    }

    public static boolean changePassword(Context context, String username, String newPassword){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", newPassword);
        int result = db.update("Users", values, "username=?", new String[]{username});
        return (result > 0);
    }

    public static boolean checkEmail(Context context, String email, String username){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE email=? and username=?", new String[]{email, username});
        cursor.close();
        return cursor.getCount() > 0;
    }

    public static boolean forgotPassword(Context context, String email, String resetPassword){
        FoodDBContext data = new FoodDBContext(context);
        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", resetPassword);
        int result = db.update("Users", values, "email=?", new String[]{email});
        return (result > 0);
    }
}
