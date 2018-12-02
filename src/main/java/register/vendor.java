/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import com.git.core.Recipient;
import com.git.dbcon.AESencrp;
import com.git.dbcon.DateManipulation;
import com.git.dbcon.DbConnectionX;
import com.git.getitdone.SelectOptionMenu;
import com.git.register.Recipient1;
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
import java.sql.SQLException;
import javax.json.JsonObject;
import org.codehaus.jackson.map.ObjectMapper;


/**
 *
 * @author Gold
 */
@ManagedBean
@ViewScoped
public class vendor implements Serializable {

    private VendorModel vendor = new VendorModel();
    private String messangerOfTruth;
    private SelectOptionMenu menu = new SelectOptionMenu();

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

    public int studentIdCheck(Connection con) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String testflname = "Select * from tbvendor order by id DESC LIMIT 1";
        pstmt = con.prepareStatement(testflname);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");
        }
        return 0;
    }

    public void registerVendor() {
        FacesContext context = FacesContext.getCurrentInstance();
        DbConnectionX dbConnections = new DbConnectionX();
        Recipient receipt = new Recipient();
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
            } else if (checkAccountExist(vendor.getAcctNum())) {
                setMessangerOfTruth("Account Number already exists!!");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessangerOfTruth(), getMessangerOfTruth());
                context.addMessage(null, msg);
            } else {

                JSONObject bn = receipt.createRecipient(menu.categoryName(vendor.getCat()) + "'s " + vendor.getBname(), vendor.getBname(), vendor.getAcctNum(), vendor.getBankname(), new JSONObject().put("job", vendor.getBname()).toString());
                ObjectMapper mapp = new ObjectMapper();
                Recipient1 dat = mapp.readValue(bn.toString(), Recipient1.class);
                System.out.println("yeah" + bn.toString());
                System.out.println("yeah" + dat.getMessage()+" status: "+dat.isStatus());
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

        System.out.println(jsonString + " i was here");
    }

    public String getMessangerOfTruth() {
        return messangerOfTruth;
    }

    public void setMessangerOfTruth(String messangerOfTruth) {
        this.messangerOfTruth = messangerOfTruth;
    }

}
