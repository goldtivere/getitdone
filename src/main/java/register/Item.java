/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import com.git.core.Recipient;
import com.git.dbcon.DateManipulation;
import com.git.dbcon.DbConnectionX;
import com.git.getitdone.LocationModel;
import com.git.getitdone.SelectOptionMenu;
import com.git.register.UserDetails;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class Item implements Serializable {

    private ItemModel mode = new ItemModel();
    private String bname;
    private String messangerOfTruth;
    private List<LocationModel> listMode;
    private SelectOptionMenu menu = new SelectOptionMenu();

    @PostConstruct
    public void init() {
        try {
            listMode = menu.Location();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ItemModel getMode() {
        return mode;
    }

    public void setMode(ItemModel mode) {
        this.mode = mode;
    }

    public void refresh() {
        mode.setAgentPercentage(0);
        mode.setAmount(0);
        mode.setItemDescritpion(null);
        mode.setItemname(null);
        mode.setLocationfk(0);
        setBname(null);
        mode.setCategoryfk(0);

    }

    public List<VendorModel> displayVendor() throws SQLException {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {

            con = dbConnections.mySqlDBconnection();
            String query = "SELECT * from tbvendor where isdeleted=false";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            //
            List<VendorModel> lst = new ArrayList<>();
            while (rs.next()) {

                VendorModel coun = new VendorModel();
                coun.setId(rs.getInt("id"));
                coun.setBname(rs.getString("corporatename"));

                //
                lst.add(coun);
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {

            if (!(con == null)) {
                con.close();
                con = null;
            }
            if (!(pstmt == null)) {
                pstmt.close();
                pstmt = null;
            }

        }
    }

    public List<String> completeVendor(String val) {
        List<String> com = new ArrayList<>();
        try {
            for (VendorModel value : displayVendor()) {
                if (value.getBname().toUpperCase().contains(val.toUpperCase())) {

                    com.add(value.getBname());
                }

            }
            return com;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }

    public int vendorfk(String bname) throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = dbConnections.mySqlDBconnection();
            String query = "SELECT * FROM tbvendor where corporatename=? and isdeleted=false";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, bname);
            rs = pstmt.executeQuery();
            //

            if (rs.next()) {

                return rs.getInt("id");

            }

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;

        } finally {

            if (!(con == null)) {
                con.close();
                con = null;
            }
            if (!(pstmt == null)) {
                pstmt.close();
                pstmt = null;
            }

        }
    }

    public void saveItem() throws SQLException {
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
            int createdby = userObj.getId();
            if (userObj != null) {
                int id = vendorfk(getBname());
                String insertemail = "insert into tbvendoritem (vendorfk,category,itemname,amount,description,locationfk,agentpercentage,datecreated,datetime,createdby)"
                        + "values(?,?,?,?,?,?,?,?,?,?)";

                pstmt = con.prepareStatement(insertemail);
                pstmt.setInt(1, id);
                pstmt.setInt(2, mode.getCategoryfk());
                pstmt.setString(3, mode.getItemname());
                pstmt.setDouble(4, mode.getAmount());
                pstmt.setString(5, mode.getItemDescritpion());
                pstmt.setInt(6, mode.getLocationfk());
                pstmt.setDouble(7, mode.getAgentPercentage());
                pstmt.setString(8, DateManipulation.dateAlone());
                pstmt.setString(9, DateManipulation.dateAndTime());
                pstmt.setInt(10, createdby);

                pstmt.executeUpdate();
                setMessangerOfTruth("Item Saved ");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        getMessangerOfTruth(), getMessangerOfTruth());
                context.addMessage(null, msg);
                refresh();
            } else {
                setMessangerOfTruth("Expired Session, please - login " + on);
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        getMessangerOfTruth(), getMessangerOfTruth());
                context.addMessage(null, msg);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (!(con == null)) {
                con.close();
                con = null;
            }
            if (!(pstmt == null)) {
                pstmt.close();
                pstmt = null;
            }

        }

    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getMessangerOfTruth() {
        return messangerOfTruth;
    }

    public void setMessangerOfTruth(String messangerOfTruth) {
        this.messangerOfTruth = messangerOfTruth;
    }

    public List<LocationModel> getListMode() {
        return listMode;
    }

    public void setListMode(List<LocationModel> listMode) {
        this.listMode = listMode;
    }

}
