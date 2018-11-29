/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.getitdone;

import java.io.Serializable;

/**
 *
 * @author Gold
 */
public class BankModel implements Serializable{
    private int id;
    private String bankName;
    private String bankcode;
    private String bankLongCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public String getBankLongCode() {
        return bankLongCode;
    }

    public void setBankLongCode(String bankLongCode) {
        this.bankLongCode = bankLongCode;
    }
    
    
}
