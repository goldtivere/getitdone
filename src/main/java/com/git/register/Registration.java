/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.register;

import BackGroundManager.MessageModel;
import com.git.dbcon.AESencrp;
import com.git.dbcon.DateManipulation;
import com.git.dbcon.DbConnectionX;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gold
 */
@ManagedBean
public class Registration {

    private boolean firstPanel;
    private boolean secondPanel;
    private String fname;
    private String lname;
    private String pnum;
    private String verCode;
    private String pwd;

    @PostConstruct
    public void init() {
        setFirstPanel(true);
        setSecondPanel(false);
    }

    public void tempPhone() {

    }

    public boolean submit() {
        try {

            String gRecap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("g-recaptcha-response");
            boolean verify = VerifyRecaptcha.verify(gRecap);

            if (verify) {
                System.out.println("I got here");
                return true;
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Select Captcha"));
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //check if verCodeExist
    public boolean checkIfVerExists() {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = dbConnections.mySqlDBconnection();
        try {
            String queryProfile = "select * from tbtempregistration "
                    + "where verificationcode=? and phonenumber=? and phoneverified=? order by createdon desc limit 1";
            pstmt = con.prepareStatement(queryProfile);
            pstmt.setString(1, getVerCode());
            pstmt.setString(2, getPnum());
            pstmt.setBoolean(3, false);
            rs = pstmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean checkIfNumExists() {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = dbConnections.mySqlDBconnection();
        try {
            String queryProfile = "select * from tbtempregistration "
                    + "where phonenumber=? and phoneverified=?";
            pstmt = con.prepareStatement(queryProfile);
            pstmt.setString(1, getPnum());
            pstmt.setBoolean(2, true);
            rs = pstmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void register() {
        FacesContext context = FacesContext.getCurrentInstance();
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dbConnections.mySqlDBconnection();

            String pass = AESencrp.encrypt(getPwd());
            if (checkIfVerExists()) {
                String insert = "insert into tbregistration (firstname,lastname,fullname,phonenumber,password,datecreated)"
                        + "values(?,?,?,?,?,?)";
                pstmt = con.prepareStatement(insert);

                pstmt.setString(1, getFname());
                pstmt.setString(2, getLname());
                pstmt.setString(3, getLname() + " " + getFname());
                pstmt.setString(4, getPnum());
                pstmt.setString(5, pass);
                pstmt.setString(6, DateManipulation.dateAndTime());
                pstmt.executeUpdate();

                String update = "update tbtempregistration set phoneverified=? where phonenumber=? and verificationcode=? order by createdon desc limit 1";
                pstmt = con.prepareStatement(update);

                pstmt.setBoolean(1, true);
                pstmt.setString(2, getPnum());
                pstmt.setString(3, getVerCode());
                pstmt.executeUpdate();

                NavigationHandler nav = context.getApplication().getNavigationHandler();

                String url_ = "/pages/success/index.xhtml?faces-redirect=true";
                nav.handleNavigation(context, null, url_);
                context.renderResponse();

            } else {
                context.addMessage(null, new FacesMessage("incorrect verification code!!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //this method generates validation code for the phonenumber
    public String generateRandom() {
        Random rnd = new Random();
        int a = 100000 + rnd.nextInt(900000);

        return String.valueOf(a);
    }

    public void nextPage() {
        FacesContext context = FacesContext.getCurrentInstance();
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = dbConnections.mySqlDBconnection();
        try {

            if (checkIfNumExists()) {
                context.addMessage(null, new FacesMessage("Phone number aready registered!!"));
            } else if (submit()) {
                String insertemail = "insert into tbtempregistration (phonenumber,verified,verificationcode,phoneverified,createdon)"
                        + "values(?,?,?,?,?)";
                pstmt = con.prepareStatement(insertemail);

                pstmt.setString(1, getPnum());
                pstmt.setBoolean(2, false);
                pstmt.setString(3, generateRandom());
                pstmt.setBoolean(4, false);
                pstmt.setString(5, DateManipulation.dateAndTime());
                pstmt.executeUpdate();

                setFirstPanel(false);
                setSecondPanel(true);
                System.out.println("This is it: " + generateRandom());
            } else {
                context.addMessage(null, new FacesMessage("Something went wrong!!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testResult() {
        Connection conn = null;

        String url = "jdbc:mysql://localhost:3306/bookings";
        //String dbName = "bookings";
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt;
        String driver = "com.mysql.jdbc.Driver";
        String databaseUserName = "root";
        String databasePassword = "Caroline93*";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, databaseUserName, "Caroline93*");
            System.out.println("this " + conn);

            String querySMSDetails = "select * from tbtempregistration "
                    + "where verified=?";
            //
            pstmt = conn.prepareStatement(querySMSDetails);
            pstmt.setBoolean(1, false);
            rs = pstmt.executeQuery();

            //
            String _val = null;

            while (rs.next()) {
                System.out.println(rs.getString("verificationcode") + "  ok");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPnum() {
        return pnum;
    }

    public void setPnum(String pnum) {
        this.pnum = pnum;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
