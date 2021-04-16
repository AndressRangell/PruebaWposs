package com.javaya.pruebawposs.models;

public class User {

    private String idUser;
    private String card;
    private String count;
    private String name;
    private String email;
    private String password;
    private int balance;
    private String status;

    public User(String idUser, String card, String count, String name, String email, String password, int balance, String status) {
        this.idUser = idUser;
        this.card = card;
        this.count = count;
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.status = status;
    }

    public User() {
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
