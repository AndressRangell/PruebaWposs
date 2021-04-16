package com.javaya.pruebawposs.models;

public class Transaction {

    String numberCard;
    String name;
    String value;
    String typeTransaction;
    String date;

    public Transaction(String numberCard, String name, String value, String typeTransaction, String date) {
        this.numberCard = numberCard;
        this.name = name;
        this.value = value;
        this.typeTransaction = typeTransaction;
        this.date = date;
    }

    public Transaction() {
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
