/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.request;

import com.git.dbcon.DbConnectionX;
import com.git.getitdone.CategoryModel;
import com.git.getitdone.SessionTest;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Gold
 */
@ManagedBean
@ViewScoped
public class GetRequest implements Serializable {

    private boolean panelVisible;
    private boolean edit;
    private List<RequestModel> requestList;
    private List<RequestModel> reques = new ArrayList<>();
    private RequestModel mm;
    private ReceiverDetailsModel receiverDetails = new ReceiverDetailsModel();
    private SessionTest test = new SessionTest();
    private String messangerOfTruth;

    @PostConstruct
    public void init() {
        try {

            setPanelVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeVisible(CategoryModel mod) {

        FacesMessage message;
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (test.test()) {
                requestList = requestLst(mod.getId());
                System.out.println(mod.getId()+ " yeah im here");
            } else {
                setMessangerOfTruth("User Session not found please sign out and back in ");
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessangerOfTruth(), getMessangerOfTruth());
                context.addMessage(null, message);
            }
            setPanelVisible(true);
        } catch (NullPointerException e) {
            setMessangerOfTruth("User Session not found please sign out and back in ");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessangerOfTruth(), getMessangerOfTruth());
            context.addMessage(null, message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<RequestModel> requestLst(int val) throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        SessionTest test = new SessionTest();

        try {

            con = dbConnections.mySqlDBconnection();
            String query = "select g.vendorfk,g.category,p.corporatename,p.coveragelocation, g.amount,l.vendorfk as requestId,l.requestStatus,l.completed from "
                    + "tbvendoritem g inner join tbvendor p on g.vendorfk=p.id left OUTER join "
                    + "tbrequest l on l.vendorfk=g.vendorfk "
                    + " where p.isdeleted=false and g.category=?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, val);
            rs = pstmt.executeQuery();
            //
            List<RequestModel> lst = new ArrayList<>();
            while (rs.next()) {

                RequestModel coun = new RequestModel();
                coun.setVendorfk(rs.getInt("vendorfk"));
                coun.setCatId(rs.getInt("category"));
                coun.setAmount(rs.getDouble("amount"));
                coun.setCorporatename(rs.getString("corporatename"));
                coun.setRequestedId(rs.getString("requestid"));
                coun.setRequestStatus(rs.getBoolean("requeststatus"));
                coun.setCompleted(rs.getBoolean("completed"));
                coun.setCoverageLocation(rs.getString("coveragelocation"));
                if (!coun.isCompleted() && coun.isRequestStatus()) {
                    coun.setRequestStat("Currently Unavailable");
                    setEdit(false);

                } else if (!coun.isCompleted() &&  coun.isRequestStatus()) {
                    coun.setRequestStat("Currently Available");
                    setEdit(true);
                }

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

    public List<RequestModel> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<RequestModel> requestList) {
        this.requestList = requestList;
    }

    public boolean isPanelVisible() {
        return panelVisible;
    }

    public void setPanelVisible(boolean panelVisible) {
        this.panelVisible = panelVisible;
    }

    public RequestModel getMm() {
        return mm;
    }

    public void setMm(RequestModel mm) {
        this.mm = mm;
    }

    public void vex(RequestModel m) {
        List<RequestModel> on = new ArrayList<>();
        // reques.add(m);
        reques.add(m);
        on.add(m);
        System.out.println(m.getAmount() + " okay oo " + m.isSelect() + " " + System.getProperty("user.home") + " hello");
    }

    public void valueO() {
        for (RequestModel b : reques) {
            System.out.println(b.getAmount() + " hi Dude " + b.getCorporatename());
        }

    }

    public ReceiverDetailsModel getReceiverDetails() {
        return receiverDetails;
    }

    public void setReceiverDetails(ReceiverDetailsModel receiverDetails) {
        this.receiverDetails = receiverDetails;
    }

    public String getMessangerOfTruth() {
        return messangerOfTruth;
    }

    public void setMessangerOfTruth(String messangerOfTruth) {
        this.messangerOfTruth = messangerOfTruth;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

}
