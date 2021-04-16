package com.javaya.pruebawposs.models;

public class Client {

    String id;
    String name;
    String card;
    String document;
    String PIN;
    int balance;

    public Client() {
    }

    public Client(String id, String name, String card, String document, String PIN, int balance) {
        this.id = id;
        this.name = name;
        this.card = card;
        this.document = document;
        this.PIN = PIN;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
