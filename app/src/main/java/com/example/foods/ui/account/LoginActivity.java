package com.example.foods.ui.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foods.R;
import com.example.foods.dao.UserDAO;
import com.example.foods.ui.admin.AdminActivity;
import com.example.foods.ui.home.HomeActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1000;
    TextView signUp;
    ImageView close;
    LinearLayout google;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button login;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signUp = findViewById(R.id.btn_toSignUp);
        close = findViewById(R.id.img_login_cancel);
        google = findViewById(R.id.layout_login_google);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(LoginActivity.this, gso);
        login = findViewById(R.id.btn_login);
        username = findViewById(R.id.txt_login_username);
        password = findViewById(R.id.txt_login_password);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
            navigateToHome();
        }
        login.setOnClickListener(v -> {
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
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        google.setOnClickListener(v -> signIn());
        signUp.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
        close.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, HomeActivity.class)));
    }

    private void signIn(){
        Intent _signIntent = gsc.getSignInIntent();
        startActivityForResult(_signIntent, RC_SIGN_IN);
    }

    void navigateToHome(){
        finish();
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToHome();
            } catch (ApiException e) {
                Log.d(e.toString(), null);
                Toast.makeText(LoginActivity.this, "Something was wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}