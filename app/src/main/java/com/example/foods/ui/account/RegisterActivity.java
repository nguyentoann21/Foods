package com.example.foods.ui.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foods.R;
import com.example.foods.dao.UserDAO;
import com.example.foods.ui.home.HomeActivity;

public class RegisterActivity extends AppCompatActivity {
    TextView _signIn;
    EditText username, fullName, password, confirmPassword, address, email, phone;
    Button register, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        _signIn = findViewById(R.id.btn_toSignIn);
        username = findViewById(R.id.txt_register_username);
        fullName = findViewById(R.id.txt_register_fullName);
        password = findViewById(R.id.txt_register_password);
        confirmPassword = findViewById(R.id.txt_add_user_rePassword);
        address = findViewById(R.id.txt_register_address);
        email = findViewById(R.id.txt_register_email);
        phone = findViewById(R.id.txt_register_phone);
        register = findViewById(R.id.btn_register);
        cancel = findViewById(R.id.btn_register_back);

        _signIn.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
        register.setOnClickListener(v -> {
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
                Toast.makeText(RegisterActivity.this, "All fields must be required", Toast.LENGTH_SHORT).show();
            }else{
                if(pwd.equals(rePwd)){
                    boolean checkUser = UserDAO.checkDuplicateUser(RegisterActivity.this, user);
                    if(checkUser){
                        Toast.makeText(RegisterActivity.this, "The username has already existed...", Toast.LENGTH_SHORT).show();
                    }else{
                        if(pwd.length() >= 6 && pwd.length() <= 20) {
                            boolean addUser = UserDAO.insertUser(RegisterActivity.this, user, name, pwd, rePwd, add, mail, numberPhone);
                            if (addUser) {
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "The password length must be from 6 to 20 characters", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, HomeActivity.class)));
    }
}