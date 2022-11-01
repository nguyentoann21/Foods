package com.example.foods.ui.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foods.R;
import com.example.foods.dao.UserDAO;
import com.example.foods.ui.admin.AdminActivity;
import com.example.foods.ui.home.HomeActivity;

public class LoginActivity extends AppCompatActivity {
    TextView signUp, forgotPwd;
    ImageView close;
    Button login;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signUp = findViewById(R.id.btn_toSignUp);
        close = findViewById(R.id.img_login_cancel);
        login = findViewById(R.id.btn_login);
        username = findViewById(R.id.txt_login_username);
        password = findViewById(R.id.txt_login_password);
        forgotPwd = findViewById(R.id.txt_login_forgot);

        login.setOnClickListener(v1 -> {
            String user, pwd;
            user = username.getText().toString();
            pwd = password.getText().toString();

            if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pwd)){
                Toast.makeText(LoginActivity.this, "All fields must be required", Toast.LENGTH_SHORT).show();
            }else{
                boolean checkDataLogin = UserDAO.checkLogin(LoginActivity.this, user, pwd);
                boolean isAdmin = UserDAO.isAdmin(LoginActivity.this, user, pwd);
                if(checkDataLogin){
                    if(isAdmin) {
                        Toast.makeText(LoginActivity.this, "Admin Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Login failed, Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        forgotPwd.setOnClickListener(v3 -> startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));
        signUp.setOnClickListener(v4 -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
        close.setOnClickListener(v5 -> startActivity(new Intent(LoginActivity.this, HomeActivity.class)));
    }
}