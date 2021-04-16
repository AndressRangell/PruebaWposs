package com.javaya.pruebawposs.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.javaya.pruebawposs.MyAdapterUsers;
import com.javaya.pruebawposs.R;
import com.javaya.pruebawposs.database.DataBase;
import com.javaya.pruebawposs.models.User;

import java.util.ArrayList;

public class ListUsersActivity extends AppCompatActivity {

    ArrayList<User> userList;
    RecyclerView recyclerUsers;
    MyAdapterUsers myAdapter;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        db = new DataBase(getApplicationContext());
        userList = new ArrayList<>();
        recyclerUsers = findViewById(R.id.recyclerUsers);
        recyclerUsers.setLayoutManager(new LinearLayoutManager(this));
        userList = db.listUsers();

        myAdapter = new MyAdapterUsers(userList, this);

        //metodo para sobrescribir el metodo onclicklistener y generar la escucha dependiendo del item seleccionado
        myAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userList.get(recyclerUsers.getChildAdapterPosition(view)).getStatus().equals("habilitado")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ListUsersActivity.this);
                    builder.setMessage("¿Desea deshabilitar este usuario?").setPositiveButton("Deshabilitar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DataBase db = new DataBase(getApplicationContext());
                            db.changeStatus(userList.get(recyclerUsers.getChildAdapterPosition(view)).getIdUser(), "deshabilitar");
                            finish();
                            startActivity(getIntent());
                        }
                    });
                    builder.show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(ListUsersActivity.this);
                    builder.setMessage("¿Desea habilitar este usuario?").setPositiveButton("Habilitar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DataBase db = new DataBase(getApplicationContext());
                            db.changeStatus(userList.get(recyclerUsers.getChildAdapterPosition(view)).getIdUser(), "habilitado");
                            finish();
                            startActivity(getIntent());
                        }
                    });
                    builder.show();
                }
            }
        });

        recyclerUsers.setAdapter(myAdapter);

    }

    public void back(View view) {

        finish();

    }
}