/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.forgotpassword;

import com.git.dbcon.AESencrp;
import com.git.dbcon.DateManipulation;
import com.git.dbcon.DbConnectionX;
import com.git.dbcon.LoadPPTfile;
import com.git.getitdone.RandomWordEquivalent;
import com.git.getitdone.XMLCreator;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gold
 */
@ManagedBean
@ViewScoped
public class ForgotPassword implements Serializable {

    private String phoneNumber;
    private String messangerOfTruth;
    private boolean firstPanel;
    private boolean secondPanel;
    private String password;
    private String code;

    @PostConstruct
    public void init() {
        setFirstPanel(true);
        setSecondPanel(false);
    }

    public boolean checkIfVerExists(String code, String pnum) throws SQLException {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = dbConnections.mySqlDBconnection();
            String queryProfile = "select * from tbtempregistration "
                    + "where verificationcode=? and phonenumber=? and phoneverified=? order by createdon desc limit 1";
            pstmt = con.prepareStatement(queryProfile);
            pstmt.setString(1, code);
            pstmt.setString(2, pnum);
            pstmt.setBoolean(3, false);
            rs = pstmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

    public boolean checkIfNumExists(String pnum) throws SQLException {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = dbConnections.mySqlDBconnection();
            String queryProfile = "select * from tbregistration "
                    + "where phonenumber=? and isdeleted=?";
            pstmt = con.prepareStatement(queryProfile);
            pstmt.setString(1, pnum);
            pstmt.setBoolean(2, true);
            rs = pstmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {

            if (!(con == null)) {
                con.close();
            }

            if (!(pstmt == null)) {
                pstmt.close();
            }

            if (!(rs == null)) {
                rs.close();
            }

        }

    }

    public void executeForgot() throws SQLException, IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        RandomWordEquivalent ran = new RandomWordEquivalent();
        LoadPPTfile loadPPT = new LoadPPTfile();
        XMLCreator xmlcr = new XMLCreator();
        try {
            con = dbConnections.mySqlDBconnection();
            if (checkIfNumExists(getPhoneNumber())) {
                System.out.println("Hello Gold");
                String lum = ran.generateRandom();
                String num = lum + "forgot.xml";
                String filename = loadPPT.xmlFolder() + num;
                String xmlPath = loadPPT.xmlPath() + num;
                String verificationMessage = " Your verification code is: " + ran.wordEquivalent(lum) + ". Your verification code is: " + ran.wordEquivalent(lum) + ". Thank you.";
                System.out.println(loadPPT.xmlPath() + num + " done***");
                xmlcr.xmlCreate(verificationMessage, loadPPT.xmlPath() + num);
                String insertemail = "insert into tbtempregistration (phonenumber,verified,verificationcode,verificationxml,filename,phoneverified,createdon,xmlfilename,iscalled)"
                        + "values(?,?,?,?,?,?,?,?,?)";
                pstmt = con.prepareStatement(insertemail);

                pstmt.setString(1, getPhoneNumber());
                pstmt.setBoolean(2, false);
                pstmt.setString(3, lum);
                pstmt.setString(4, verificationMessage);
                pstmt.setString(5, filename);
                pstmt.setBoolean(6, false);
                pstmt.setString(7, DateManipulation.dateAndTime());
                pstmt.setString(8, xmlPath);
                pstmt.setBoolean(9, false);
                pstmt.executeUpdate();

                setFirstPanel(false);
                setSecondPanel(true);
                System.out.println("This is it: " + ran.generateRandom());
            } else {
                context.addMessage(null, new FacesMessage("Phone number does not exist!!"));
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

    public void saveUpdate() throws SQLException {

        FacesContext context = FacesContext.getCurrentInstance();
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dbConnections.mySqlDBconnection();

            String pass = AESencrp.encrypt(getPassword());
            if (checkIfVerExists(getCode(), getPhoneNumber())) {
                String updat = "update tbregistration set password=?,dateupdated=?,datetimeupdated=? where phonenumber=?"
                        + " and isdeleted=?";
                pstmt = con.prepareStatement(updat);

                pstmt.setString(1, getPassword());
                pstmt.setString(2, DateManipulation.dateAlone());
                pstmt.setString(3, DateManipulation.dateAndTime());
                pstmt.setString(4, getPhoneNumber());
                pstmt.setBoolean(5, false);
                pstmt.executeUpdate();

                String update = "update tbtempregistration set phoneverified=? where phonenumber=? and verificationcode=? order by createdon desc limit 1";
                pstmt = con.prepareStatement(update);

                pstmt.setBoolean(1, true);
                pstmt.setString(2, getPhoneNumber());
                pstmt.setString(3, getCode());
                pstmt.executeUpdate();

                NavigationHandler nav = context.getApplication().getNavigationHandler();

                String url_ = "/pages/success/success.xhtml?faces-redirect=true";
                nav.handleNavigation(context, null, url_);
                context.renderResponse();

            } else {
                context.addMessage(null, new FacesMessage("incorrect verification code!!"));
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessangerOfTruth() {
        return messangerOfTruth;
    }

    public void setMessangerOfTruth(String messangerOfTruth) {
        this.messangerOfTruth = messangerOfTruth;
    }

    public boolean isFirstPanel() {
        return firstPanel;
    }

    public void setFirstPanel(boolean firstPanel) {
        this.firstPanel = firstPanel;
    }

    public boolean isSecondPanel() {
        return secondPanel;
    }

    public void setSecondPanel(boolean secondPanel) {
        this.secondPanel = secondPanel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
