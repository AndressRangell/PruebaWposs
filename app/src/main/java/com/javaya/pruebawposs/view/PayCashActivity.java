package com.javaya.pruebawposs.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.javaya.pruebawposs.R;
import com.javaya.pruebawposs.database.DataBase;
import com.javaya.pruebawposs.models.Payment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PayCashActivity extends AppCompatActivity {

    EditText txtName, txtPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_cash);

        txtName = findViewById(R.id.txtName);
        txtPayment = findViewById(R.id.txtPayment);

    }

    public void menu(View view) {
    }

    public void previous(View view) {

        Intent intent = new Intent(PayCashActivity.this, FailedActivity.class);
        startActivity(intent);

    }

    public void next(View view) {

        if(validateInputs()){

            Date date = Calendar.getInstance().getTime();
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String today = format.format(date);

            Payment payment = new Payment();
            payment.setName(txtName.getText().toString());
            payment.setPaymentValue(txtPayment.getText().toString());
            payment.setDate(today);

            DataBase db = new DataBase(getApplicationContext());
            boolean answer = db.addPayment(payment);

            if(answer){
                Intent intent = new Intent(PayCashActivity.this, SuccesfulActivity.class);
                startActivity(intent);
            }

        }

    }

    /**
     * metodo para validar campos de pago en efectivo
     * @return true > campos correctos. false > algun campo incorrecto
     */

    public boolean validateInputs() {
        boolean answer = false;
        String total = txtPayment.getText().toString();
        String name = txtName.getText().toString();

        if(total.isEmpty() || name.isEmpty()){

            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();

        }else if(!name.matches("[a-zA-ZÀ-ÿ\\s?]*\\D{5}")) {

            Toast.makeText(this, "El nombre no es válido", Toast.LENGTH_SHORT).show();

        }else if(Integer.parseInt(total) < 100 || Integer.parseInt(total) > 90000) {

            Toast.makeText(this, "Pago mínimo 100 y máximo 90.000", Toast.LENGTH_SHORT).show();

        }else{

            answer = true;

        }

        return answer;

    }

}