/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.filter;

/**
 *
 * @author Gold
 */
public class Smsmodel {

    private int id;
    private String trxnref;
    private double amount;
    private boolean paid;
    private boolean trxncomp;
    private boolean trxnpaid;
    private boolean smssent;
    private String smscontent;
    private String phone;
    private String vendorPhone;
    private String vendorMessage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrxnref() {
        return trxnref;
    }

    public void setTrxnref(String trxnref) {
        this.trxnref = trxnref;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isTrxncomp() {
        return trxncomp;
    }

    public void setTrxncomp(boolean trxncomp) {
        this.trxncomp = trxncomp;
    }

    public boolean isTrxnpaid() {
        return trxnpaid;
    }

    public void setTrxnpaid(boolean trxnpaid) {
        this.trxnpaid = trxnpaid;
    }

    public boolean isSmssent() {
        return smssent;
    }

    public void setSmssent(boolean smssent) {
        this.smssent = smssent;
    }

    public String getSmscontent() {
        return smscontent;
    }

    public void setSmscontent(String smscontent) {
        this.smscontent = smscontent;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    public String getVendorMessage() {
        return vendorMessage;
    }

    public void setVendorMessage(String vendorMessage) {
        this.vendorMessage = vendorMessage;
    }

}
