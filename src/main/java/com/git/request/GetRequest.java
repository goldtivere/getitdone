/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.request;

import com.git.dbcon.DbConnectionX;
import com.git.getitdone.CategoryModel;
import com.git.getitdone.LocationModel;
import com.git.getitdone.SelectOptionMenu;
import com.git.getitdone.SessionTest;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
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
    private List<RequestModel> reques = new ArrayList<>();
    private List<RequestModel> myRequest;
    private RequestModel mm;
    private ReceiverDetailsModel receiverDetails = new ReceiverDetailsModel();
    private List<LocationModel> listMode;
    private List<CategoryModel> mode;
    private SelectOptionMenu loc = new SelectOptionMenu();
    private SessionTest test = new SessionTest();
    private String messangerOfTruth;
    private int locationfk;
    private boolean show;

    @PostConstruct
    public void init() {
        try {
            listMode = loc.Location();
            setPanelVisible(false);
            setShow(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onLocationChange() {
        try
        {
            setShow(true);
        mode = loc.dropCategory();
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void makeVisible(CategoryModel mod) {

        FacesMessage message;
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (test.test()) {
                requestList = requestLst(mod.getId());
                System.out.println(mod.getId() + " yeah im here");
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

    public String redirectNow() {
        return "https://checkout.paystack.com/bwxa5iymvbju7sy";
    }

    public void redirectAgain() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("https://checkout.paystack.com/bwxa5iymvbju7sy");
    }

    public void redirect() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        NavigationHandler nav = ctx.getApplication().getNavigationHandler();
        ctx.getExternalContext().getApplicationMap().remove("request");
        ctx.getExternalContext().getApplicationMap().put("request", reques);
        String url = "/pages/home/makePayment.xhtml?faces-redirect=true";
        nav.handleNavigation(ctx, null, url);
        ctx.renderResponse();

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
            String query = "select distinct g.vendorfk,g.category,g.quantity,p.corporatename,p.coveragelocation,l.trxnpaid, g.amount,l.vendorfk as requestId,l.ispaid,l.trxncompleted from "
                    + "tbvendoritem g inner join tbvendor p on g.vendorfk=p.id left OUTER join "
                    + "tbpayment l on l.vendorfk=g.vendorfk "
                    + " where p.isdeleted=false and g.category=? and g.locationfk=? and l.trxncompleted=false";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, val);
            pstmt.setInt(2, getLocationfk());
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
                coun.setRequestStatus(rs.getBoolean("ispaid"));
                coun.setCompleted(rs.getBoolean("trxncompleted"));
                coun.setCoverageLocation(rs.getString("coveragelocation"));
                coun.setTrxnpaid(rs.getBoolean("trxnpaid"));
                coun.setQuan(rs.getInt("quantity"));
                System.out.println(coun.isCompleted() + " hi " + coun.isRequestStatus() + " yeah " + coun.isTrxnpaid());
                if (!coun.isCompleted() && coun.isRequestStatus()) {
                    coun.setRequestStat("Currently Unavailable");
                    coun.setEdit(false);

                } else {
                    coun.setRequestStat("Currently Available");
                    coun.setEdit(true);
                }
                System.out.println(coun.isEdit() + " hi bob");
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
        if (m.isSelect()) {

            // reques.add(m);
            reques.add(m);
            //  on.add(m);
        } else {
            reques.remove(m);
        }

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

    public List<RequestModel> getMyRequest() {
        return myRequest;
    }

    public void setMyRequest(List<RequestModel> myRequest) {
        this.myRequest = myRequest;
    }

    public int getLocationfk() {
        return locationfk;
    }

    public void setLocationfk(int locationfk) {
        this.locationfk = locationfk;
    }

    public List<LocationModel> getListMode() {
        return listMode;
    }

    public void setListMode(List<LocationModel> listMode) {
        this.listMode = listMode;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public List<CategoryModel> getMode() {
        return mode;
    }

    public void setMode(List<CategoryModel> mode) {
        this.mode = mode;
    }

}
