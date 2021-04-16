package com.javaya.pruebawposs.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.javaya.pruebawposs.R;
import com.javaya.pruebawposs.database.DataBase;
import com.javaya.pruebawposs.models.Payment;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView name, total, share, numberCard, company;
    Payment payment = new Payment();
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        name = findViewById(R.id.name);
        total = findViewById(R.id.total);
        share = findViewById(R.id.share);
        numberCard = findViewById(R.id.numberCard);
        company = findViewById(R.id.company);

        Bundle bundle = this.getIntent().getExtras();
        String companyCard = "";

        if(bundle != null){
            payment.setNumberCard(bundle.getString("numberCard"));
            payment.setName(bundle.getString("name"));
            payment.setPaymentValue(bundle.getString("payment"));
            payment.setShare(bundle.getString("share"));
            payment.setDate(bundle.getString("date"));
            companyCard = bundle.getString("company");
        }

        numberCard.setText("************" + payment.getNumberCard().substring(payment.getNumberCard().length()-4, payment.getNumberCard().length()));
        name.setText(payment.getName());
        total.setText(payment.getPaymentValue() + " USD");
        share.setText("A (" + payment.getShare() + ") Cuotas");
        company.setText(companyCard);

    }

    public void cancel(View view) {

        Intent intent = new Intent(ConfirmationActivity.this, FailedActivity.class);
        startActivity(intent);

    }

    public void confirm(View view) {

        DataBase db = new DataBase(getApplicationContext());
        boolean answer = db.addPayment(payment);

        if(answer){
            Intent intent = new Intent(ConfirmationActivity.this, SuccesfulActivity.class);
            startActivity(intent);
        }

    }
}