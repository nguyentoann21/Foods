package com.example.foods.ui.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foods.R;
import com.example.foods.dao.UserDAO;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText usernameForgot, emailForgot, passwordForgot, rePassword;
    Button forgotPassword, cancelForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        usernameForgot = findViewById(R.id.txt_forgot_username);
        emailForgot = findViewById(R.id.txt_forgot_email);
        passwordForgot = findViewById(R.id.txt_forgot_password_new);
        rePassword = findViewById(R.id.txt_forgot_password_reNew);
        forgotPassword = findViewById(R.id.forgot_password);
        cancelForgot = findViewById(R.id.cancel_forgot);

        forgotPassword.setOnClickListener(view -> {
            String usernameFG, emailFG, newPasswordForgot, rePasswordForgot;
            usernameFG = usernameForgot.getText().toString();
            emailFG = emailForgot.getText().toString();
            newPasswordForgot = passwordForgot.getText().toString();
            rePasswordForgot = rePassword.getText().toString();
            if(TextUtils.isEmpty(emailFG) || TextUtils.isEmpty(newPasswordForgot) || TextUtils.isEmpty(rePasswordForgot)){
                Toast.makeText(ForgotPasswordActivity.this, "All fields must be required", Toast.LENGTH_SHORT).show();
            }else {
                boolean checkEmail = UserDAO.checkEmail(ForgotPasswordActivity.this, emailFG, usernameFG);
                if(!checkEmail){
                    Toast.makeText(ForgotPasswordActivity.this, "Email or Username is invalid!", Toast.LENGTH_SHORT).show();
                }else{
                    if(!newPasswordForgot.equals(rePasswordForgot)){
                        Toast.makeText(ForgotPasswordActivity.this, "The password does not match", Toast.LENGTH_SHORT).show();
                    }else{
                        if (newPasswordForgot.length() >= 6 && newPasswordForgot.length() <= 20) {
                            boolean forgotPassword = UserDAO.forgotPassword(ForgotPasswordActivity.this, emailFG, newPasswordForgot);
                            if(forgotPassword){
                                Toast.makeText(ForgotPasswordActivity.this, "Reset password successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                                finish();
                            }else{
                                Toast.makeText(ForgotPasswordActivity.this, "Reset password failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, "The password length must be from 6 to 20 characters.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        cancelForgot.setOnClickListener(v -> startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class)));
    }
}