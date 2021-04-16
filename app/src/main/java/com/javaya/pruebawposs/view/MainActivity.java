package com.javaya.pruebawposs.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.javaya.pruebawposs.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void back(View view) {
    }

    public void menu(View view) {
    }

    public void payCard(View view) {

        Intent intent = new Intent(MainActivity.this, PayCardActivity.class);
        startActivity(intent);

    }

    public void payCash(View view) {

        Intent intent = new Intent(MainActivity.this, PayCashActivity.class);
        startActivity(intent);

    }

    public void deposit(View view) {

        Intent intent = new Intent(MainActivity.this, DepositActivity.class);
        startActivity(intent);

    }

    public void drawal(View view) {

        Intent intent = new Intent(MainActivity.this, DrawalActivity.class);
        startActivity(intent);

    }

    public void history(View view) {

        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);

    }

    public void report(View view) {

        Intent intent = new Intent(MainActivity.this, ReportActivity.class);
        startActivity(intent);

    }

    public void perfil(View view) {

        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);

    }

    public void logout(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("¿Desea cerrar sesión?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

}