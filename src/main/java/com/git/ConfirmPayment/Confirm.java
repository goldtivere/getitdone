/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.ConfirmPayment;

import com.git.dbcon.DbConnectionX;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gold
 */
@ManagedBean
@ViewScoped
public class Confirm implements Serializable {

    private String ref;
    private String someParam;
    private String correctRef;
    private String errorLabel;
    private boolean visible;

    @PostConstruct
    public void init() {
        try {
            if (!confirmReference()) {

                setErrorLabel(" LINK HAS EXPIRED OR DOES NOT EXIST. PLEASE CONTACT ADMIN!!!");
                setVisible(false);

            } else {
                setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //picks and checks that query param in URL exist in transactio table
    public boolean confirmReference() throws SQLException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        Map<String, String> params = externalContext.getRequestParameterMap();
        someParam = params.get("ref");

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dbConnections.mySqlDBconnection();
            String testguid = "Select * from tbtransaction where reference=?";
            pstmt = con.prepareStatement(testguid);
            pstmt.setString(1, someParam);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                correctRef = rs.getString("reference");
                return true;

            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(String errorLabel) {
        this.errorLabel = errorLabel;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
