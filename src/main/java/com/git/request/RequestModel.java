/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.request;

import java.io.Serializable;

/**
 *
 * @author Gold
 */
public class RequestModel implements Serializable{
    private int vendorfk;
    private String corporatename;
    private double amount;
    private String requestedId;
    private boolean requestStatus;
    private boolean completed;
    private String requestStat;
    private String coverageLocation;
    private boolean select;
    private int catId;
    private boolean edit;
    private double finalSum;
    private boolean trxnpaid;

    public int getVendorfk() {
        return vendorfk;
    }

    public void setVendorfk(int vendorfk) {
        this.vendorfk = vendorfk;
    }

    public String getCorporatename() {
        return corporatename;
    }

    public void setCorporatename(String corporatename) {
        this.corporatename = corporatename;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRequestedId() {
        return requestedId;
    }

    public void setRequestedId(String requestedId) {
        this.requestedId = requestedId;
    }

    public boolean isRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getRequestStat() {
        return requestStat;
    }

    public void setRequestStat(String requestStat) {
        this.requestStat = requestStat;
    }

    public String getCoverageLocation() {
        return coverageLocation;
    }

    public void setCoverageLocation(String coverageLocation) {
        this.coverageLocation = coverageLocation;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public double getFinalSum() {
        return finalSum;
    }

    public void setFinalSum(double finalSum) {
        this.finalSum = finalSum;
    }

    public boolean isTrxnpaid() {
        return trxnpaid;
    }

    public void setTrxnpaid(boolean trxnpaid) {
        this.trxnpaid = trxnpaid;
    }
     
    
}
