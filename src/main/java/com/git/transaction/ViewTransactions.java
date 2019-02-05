/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.transaction;

import com.git.dbcon.DateManipulation;
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
import javax.el.PropertyNotFoundException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Gold
 */
@ManagedBean
@ViewScoped
public class ViewTransactions implements Serializable {

    private List<TrxnStatusModel> model;
    private List<TrxnModel> trxn;
    private List<TrxnModel> selectedtrxn;
    private SelectOptionMenu menu = new SelectOptionMenu();
    private int valueStatus;
    private String messangerOfTruth;
    private String status;
    private boolean checkStatus;

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
                    + " on p.vendorfk=vi.vendorfk where p.ispaid=? and p.trxncompleted=? and t.userfk=? ";
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
                mode.setDatedelivered(rs.getDate("datetrxncompleted"));

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

    public List<TrxnModel> viewTrxnVendor(boolean trxnSent, boolean trxnpaid, int vendorfk) throws SQLException {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = dbConnections.mySqlDBconnection();
            String query = "select p.*,t.datecreated as created,t.userfk,v.*,vi.* from tbpayment p inner join tbtransaction t on p.trxnreference=t.reference "
                    + " inner join tbvendor v on p.vendorfk=v.id inner join tbvendoritem vi "
                    + " on p.vendorfk=vi.vendorfk where p.ispaid=? and p.trxncompleted=? and p.vendorfk=? ";
            pstmt = con.prepareStatement(query);
            pstmt.setBoolean(1, trxnSent);
            pstmt.setBoolean(2, trxnpaid);
            pstmt.setInt(3, vendorfk);
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
                mode.setDatedelivered(rs.getDate("datetrxncompleted"));

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

    public void onStatusChange() throws SQLException {
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
                setStatus("Completed Transaction(s)");
                setCheckStatus(false);
                trxn = viewTrxn(true, true, userObj.getId());
            } else if (getValueStatus() == 2 && userObj.getRole() == 3) {
                setStatus("Pending Transaction(s)");
                setCheckStatus(false);
                trxn = viewTrxn(true, false, userObj.getId());
            } else if (getValueStatus() == 2 && userObj.getRole() == 3) {
                setStatus("Pending Transaction(s)");
                setCheckStatus(true);
                trxn = viewTrxnVendor(true, false, userObj.getId());
            } else if (getValueStatus() == 2 && userObj.getRole() ==1) {
                setStatus("Pending Transaction(s)");
                setCheckStatus(true);
                trxn = viewTrxnVendor(true, false, userObj.getId());
            }
        }
    }

    public void confirmDelivery() throws SQLException {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FacesMessage msg;
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext cont = RequestContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        String tablename = null;
        System.out.println("hi Gold");
        try {
            UserDetails userObj = (UserDetails) context.getExternalContext().getSessionMap().get("sessn_nums");
            String on = String.valueOf(userObj);
            int createdId = userObj.getId();
            if (userObj != null) {
                con = dbConnections.mySqlDBconnection();
                if (selectedtrxn == null) {
                    setMessangerOfTruth("Item(s) not selected!!");
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessangerOfTruth(), getMessangerOfTruth());
                    context.addMessage(null, msg);
                } else {
                    String updateTrxn = "update tbpayment set trxncompleted=?,datetrxncompleted=? where trxnreference=? and id=?";

                    pstmt = con.prepareStatement(updateTrxn);
                    for (TrxnModel ta : selectedtrxn) {
                        pstmt.setBoolean(1, true);
                        pstmt.setString(2, DateManipulation.dateAndTime());
                        pstmt.setString(3, ta.getRef());
                        pstmt.setInt(4, ta.getId());
                        pstmt.executeUpdate();

                    }

                    trxn = viewTrxn(true, false, userObj.getId());

                    setMessangerOfTruth("Delivery Confrmed!!");
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessangerOfTruth(), getMessangerOfTruth());
                    context.addMessage(null, msg);
                }

            } else {
                setMessangerOfTruth("Expired Session, please - login " + on);
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        getMessangerOfTruth(), getMessangerOfTruth());
                context.addMessage(null, msg);
            }
        } catch (PropertyNotFoundException e) {

            setMessangerOfTruth("Item(s) not selected!!");
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessangerOfTruth(), getMessangerOfTruth());
            context.addMessage(null, msg);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TrxnModel> getSelectedtrxn() {
        return selectedtrxn;
    }

    public void setSelectedtrxn(List<TrxnModel> selectedtrxn) {
        this.selectedtrxn = selectedtrxn;
    }

    public boolean isCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
    }

}
