/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.getitdone;

import com.git.dbcon.DbConnectionX;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class SelectOptionMenu {
    private List<CategoryModel> mode;
    private List<BankModel> bankMode;
    
    @PostConstruct
    public void init()
    {
        try
        {
        mode=dropCategory();
        bankMode=dropBank();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //category select menu method
    public List<CategoryModel> dropCategory() throws Exception
    {
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
      public List<BankModel> dropBank() throws Exception
    {
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
            
    public List<CategoryModel> getMode() {
        return mode;
    }

    public void setMode(List<CategoryModel> mode) {
        this.mode = mode;
    }

    public List<BankModel> getBankMode() {
        return bankMode;
    }

    public void setBankMode(List<BankModel> bankMode) {
        this.bankMode = bankMode;
    }
    
    
}
