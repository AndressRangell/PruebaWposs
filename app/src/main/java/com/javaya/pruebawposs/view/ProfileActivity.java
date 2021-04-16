package com.javaya.pruebawposs.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.javaya.pruebawposs.R;

public class ProfileActivity extends AppCompatActivity {

    private TextView countProfile, nameProfile, emailProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        countProfile = findViewById(R.id.countProfile);
        nameProfile = findViewById(R.id.nameProfile);
        emailProfile = findViewById(R.id.emailProfile);

        SharedPreferences preferences = getSharedPreferences("loginPreferences",
                Context.MODE_PRIVATE);

        String count = preferences.getString("count", "");
        String name = preferences.getString("name", "");
        String email = preferences.getString("email", "");

        countProfile.setText(count);
        nameProfile.setText(name);
        emailProfile.setText(email);

    }

    public void back(View view) {

        finish();

    }

    public void menu(View view) {
    }
}