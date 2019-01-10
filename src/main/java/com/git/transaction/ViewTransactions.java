/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.transaction;

import com.git.dbcon.DbConnectionX;
import com.git.getitdone.SelectOptionMenu;
import com.git.getitdone.TrxnStatusModel;
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
public class ViewTransactions implements Serializable {

    private List<TrxnStatusModel> model;
    private List<TrxnModel> trxn;
    private SelectOptionMenu menu = new SelectOptionMenu();
    private int valueStatus;
    private String messangerOfTruth;

    @PostConstruct
    public void init() {
        try {
            model = menu.trxnStatus();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TrxnModel> viewTrxn(boolean trxnSent, boolean trxnpaid, int userfk) throws SQLException {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = dbConnections.mySqlDBconnection();
            String query = "select p.*,t.datecreated as created,t.userfk,v.*,vi.* from tbpayment p inner join tbtransaction t on p.trxnreference=t.reference "
                    + " inner join tbvendor v on p.vendorfk=v.id inner join tbvendoritem vi "
                    + " on p.vendorfk=vi.vendorfk where p.ispaid=? and p.trxncompleted=? and t.userfk=?";
            pstmt = con.prepareStatement(query);
            pstmt.setBoolean(1, trxnSent);
            pstmt.setBoolean(2, trxnpaid);
            pstmt.setInt(3, userfk);
            rs = pstmt.executeQuery();
            List<TrxnModel> model = new ArrayList<>();
            //
            while (rs.next()) {
                TrxnModel mode = new TrxnModel();
                mode.setId(rs.getInt("id"));
                mode.setVendorfk(rs.getInt("vendorfk"));
                mode.setRef(rs.getString("trxnreference"));
                mode.setVendorName(rs.getString("corporatename"));
                mode.setAmount(rs.getDouble("amount"));
                mode.setPercent(rs.getDouble("agentpercentage"));
                mode.setDatecompleted(rs.getDate("created"));

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

    public void onStatusChange() throws SQLException{
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        UserDetails userObj = (UserDetails) context.getExternalContext().getSessionMap().get("sessn_nums");
        String on = String.valueOf(userObj);
        int createdby = userObj.getId();

        if (userObj == null) {
            setMessangerOfTruth("Expired Session, please - login " + on);
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    getMessangerOfTruth(), getMessangerOfTruth());
            context.addMessage(null, msg);
        }
        {
            if (getValueStatus() == 1) {
                trxn=viewTrxn(true,true,userObj.getId());
            } else if (getValueStatus() == 2) {
                trxn= viewTrxn(true,false,userObj.getId());
            }
        }
    }

    public List<TrxnStatusModel> getModel() {
        return model;
    }

    public void setModel(List<TrxnStatusModel> model) {
        this.model = model;
    }

    public List<TrxnModel> getTrxn() {
        return trxn;
    }

    public void setTrxn(List<TrxnModel> trxn) {
        this.trxn = trxn;
    }

    public int getValueStatus() {
        return valueStatus;
    }

    public void setValueStatus(int valueStatus) {
        this.valueStatus = valueStatus;
    }

    public String getMessangerOfTruth() {
        return messangerOfTruth;
    }

    public void setMessangerOfTruth(String messangerOfTruth) {
        this.messangerOfTruth = messangerOfTruth;
    }

}
