package com.example.foods.ui.admin.management.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.foods.R;
import com.example.foods.dao.UserDAO;
import com.example.foods.models.Users;

import java.util.ArrayList;

public class ShowAllUserActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter adapter;
    ArrayList<Users> listUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_user);
        lv = findViewById(R.id.lv_show_all_user);
        listUser = UserDAO.getAllUser(ShowAllUserActivity.this);
        adapter = new ArrayAdapter(ShowAllUserActivity.this, android.R.layout.simple_list_item_1, listUser);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((adapterView, view, i, l) -> showDialogUser(i));

    }

    private void showDialogUser(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowAllUserActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_user, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText name, pwd, rePwd, add, mail, fax;
        Button edit, remove;
        Users user = listUser.get(position);

        name = view.findViewById(R.id.user_detail_fullName);
        pwd = view.findViewById(R.id.user_detail_password);
        rePwd = view.findViewById(R.id.user_detail_rePassword);
        add = view.findViewById(R.id.user_detail_address);
        mail = view.findViewById(R.id.user_detail_email);
        fax = view.findViewById(R.id.user_detail_phone);
        edit = view.findViewById(R.id.button_edit);
        remove = view.findViewById(R.id.button_delete);

        name.setText(user.getFullName());
        pwd.setText(user.getPassword());
        rePwd.setText(user.getConfirmPassword());
        add.setText(user.getAddress());
        mail.setText(user.getEmail());
        fax.setText(user.getPhone());

        edit.setOnClickListener(view1 -> {
            user.setFullName(name.getText().toString());
            user.setPassword(pwd.getText().toString());
            user.setConfirmPassword(rePwd.getText().toString());
            user.setAddress(add.getText().toString());
            user.setEmail(mail.getText().toString());
            user.setPhone(fax.getText().toString());
            if(UserDAO.updateUser(ShowAllUserActivity.this, user)){
                Toast.makeText(ShowAllUserActivity.this, "Editing user is successful", Toast.LENGTH_SHORT).show();
                listUser.clear();
                listUser.addAll(UserDAO.getAllUser(ShowAllUserActivity.this));
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }else{
                Toast.makeText(ShowAllUserActivity.this, "Editing user is failed", Toast.LENGTH_SHORT).show();
            }
        });

        remove.setOnClickListener(view12 -> {
            AlertDialog.Builder message = new AlertDialog.Builder(ShowAllUserActivity.this);
            message.setTitle("Delete message");
            message.setMessage("Do you want to delete "+user.getFullName()+", right?");
            message.setIcon(R.drawable.ic_remove);
            message.setPositiveButton("OK", (dialogInterface, i) -> {
                if(UserDAO.deleteUser(ShowAllUserActivity.this, position)){
                    Toast.makeText(ShowAllUserActivity.this, "Deleted user is successful", Toast.LENGTH_SHORT).show();
                    listUser.clear();
                    listUser.addAll(UserDAO.getAllUser(ShowAllUserActivity.this));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else{
                    Toast.makeText(ShowAllUserActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            });
            message.setNegativeButton("Cancel", (dialogInterface, i) -> {

            });
            AlertDialog dialogSub = message.create();
            dialogSub.show();
        });

    }
}