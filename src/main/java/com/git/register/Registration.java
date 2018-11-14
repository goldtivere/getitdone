/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.register;

import BackGroundManager.MessageModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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

    @PostConstruct
    public void init() {
        setFirstPanel(true);
        setSecondPanel(false);
    }

    public void submit() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void nextPage() {
        setFirstPanel(false);
        setSecondPanel(true);
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

}
