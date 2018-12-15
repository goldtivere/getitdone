/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.request;

import com.git.dbcon.DbConnectionX;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private List<RequestModel> requestList;

    @PostConstruct
    public void init() {
        try {
            requestList = requestLst();
            setPanelVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeVisible() {

        setPanelVisible(true);

    }

    public List<RequestModel> requestLst() throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = dbConnections.mySqlDBconnection();
            String query = "select g.vendorfk,p.corporatename,p.coveragelocation, g.amount,l.vendorfk as requestId,l.requestStatus,l.completed from "
                    + "tbvendoritem g inner join tbvendor p on g.vendorfk=p.id left OUTER join "
                    + "tbrequest l on l.vendorfk=g.vendorfk "
                    + "where  l.requestStatus and p.isdeleted=false or l.vendorfk is  null";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            //
            List<RequestModel> lst = new ArrayList<>();
            while (rs.next()) {

                RequestModel coun = new RequestModel();
                coun.setVendorfk(rs.getInt("vendorfk"));
                coun.setAmount(rs.getDouble("amount"));
                coun.setCorporatename(rs.getString("corporatename"));
                coun.setRequestedId(rs.getString("requestid"));
                coun.setRequestStatus(rs.getBoolean("requeststatus"));
                coun.setCompleted(rs.getBoolean("completed"));
                coun.setCoverageLocation(rs.getString("coveragelocation"));
                if (coun.isCompleted()) {
                    coun.setRequestStat("Currently Availble");

                } else if (!coun.isCompleted()) {
                    coun.setRequestStat("Currently on a trip");
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

}
