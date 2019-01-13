/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.TransferRequest;

/**
 *
 * @author Gold
 */
public class TransferinitiateModel {
    private int id;
    private int vendorfk;
    private String ref;
    public static final String Balance="balance";
    private double amount;
    private double percent;
    private String recipientCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVendorfk() {
        return vendorfk;
    }

    public void setVendorfk(int vendorfk) {
        this.vendorfk = vendorfk;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public String getRecipientCode() {
        return recipientCode;
    }

    public void setRecipientCode(String recipientCode) {
        this.recipientCode = recipientCode;
    }
    
}
