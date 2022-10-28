package com.example.foods.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.foods.R;
import com.example.foods.dao.UserDAO;
import com.example.foods.models.Users;
import com.example.foods.ui.account.ChangePasswordActivity;
import com.example.foods.ui.account.LoginActivity;


public class ProfileFragment extends Fragment {
    TextView uName, fName, add, mail, nPhone;
    Button changePassword, logout;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile,container,false);

        uName  = root.findViewById(R.id.txt_username);
        fName  = root.findViewById(R.id.txt_display_name);
        add  = root.findViewById(R.id.txt_display_location);
        mail  = root.findViewById(R.id.txt_display_email);
        nPhone  = root.findViewById(R.id.txt_display_phone);
        changePassword = root.findViewById(R.id.btn_change_password);
        logout = root.findViewById(R.id.btn_logout);

        Users u = UserDAO.USER;
        if(u != null) {
            uName.setText(u.getUsername());
            fName.setText(u.getFullName());
            add.setText(u.getAddress());
            mail.setText(u.getEmail());
            nPhone.setText(u.getPhone());

            changePassword.setOnClickListener(v -> startActivity(new Intent(getActivity(), ChangePasswordActivity.class)));
            logout.setOnClickListener(view -> {
                UserDAO.USER = null;
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                Toast.makeText(getActivity(), "Logout successful", Toast.LENGTH_SHORT).show();
            });
        }else{
            Toast.makeText(getActivity(), "You must be login before", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
        return root;
    }
}