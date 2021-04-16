package com.javaya.pruebawposs.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.javaya.pruebawposs.R;
import com.javaya.pruebawposs.database.DataBase;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    private TextView deposits, drawals, payments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        deposits = findViewById(R.id.deposits);
        drawals = findViewById(R.id.drawals);
        payments = findViewById(R.id.payments);

        DataBase db = new DataBase(getApplicationContext());
        ArrayList<Integer> report =  db.getReport();

        deposits.setText("Depositos: " + report.get(0));
        drawals.setText("Retiros: " + report.get(1));
        payments.setText("Pagos: " + report.get(2));

    }

    public void back(View view) {

        finish();

    }
}