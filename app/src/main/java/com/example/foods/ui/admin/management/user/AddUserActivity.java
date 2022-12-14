package com.example.foods.ui.admin.management.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.foods.R;
import com.example.foods.dao.UserDAO;

public class AddUserActivity extends AppCompatActivity {

    EditText username, fullName, password, confirmPassword, address, email, phone;
    Button add_User, back_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        username = findViewById(R.id.txt_add_user_username);
        fullName = findViewById(R.id.txt_add_user_fullName);
        password = findViewById(R.id.txt_add_user_password);
        confirmPassword = findViewById(R.id.txt_add_user_rePassword);
        address = findViewById(R.id.txt_add_user_address);
        email = findViewById(R.id.txt_add_user_email);
        phone = findViewById(R.id.txt_add_user_phone);
        add_User = findViewById(R.id.btn_add_user);
        back_admin = findViewById(R.id.btn_back_admin);

        add_User.setOnClickListener(v -> {
            String user, name, pwd, rePwd, add, mail, numberPhone;
            user = username.getText().toString();
            name = fullName.getText().toString();
            pwd = password.getText().toString();
            rePwd = confirmPassword.getText().toString();
            add = address.getText().toString();
            mail = email.getText().toString();
            numberPhone = phone.getText().toString();
            if(TextUtils.isEmpty(user) || TextUtils.isEmpty(name) ||
                    TextUtils.isEmpty(pwd) || TextUtils.isEmpty(rePwd) ||
                    TextUtils.isEmpty(add) || TextUtils.isEmpty(mail) ||
                    TextUtils.isEmpty(numberPhone)){
                Toast.makeText(AddUserActivity.this, "All fields must be required", Toast.LENGTH_SHORT).show();
            }else{
                if(pwd.equals(rePwd)){
                    boolean checkUser = UserDAO.checkDuplicateUser(AddUserActivity.this, user);
                    if(checkUser){
                        Toast.makeText(AddUserActivity.this, "The username has already existed...", Toast.LENGTH_SHORT).show();
                    }else{
                        if(pwd.length() >= 6 && pwd.length() <= 20) {
                            boolean addUser = UserDAO.insertUser(AddUserActivity.this, user, name, pwd, rePwd, add, mail, numberPhone);
                            if (addUser) {
                                Toast.makeText(AddUserActivity.this, "Add Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddUserActivity.this, UserManagementActivity.class));
                                finish();
                            } else {
                                Toast.makeText(AddUserActivity.this, "Add Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(AddUserActivity.this, "The password length must be from 6 to 20 characters", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(AddUserActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back_admin.setOnClickListener(v -> startActivity(new Intent(AddUserActivity.this, UserManagementActivity.class)));
    }
}