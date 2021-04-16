package com.javaya.pruebawposs.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.javaya.pruebawposs.R;

import java.util.Calendar;

public class PayCardActivity extends AppCompatActivity {

    EditText txtNumberCard, txtCvv, txtName, txtPayment;
    TextView date, company;
    Spinner spinnerShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_card);

        txtNumberCard = findViewById(R.id.txtNumberCard);
        txtCvv = findViewById(R.id.txtCvv);
        txtName = findViewById(R.id.txtName);
        txtPayment = findViewById(R.id.txtPayment);
        date = findViewById(R.id.date);
        company = findViewById(R.id.company);
        spinnerShare = (Spinner)findViewById(R.id.spinnerShare);

        String[] share = {"1","3","6","9","12","18","24","32"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, share);
        spinnerShare.setAdapter(adapter);

        txtNumberCard.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(txtNumberCard.length() == 4){
                    company.setText(validateCard(txtNumberCard.getText().toString()));
                }else if(txtNumberCard.length() < 4){
                    company.setText("");
                }
                return false;
            }
        });

    }

    public void menu(View view) {
    }

    public void next(View view) {

        if(validateInputs()){

            Intent intent = new Intent(PayCardActivity.this, ConfirmationActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("numberCard", txtNumberCard.getText().toString());
            bundle.putString("date", date.getText().toString());
            bundle.putString("name", txtName.getText().toString());
            bundle.putString("payment", txtPayment.getText().toString());
            bundle.putString("share", spinnerShare.getSelectedItem().toString());
            bundle.putString("company", company.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);

        }

    }

    public void previous(View view) {

        Intent intent = new Intent(PayCardActivity.this, MainActivity.class);
        startActivity(intent);

    }

    /**
     * metodo para generar un panel de fecha cuando pinchamos un boton, luego esa fecha la formateamos y la imprimimos en un label
     * @param view
     */

    public void openCalendar(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(PayCardActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String monthFinal = String.valueOf(month + 1);
                String dayFinal = String.valueOf(dayOfMonth);
                if(monthFinal.length() == 1){
                    monthFinal = "0" + monthFinal;
                }
                if(dayFinal.length() == 1){
                    dayFinal = "0" + dayFinal;
                }
                String flag = dayFinal + "/" + monthFinal + "/" + year;
                date.setText(flag);
            }
        }, year, month, day);
        datePicker.show();
    }

    /**
     * metodo para validar todos los campos input del pago que estamos realizando
     * @return true > campos correctos. false > algun campo incorrecto
     */

    public boolean validateInputs() {
        boolean answer = false;
        String card = txtNumberCard.getText().toString();
        String cvv = txtCvv.getText().toString();
        String total = txtPayment.getText().toString();
        String name = txtName.getText().toString();
        String dateFinal = date.getText().toString();

        if(card.isEmpty() || cvv.isEmpty() || total.isEmpty() || name.isEmpty() || dateFinal.equals("DD/MM/AAAA")){

            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();

        }else if(card.length() != 16) {

            Toast.makeText(this, "Tarjeta no válida. Min 16 caracteres", Toast.LENGTH_SHORT).show();

        }else if(cvv.length() != 3) {

            Toast.makeText(this, "Código CVV debe contener 3 caracteres", Toast.LENGTH_SHORT).show();

        }else if(!name.matches("[a-zA-ZÀ-ÿ\\s?]*\\D{5}")) {

            Toast.makeText(this, "El nombre no es válido", Toast.LENGTH_SHORT).show();

        }else if(Integer.parseInt(total) < 100 || Integer.parseInt(total) > 90000) {

            Toast.makeText(this, "Pago mínimo 100 y máximo 90.000", Toast.LENGTH_SHORT).show();

        }else{

            answer = true;

        }

        return answer;

    }

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

}