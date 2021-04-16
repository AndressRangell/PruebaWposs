package com.javaya.pruebawposs.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.javaya.pruebawposs.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void menu(View view) {
    }

    public void addUser(View view) {

        Intent intent = new Intent(AdminActivity.this, AddUserActivity.class);
        startActivity(intent);

    }

    public void listUsers(View view) {

        Intent intent = new Intent(AdminActivity.this, ListUsersActivity.class);
        startActivity(intent);

    }

    public void perfil(View view) {

        Intent intent = new Intent(AdminActivity.this, ProfileActivity.class);
        startActivity(intent);

    }

    /**
     * metodo para cerrar sesion, lanza un cuadro de confirmacion
     * @param view
     */

    public void logout(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
        builder.setMessage("¿Desea cerrar sesión?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
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