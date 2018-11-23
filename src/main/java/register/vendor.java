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

/**
 *
 * @author Gold
 */
@ManagedBean
@ViewScoped
public class vendor {

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

    }

    public boolean checkRcExist() {

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
            String insert = "insert into tbvendor (firstname,middlename,lastname,fullname,phonenumber,corporatename,accountnumber,accountname,address,createdby,datecreated,isdeleted)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pstmt.executeUpdate();
            refresh();
            setMessangerOfTruth("Vendor Created!!");
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessangerOfTruth(), getMessangerOfTruth());
            context.addMessage(null, msg);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public String getMessangerOfTruth() {
        return messangerOfTruth;
    }

    public void setMessangerOfTruth(String messangerOfTruth) {
        this.messangerOfTruth = messangerOfTruth;
    }

}
