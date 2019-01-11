/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.transaction;

import java.util.Date;

/**
 *
 * @author Gold
 */
public class TrxnModel {
    private int id;
    private int vendorfk;
    private String ref;
    private String vendorName;
    private double amount;
    private Date datecompleted;
    private double percent;
    private Date datedelivered;

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

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDatecompleted() {
        return datecompleted;
    }

    public void setDatecompleted(Date datecompleted) {
        this.datecompleted = datecompleted;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public Date getDatedelivered() {
        return datedelivered;
    }

    public void setDatedelivered(Date datedelivered) {
        this.datedelivered = datedelivered;
    }

    
    
}
