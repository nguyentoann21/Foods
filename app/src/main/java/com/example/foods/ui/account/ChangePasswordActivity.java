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
import com.example.foods.models.Users;
import com.example.foods.ui.home.HomeActivity;


public class ChangePasswordActivity extends AppCompatActivity {

    EditText old, newPwd, reNewPwd;
    Button reset, cancelReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Users u = UserDAO.USER;
        old = findViewById(R.id.txt_change_password_old);
        newPwd = findViewById(R.id.txt_change_password_new);
        reNewPwd = findViewById(R.id.txt_change_password_reNew);
        reset = findViewById(R.id.reset_password);
        cancelReset = findViewById(R.id.cancel_reset);

        reset.setOnClickListener(view -> {
            String oldPassword, newPassword, rePassword;
            oldPassword = old.getText().toString();
            newPassword = newPwd.getText().toString();
            rePassword = reNewPwd.getText().toString();
            if(TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(rePassword)){
                Toast.makeText(ChangePasswordActivity.this, "All fields must be required", Toast.LENGTH_SHORT).show();
            }else {
                if (!oldPassword.equals(u.getPassword())) {
                    Toast.makeText(ChangePasswordActivity.this, "Old password incorrect!", Toast.LENGTH_SHORT).show();
                } else {
                    if (newPassword.equals(oldPassword)) {
                        Toast.makeText(ChangePasswordActivity.this, "The new password must be distinct from the old password", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!newPassword.equals(rePassword)) {
                            Toast.makeText(ChangePasswordActivity.this, "The password does not match", Toast.LENGTH_SHORT).show();
                        }else{
                            if(newPassword.length() >= 6 && newPassword.length() <= 20) {
                                boolean changePassword = UserDAO.changePassword(ChangePasswordActivity.this, u.getUsername(), newPassword);
                                if (changePassword) {
                                    Toast.makeText(ChangePasswordActivity.this, "Change password is successful!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ChangePasswordActivity.this, LoginActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(ChangePasswordActivity.this, "Change password failed!", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(ChangePasswordActivity.this, "The password length must be from 6 to 20 characters.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
        cancelReset.setOnClickListener(v -> startActivity(new Intent(ChangePasswordActivity.this, HomeActivity.class)));
    }
}