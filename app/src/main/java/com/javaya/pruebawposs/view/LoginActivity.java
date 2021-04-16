package com.javaya.pruebawposs.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.javaya.pruebawposs.R;
import com.javaya.pruebawposs.database.DataBase;
import com.javaya.pruebawposs.models.Client;
import com.javaya.pruebawposs.models.User;

public class LoginActivity extends AppCompatActivity {

    Context context = LoginActivity.this;
    EditText txtEmail, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        SharedPreferences preferences = getSharedPreferences("loginPreferences",
                Context.MODE_PRIVATE);
        preferences.edit().clear().commit();

        //generamos un registro de usuario admin y uno de cliente por defecto
        try {
            DataBase db = new DataBase(getApplicationContext());
            db.addUser(new User("1", "12345", "67890", "admin", "admin@wposs.com",
                    "Admin123*", 0, "habilitado"));
            db.addClient(new Client("1", "Jhoan Rangel", "1234567891234567", "112233", "1209", 5000));
        }catch (Exception e){

        }

    }

    /**
     * metodo para validar los datos del cliente al momento de loguearnos valida que el email y password correspondan a un cliente
     * @param view
     */

    public void login(View view) {
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        DataBase db = new DataBase(getApplicationContext());
        User user = db.validateUser(email, password);
        if (user != null) {
            SharedPreferences preferences = getSharedPreferences("loginPreferences",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("count", user.getCount());
            editor.putString("name", user.getName());
            editor.putString("email", user.getEmail());
            editor.putString("status", user.getStatus());
            editor.commit();

            if(email.contains("@wposs")){
                Toast.makeText(this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            finish();
        } else {
            Toast.makeText(this, "Error en inicio de sesion", Toast.LENGTH_SHORT).show();
        }

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