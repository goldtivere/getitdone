/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.Contact;

import com.git.dbcon.DateManipulation;
import com.git.dbcon.DbConnectionX;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ContactUs implements Serializable {

    private ContactUsModel mode = new ContactUsModel();

    public void refresh() {
        mode.setBody("");
        mode.setEmailaddress("");
        mode.setSubject("");
    }

    public void sendMessage() throws SQLException {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = dbConnections.mySqlDBconnection();
            String insertemail = "insert into tbcontactus (emailaddress,subject,body,datesent,datetimesent,read) "
                    + "values(?,?,?,?,?,?)";
            pstmt = con.prepareStatement(insertemail);

            pstmt.setString(1, mode.getEmailaddress());
            pstmt.setString(2, mode.getSubject());
            pstmt.setString(3, mode.getBody());
            pstmt.setString(4, DateManipulation.dateAlone());
            pstmt.setString(5, DateManipulation.dateAndTime());
            pstmt.setBoolean(6, false);
            pstmt.executeUpdate();
            refresh();
            context.addMessage(null, new FacesMessage("Message Sent!!"));

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

    public ContactUsModel getMode() {
        return mode;
    }

    public void setMode(ContactUsModel mode) {
        this.mode = mode;
    }

}
