package com.example.tugas8lagi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SelectedUser extends AppCompatActivity {

    TextView tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_user);

        tvUser = findViewById(R.id.selectedUser);

        Intent intent = getIntent();

        if (intent.getExtras() != null){
            UsersModel usersModel = (UsersModel) intent.getSerializableExtra("data");

            tvUser.setText(usersModel.getUserName());

        }
    }
}