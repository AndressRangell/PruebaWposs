package com.javaya.pruebawposs.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.javaya.pruebawposs.MyAdapterTransaction;
import com.javaya.pruebawposs.R;
import com.javaya.pruebawposs.database.DataBase;
import com.javaya.pruebawposs.models.Transaction;
import com.javaya.pruebawposs.models.User;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ArrayList<Transaction> transactionList;
    RecyclerView recyclerTransactions;
    MyAdapterTransaction myAdapter;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        db = new DataBase(getApplicationContext());
        transactionList = new ArrayList<>();
        recyclerTransactions = findViewById(R.id.recyclerTransactions);
        recyclerTransactions.setLayoutManager(new LinearLayoutManager(this));
        transactionList = db.listTransactions();

        myAdapter = new MyAdapterTransaction(transactionList, this);

        recyclerTransactions.setAdapter(myAdapter);
    }

    public void back(View view) {

        finish();

    }
}