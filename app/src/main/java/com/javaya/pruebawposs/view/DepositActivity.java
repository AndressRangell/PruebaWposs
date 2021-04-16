package com.javaya.pruebawposs.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.javaya.pruebawposs.R;
import com.javaya.pruebawposs.database.DataBase;
import com.javaya.pruebawposs.models.Transaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DepositActivity extends AppCompatActivity {

    private EditText txtCard, txtName, txtValue;
    private TextView company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        txtCard = findViewById(R.id.txtCard);
        txtName = findViewById(R.id.txtName);
        txtValue = findViewById(R.id.txtValue);
        company = findViewById(R.id.company);

        txtCard.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(txtCard.length() == 4){
                    company.setText("Bienvenida su tarjeta " + validateCard(txtCard.getText().toString()));
                }else if(txtCard.length() < 4){
                    company.setText("Bienvenida su tarjeta ");
                }
                return false;
            }
        });

    }

    /**
     * metodo para añadir el deposito en db, valida todos los campos y los datos de la cuenta
     * @param view
     */

    public void deposit(View view) {

        String name = txtName.getText().toString().trim();
        String card = txtCard.getText().toString().trim();
        String value = txtValue.getText().toString().trim();

        if (name.isEmpty() || card.isEmpty() || value.isEmpty()){

            Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();

        }else if(!name.matches("[a-zA-ZÀ-ÿ\\s?]*\\D{5}")) {

            Toast.makeText(getApplicationContext(), "El nombre no es valido", Toast.LENGTH_SHORT).show();

        }else if(card.length() < 16) {

            Toast.makeText(getApplicationContext(), "Documento debe contener 16 caracteres", Toast.LENGTH_SHORT).show();

        }else{

            Date date = Calendar.getInstance().getTime();
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String today = format.format(date);

            Transaction transaction = new Transaction();
            transaction.setNumberCard(card);
            transaction.setName(name);
            transaction.setTypeTransaction("deposito");
            transaction.setValue(value);
            transaction.setDate(today);

            SharedPreferences preferences = getSharedPreferences("loginPreferences",
                    Context.MODE_PRIVATE);

            String count = preferences.getString("count", "");

            try {

                DataBase db = new DataBase(this);
                boolean answer = db.validateCard(transaction.getNumberCard());
                if(answer){
                    answer = db.addTransaction(transaction, count);
                }
                if(answer){
                    Intent intent = new Intent(DepositActivity.this, SuccesfulActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(DepositActivity.this, FailedActivity.class);
                    startActivity(intent);
                }
            }catch (Exception e){

            }
        }

    }

    /**
     * metodo para validar tarjeta de credito dependiendo de sus 4 primeros numeros
     * @param numberCard numero de la tarjeta de credito
     * @return retorna el nombre de la compañia a la que pertenece la tarjeta
     */

    private String validateCard(String numberCard) {

        int number = Integer.parseInt(numberCard);
        String answer = "";

        if(number < 1000 || number >= 6000){
            answer = "Error!!";
        }else if(number > 999 && number < 2000){
            answer = "MasterCard";
        }else if(number > 1999 && number < 3000){
            answer = "VISA";
        }else if(number > 2999 && number < 4000){
            answer ="American";
        }else if(number > 3999 && number < 5000){
            answer ="Maestro";
        }else{
            answer = "Otra";
        }

        return answer;

    }

    public void back(View view) {

        finish();

    }
}