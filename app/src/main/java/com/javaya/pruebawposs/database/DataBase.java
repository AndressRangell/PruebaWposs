package com.javaya.pruebawposs.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.javaya.pruebawposs.models.Client;
import com.javaya.pruebawposs.models.Payment;
import com.javaya.pruebawposs.models.Transaction;
import com.javaya.pruebawposs.models.User;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    private Context context;
    private static final String name = "prueba.db";
    private static final int version = 1;

    private static final String table_user = "user";
    private static final String column_id_user = "id_user";
    private static final String column_card_user = "card_user";
    private static final String column_count_user = "count_user";
    private static final String column_name_user = "name_user";
    private static final String column_email_user = "email_user";
    private static final String column_password_user = "password_user";
    private static final String column_balance_user = "balance_user";
    private static final String column_status_user = "status_user";

    private static final String table_payment = "payment";
    private static final String column_id_payment = "id_payment";
    private static final String column_card_payment = "card_payment";
    private static final String column_name_payment = "name_payment";
    private static final String column_value_payment = "value_payment";
    private static final String column_share_payment = "share_payment";
    private static final String column_date_payment = "date_payment";

    private static final String table_transaction = "operation";
    private static final String column_id_transaction = "id_transaction";
    private static final String column_card_transaction = "card_transaction";
    private static final String column_name_transaction = "name_transaction";
    private static final String column_value_transaction = "value_transaction";
    private static final String column_type_transaction = "type_transaction";
    private static final String column_date_transaction = "date_transaction";

    private static final String table_client = "client";
    private static final String column_id_client = "id_client";
    private static final String column_name_client = "name_client";
    private static final String column_card_client = "card_client";
    private static final String column_document_client = "document_client";
    private static final String column_pin_client = "pin_client";
    private static final String column_balance_client = "balance_client";

    public DataBase(@Nullable Context context) {
        super(context, name, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryUser = "Create table " + table_user + "(" +
            column_id_user + " integer primary key autoincrement," +
            column_card_user + " integer unique," +
            column_count_user + " integer unique," +
            column_name_user + " text," +
            column_email_user + " text unique," +
            column_password_user + " text," +
            column_balance_user + " integer," +
            column_status_user + " text);";
        db.execSQL(queryUser);

        String queryPayment = "Create table " + table_payment + "(" +
                column_id_payment + " integer primary key autoincrement," +
                column_card_payment + " integer," +
                column_name_payment + " text," +
                column_value_payment + " integer," +
                column_share_payment + " integer," +
                column_date_payment + " text);";
        db.execSQL(queryPayment);


        String queryTransaction = "Create table " + table_transaction + "(" +
                column_id_transaction + " integer primary key autoincrement," +
                column_card_transaction + " integer," +
                column_name_transaction + " text," +
                column_value_transaction + " integer," +
                column_type_transaction + " text," +
                column_date_transaction + " text);";
        db.execSQL(queryTransaction);

        String queryClient = "Create table " + table_client + "(" +
                column_id_client + " integer primary key," +
                column_name_client + " text," +
                column_card_client + " integer unique," +
                column_document_client + " integer unique," +
                column_pin_client + " integer," +
                column_balance_client + " integer);";
        db.execSQL(queryClient);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_user);
        db.execSQL("DROP TABLE IF EXISTS " + table_payment);
        db.execSQL("DROP TABLE IF EXISTS " + table_transaction);
        db.execSQL("DROP TABLE IF EXISTS " + table_client);
        onCreate(db);
    }

    /**
     * método para añadir usuario en db
     * @param user
     */

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_id_user, user.getIdUser());
        cv.put(column_card_user, user.getCard());
        cv.put(column_count_user, user.getCount());
        cv.put(column_name_user, user.getName());
        cv.put(column_email_user, user.getEmail());
        cv.put(column_password_user, user.getPassword());
        cv.put(column_balance_user, user.getBalance());
        cv.put(column_status_user, user.getStatus());
        long result = db.insert(table_user, null, cv);
        if (result == -1 && !user.getIdUser().equals("1")) {
            Toast.makeText(context, "Documento o correo ya registrado", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * método para añadir nuevo pago en db
     * @param payment
     * @return true > agregado con exito. false > error al agregar
     */

    public boolean addPayment(Payment payment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String nulo = null;
        cv.put(column_id_payment, nulo);
        cv.put(column_card_payment, payment.getNumberCard());
        cv.put(column_name_payment, payment.getName());
        cv.put(column_value_payment, payment.getPaymentValue());
        cv.put(column_share_payment, payment.getShare());
        cv.put(column_date_payment, payment.getDate());
        long result = db.insert(table_payment, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Error al registrar pago", Toast.LENGTH_SHORT).show();
        }else{
            return true;
        }
        return false;
    }

    /**
     * método para añadir registro de transacción
     * @param transaction
     * @param count cuenta de usuario admin
     * @return true > agregado con exito. false > error al agregar
     */

    public boolean addTransaction(Transaction transaction, String count){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String nulo = null;
        cv.put(column_id_transaction, nulo);
        cv.put(column_card_transaction, transaction.getNumberCard());
        cv.put(column_name_transaction, transaction.getName());
        cv.put(column_value_transaction, transaction.getValue());
        cv.put(column_type_transaction, transaction.getTypeTransaction());
        cv.put(column_date_transaction, transaction.getDate());
        long result = db.insert(table_transaction, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Error al registrar transacción", Toast.LENGTH_SHORT).show();
        }else{
            changeBalance(transaction.getNumberCard(), count, transaction.getValue(), transaction.getTypeTransaction());
            return true;
        }
        return false;
    }

    /**
     * método para añadir nuevo cliente
     * @param client
     * @return true > agregado con exito. false > error al agregar
     */

    public boolean addClient(Client client) {
        boolean process = false;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_id_client, client.getId());
        cv.put(column_name_client, client.getName());
        cv.put(column_card_client, client.getCard());
        cv.put(column_document_client, client.getDocument());
        cv.put(column_pin_client, client.getPIN());
        cv.put(column_balance_client, client.getBalance());
        long result = db.insert(table_client, null, cv);
        if (result == -1 && !client.getId().equals("1")) {
            Toast.makeText(context, "Documento o correo ya registrado", Toast.LENGTH_SHORT).show();
        }
        return process;
    }

    /**
     * metodo para validar usuario en login
     * @param email correo ingresado
     * @param password contraseña ingresada
     * @return User > datos correctos. null > datos incorrectos
     */

    public User validateUser (String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + table_user  + " WHERE " + column_email_user + " = '" + email
                + "' AND " + column_password_user + " = '" + password + "'";
        User user = new User();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()){
                user.setIdUser(cursor.getString(0));
                user.setCard(cursor.getString(1));
                user.setCount(cursor.getString(2));
                user.setName(cursor.getString(3));
                user.setEmail(cursor.getString(4));
                user.setPassword(cursor.getString(5));
                user.setBalance(cursor.getInt(6));
                user.setStatus(cursor.getString(7));
            }
            if(user.getStatus().equals("habilitado")){
                return user;
            }
        }
        return null;
    }

    /**
     * metodo para listar todos los usuarios registrados
     * @return retorna lista de usuarios
     */

    public ArrayList<User> listUsers(){
        ArrayList<User> userList = new ArrayList<User>();
        User user = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + table_user + " WHERE " + column_id_user + " != 1";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                user = new User();
                user.setIdUser(cursor.getString(0));
                user.setCard(cursor.getString(1));
                user.setCount(cursor.getString(2));
                user.setName(cursor.getString(3));
                user.setEmail(cursor.getString(4));
                user.setPassword(cursor.getString(5));
                user.setBalance(cursor.getInt(6));
                user.setStatus(cursor.getString(7));
                userList.add(user);
            }
        }
        return userList;
    }

    /**
     * metodo para listar las transacciones registradas
     * @return retorna lista de de transacciones
     */

    public ArrayList<Transaction> listTransactions(){
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
        Transaction transaction = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + table_transaction;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                transaction = new Transaction();
                transaction.setNumberCard(cursor.getString(1));
                transaction.setName(cursor.getString(2));
                transaction.setValue(cursor.getString(3));
                transaction.setTypeTransaction(cursor.getString(4));
                transaction.setDate(cursor.getString(5));
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }

    /**
     * metodo para calcular la suma de (depositos, retiros, pagos)
     * @return retorna lista de enteros (total depositos, total retiros, total pagos)
     */

    public ArrayList<Integer> getReport(){
        ArrayList<Integer> report = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();

        String queryDeposits = "SELECT type_transaction, SUM(" + column_value_transaction + ") FROM " + table_transaction + " WHERE " + column_type_transaction
                + " = 'deposito'";
        Cursor cursor = db.rawQuery(queryDeposits, null);
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                report.add(cursor.getInt(1));
            }
        }else{
            report.add(0);
        }

        String queryDrawals = "SELECT type_transaction, SUM(" + column_value_transaction + ") FROM " + table_transaction + " WHERE " + column_type_transaction
                + " = 'retiro'";
        cursor = db.rawQuery(queryDrawals, null);
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                report.add(cursor.getInt(1));
            }
        }else{
            report.add(0);
        }

        String queryPayments = "SELECT 'suma', SUM(" + column_value_payment + ") FROM " + table_payment;
        cursor = db.rawQuery(queryPayments, null);
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                report.add(cursor.getInt(1));
            }
        }else{
            report.add(0);
        }

        return report;
    }

    /**
     * metodo para cambiar estado de los usuarios (habilitado - deshabilitado)
     * @param idUser id del usuario a modificar
     * @param status estado actual
     */

    public void changeStatus(String idUser, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_status_user, status);
        String[] id = {idUser};
        db.update(table_user, cv, column_id_user + " = ?", id);
    }

    /**
     * metodo para actualizar saldo de usuario y cliente al hacer transaccion
     * @param card numero de tarjeta de cliente
     * @param count cuenta de usuario encargado
     * @param value valor de la transaccion
     * @param type tipo de transaccion (deposito - retiro)
     */

    public void changeBalance(String card, String count, String value, String type){

        SQLiteDatabase db = this.getWritableDatabase();
        int newValue = Integer.parseInt(value);

        if(type.equals("deposito")){

            db.execSQL("UPDATE user SET balance_user = balance_user + " + newValue + " WHERE count_user = " + count);
            db.execSQL("UPDATE client SET balance_client = balance_client + " + newValue + " WHERE card_client = " + card);
            db.close();

        }else{

            db.execSQL("UPDATE user SET balance_user = balance_user - " + newValue + " WHERE count_user = " + count);
            db.execSQL("UPDATE client SET balance_client = balance_client - " + newValue + " WHERE card_client = " + card);
            db.close();

        }

    }

    /**
     * metodo para validar si el cliente y el punto de corresponsal tienen saldo
     * @param count cuenta de usuario encargado
     * @param numberCard numero de tarjeta cliente
     * @param value valor de retiro
     * @param password contraseña cliente
     * @return
     */

    public boolean validateDrawal(String count, String numberCard, String value, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM user, client WHERE count_user = " + count + " AND card_client = " + numberCard +
                " AND pin_client = " + password + " AND balance_user > " + value + " AND balance_client > " + value;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() != 0){
            return true;
        }
        return false;

    }

    /**
     * metodo para validar tarjeta de credito
     * @param numberCard numero tarjeta de cliente
     * @return true > si la cuenta existe. false > si no existe
     */

    public boolean validateCard(String numberCard){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM client WHERE card_client = " + numberCard;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() != 0){
            return true;
        }
        return false;

    }

}
