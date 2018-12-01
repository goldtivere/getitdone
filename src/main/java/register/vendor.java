/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import com.git.dbcon.AESencrp;
import com.git.dbcon.DateManipulation;
import com.git.dbcon.DbConnectionX;
import com.git.register.UserDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.json.JSONObject;
import java.io.Serializable;

/**
 *
 * @author Gold
 */
@ManagedBean
@ViewScoped
public class vendor implements Serializable{

    private VendorModel vendor = new VendorModel();
    private String messangerOfTruth;

    public VendorModel getVendor() {
        return vendor;
    }

    public void setVendor(VendorModel vendor) {
        this.vendor = vendor;
    }

    public void refresh() {
        vendor.setAcctName("");
        vendor.setAcctNum("");
        vendor.setBaddress("");
        vendor.setBname("");
        vendor.setBpnum("");
        vendor.setFname("");
        vendor.setLname("");
        vendor.setMname("");
        vendor.setRcnum("");

    }

    public boolean checkRcExist(String rcnum) {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = dbConnections.mySqlDBconnection();
        try {
            String queryProfile = "select * from tbvendor "
                    + "where rcnumber=? and isdeleted=?";
            pstmt = con.prepareStatement(queryProfile);
            pstmt.setString(1, rcnum);
            pstmt.setBoolean(2, false);
            rs = pstmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    
     public boolean checkAccountExist(String acctnum) {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = dbConnections.mySqlDBconnection();
        try {
            String queryProfile = "select * from tbvendor "
                    + "where accountnumber=? and isdeleted=?";
            pstmt = con.prepareStatement(queryProfile);
            pstmt.setString(1, acctnum);
            pstmt.setBoolean(2, false);
            rs = pstmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean checkBusinessExist(String business) {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = dbConnections.mySqlDBconnection();
        try {
            String queryProfile = "select * from tbvendor "
                    + "where corporatename=? and isdeleted=?";
            pstmt = con.prepareStatement(queryProfile);
            pstmt.setString(1, business);
            pstmt.setBoolean(2, false);
            rs = pstmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void registerVendor() {
        FacesContext context = FacesContext.getCurrentInstance();
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FacesMessage msg;
        try {
            con = dbConnections.mySqlDBconnection();
            UserDetails userObj = (UserDetails) context.getExternalContext().getSessionMap().get("sessn_nums");
            String on = String.valueOf(userObj);

            if (userObj == null) {
                setMessangerOfTruth("Expired Session, pleasere - login " + on);
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        getMessangerOfTruth(), getMessangerOfTruth());
                context.addMessage(null, msg);
            }
            System.out.println(checkBusinessExist(vendor.getBname()) + "and" + checkRcExist(vendor.getRcnum()));
            if (checkBusinessExist(vendor.getBname())) {

                setMessangerOfTruth("Business name already exists!!");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessangerOfTruth(), getMessangerOfTruth());
                context.addMessage(null, msg);

            } else if (checkRcExist(vendor.getRcnum())) {
                setMessangerOfTruth("RC Number already exists!!");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessangerOfTruth(), getMessangerOfTruth());
                context.addMessage(null, msg);
            }
             else if (checkAccountExist(vendor.getAcctNum())) {
                setMessangerOfTruth("Account Number already exists!!");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessangerOfTruth(), getMessangerOfTruth());
                context.addMessage(null, msg);
            }else {
                String insert = "insert into tbvendor (firstname,middlename,lastname,fullname,phonenumber,corporatename,accountnumber,accountname,address,createdby,datecreated,isdeleted,rcnumber)"
                        + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pstmt = con.prepareStatement(insert);

                pstmt.setString(1, vendor.getFname());
                pstmt.setString(2, vendor.getMname());
                pstmt.setString(3, vendor.getLname());
                pstmt.setString(4, vendor.getLname() + " " + vendor.getMname() + " " + vendor.getFname());
                pstmt.setString(5, vendor.getBpnum());
                pstmt.setString(6, vendor.getBname());
                pstmt.setString(7, vendor.getAcctNum());
                pstmt.setString(8, vendor.getAcctName());
                pstmt.setString(9, vendor.getBaddress());
                pstmt.setInt(10, userObj.getId());
                pstmt.setString(11, DateManipulation.dateAndTime());
                pstmt.setBoolean(12, false);
                pstmt.setString(13, vendor.getRcnum());
                pstmt.executeUpdate();
                refresh();
                setMessangerOfTruth("Vendor Created!!");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessangerOfTruth(), getMessangerOfTruth());
                context.addMessage(null, msg);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void testJson() {
        String jsonString = new JSONObject()
                .put("JSON1", "Hello World!")
                .put("JSON2", "Hello my World!")
                .put("JSON3", new JSONObject()
                        .put("key1", "value1")).toString();

        System.out.println(jsonString+ " i was here");
    }

    public String getMessangerOfTruth() {
        return messangerOfTruth;
    }

    public void setMessangerOfTruth(String messangerOfTruth) {
        this.messangerOfTruth = messangerOfTruth;
    }

}
