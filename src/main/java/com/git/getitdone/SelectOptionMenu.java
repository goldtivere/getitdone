/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.getitdone;

import com.git.dbcon.DbConnectionX;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gold
 */
@ManagedBean(name = "drop")
@ViewScoped
public class SelectOptionMenu implements Serializable {

    private List<BankModel> bankMode;

    @PostConstruct
    public void init() {
        try {
            bankMode = dropBank();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //category select menu method
    public List<CategoryModel> dropCategory() throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = dbConnections.mySqlDBconnection();
            String query = "SELECT * FROM tbcategory";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            //
            List<CategoryModel> lst = new ArrayList<>();
            while (rs.next()) {

                CategoryModel coun = new CategoryModel();
                coun.setId(rs.getInt("id"));
                coun.setCategory(rs.getString("category"));
                coun.setDescription(rs.getString("description"));
                coun.setImagelink(rs.getString("imagelink"));
                coun.setImagelocation(rs.getString("imagelocation"));

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

    //gets list of bank
    public List<BankModel> dropBank() throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = dbConnections.mySqlDBconnection();
            String query = "SELECT * FROM tbbank";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            //
            List<BankModel> lst = new ArrayList<>();
            while (rs.next()) {

                BankModel coun = new BankModel();
                coun.setId(rs.getInt("id"));
                coun.setBankName(rs.getString("bankname"));
                coun.setBankcode(rs.getString("bankcode"));
                coun.setBankLongCode(rs.getString("longcode"));

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

    public List<LocationModel> Location() throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = dbConnections.mySqlDBconnection();
            String query = "SELECT * FROM tblocation";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            //
            List<LocationModel> lst = new ArrayList<>();
            while (rs.next()) {

                LocationModel coun = new LocationModel();
                coun.setId(rs.getInt("id"));
                coun.setLocationName(rs.getString("locationname"));

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

    public String Location(int locationfk) throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = dbConnections.mySqlDBconnection();
            String query = "SELECT * FROM tblocation where id=?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, locationfk);
            rs = pstmt.executeQuery();
            //
            List<LocationModel> lst = new ArrayList<>();
            if (rs.next()) {

                return rs.getString("locationname");

            }

            return null;
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

    public String categoryName(int id) throws SQLException {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = dbConnections.mySqlDBconnection();
            String query = "SELECT * FROM tbcategory where id=?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            //
            if (rs.next()) {
                return rs.getString("category");
            }
            return null;

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

    public String bankName(String bankcode) throws SQLException {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = dbConnections.mySqlDBconnection();
            String query = "SELECT * FROM tbbank where bankcode=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, bankcode);
            rs = pstmt.executeQuery();
            //
            if (rs.next()) {
                return rs.getString("bankname");
            }
            return null;

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

    public List<TrxnStatusModel> trxnStatus() throws SQLException {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = dbConnections.mySqlDBconnection();
            String query = "SELECT * FROM tbtrxnstatus";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            List<TrxnStatusModel> model = new ArrayList<>();
            //
            while (rs.next()) {
                TrxnStatusModel mode = new TrxnStatusModel();
                mode.setId(rs.getInt("id"));
                mode.setStatus(rs.getString("status"));
                model.add(mode);
            }
            return model;

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

    public List<BankModel> getBankMode() {
        return bankMode;
    }

    public void setBankMode(List<BankModel> bankMode) {
        this.bankMode = bankMode;
    }

}
