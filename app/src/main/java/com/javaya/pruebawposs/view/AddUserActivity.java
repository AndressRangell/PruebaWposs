package com.javaya.pruebawposs.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.javaya.pruebawposs.R;
import com.javaya.pruebawposs.database.DataBase;
import com.javaya.pruebawposs.models.User;

public class AddUserActivity extends AppCompatActivity {

    private EditText txtName, txtCard, txtEmail, txtPassword, txtConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        txtName = findViewById(R.id.txtName);
        txtCard = findViewById(R.id.txtCard);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);

    }

    public void back(View view) {

        finish();

    }

    public void menu(View view) {
    }

    /**
     * metodo para guardar usuario en db, dentro se validan todos los campos
     * @param view
     */

    public void saveUser(View view) {

        String name = txtName.getText().toString().trim();
        String card = txtCard.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        String confirmPassword = txtConfirmPassword.getText().toString().trim();

        if (name.isEmpty() || card.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){

            Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();

        }else if(!name.matches("[a-zA-ZÀ-ÿ\\s?]*\\D{5}")) {

            Toast.makeText(getApplicationContext(), "El nombre no es valido", Toast.LENGTH_SHORT).show();

        }else if(card.length() < 5) {

            Toast.makeText(getApplicationContext(), "Documento mínimo de 5 caracteres", Toast.LENGTH_SHORT).show();

        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.contains("@wposs")) {

            Toast.makeText(getApplicationContext(), "Ingrese un correo valido", Toast.LENGTH_SHORT).show();

        }else if(!password.equals(confirmPassword) || !password.matches("^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{4,12}$")) {

            Toast.makeText(getApplicationContext(), "Contraseña no valida o no coinciden", Toast.LENGTH_SHORT).show();

        }else{

            String count = createCount();

            User user = new User();
            user.setName(name);
            user.setCard(card);
            user.setCount(count);
            user.setEmail(email);
            user.setPassword(password);
            user.setBalance(1000000);
            user.setStatus("habilitado");

            try {
                DataBase db = new DataBase(this);
                db.addUser(user);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddUserActivity.this);
                builder.setMessage("Cuenta: " + count).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AddUserActivity.this, AdminActivity.class);
                        startActivity(intent);
                    }
                });
                builder.show();
            }catch (Exception e){

            }
        }


    }

    /**
     * metodo para crear cuenta con los 3 ultimos numeros del documento y agregando 5 numeros mas de forma aleatoria
     * @return
     */

    private String createCount(){
        String card = txtCard.getText().toString().trim();
        String characters = card.substring(card.length()-3, card.length());

        for(int i = 0; i < 5; i++){
            int number = (int) (Math.random() * 9) + 1;
            characters += String.valueOf(number);
        }
        return characters;
    }

}