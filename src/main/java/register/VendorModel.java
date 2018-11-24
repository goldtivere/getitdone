/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import java.io.Serializable;

/**
 *
 * @author Gold
 */
public class VendorModel implements Serializable {

    private int id;
    private String fname;
    private String mname;
    private String lname;
    private String bname;
    private String bpnum;
    private String baddress;
    private String acctNum;
    private String acctName;
    private String rcnum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBpnum() {
        return bpnum;
    }

    public void setBpnum(String bpnum) {
        this.bpnum = bpnum;
    }

    public String getBaddress() {
        return baddress;
    }

    public void setBaddress(String baddress) {
        this.baddress = baddress;
    }

    public String getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(String acctNum) {
        this.acctNum = acctNum;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getRcnum() {
        return rcnum;
    }

    public void setRcnum(String rcnum) {
        this.rcnum = rcnum;
    }

}
