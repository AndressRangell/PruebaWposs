package com.javaya.pruebawposs.models;

public class Payment {

    String numberCard = "111";
    String name;
    String paymentValue;
    String share = "0";
    String date;

    public Payment(String numberCard, String name, String paymentValue, String share, String date) {
        this.numberCard = numberCard;
        this.name = name;
        this.paymentValue = paymentValue;
        this.share = share;
        this.date = date;
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

    public String getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(String paymentValue) {
        this.paymentValue = paymentValue;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Payment() {
    }
}
