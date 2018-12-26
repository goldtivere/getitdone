/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.request;

import com.git.core.Recipient;
import com.git.core.Transactions;
import com.git.dbcon.DbConnectionX;
import com.git.dbcon.LoadPPTfile;
import com.git.register.UserDetails;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

/**
 *
 * @author Gold
 */
@ManagedBean
@ViewScoped
public class PaymentDetails implements Serializable {

    private String messangerOfTruth;
    private List<RequestModel> mode;
    private double sumValue;
    private SenderModel model = new SenderModel();

    @PostConstruct
    public void init() {
        double sum = 0;
        for (RequestModel mod : value()) {
            sum += mod.getAmount();
        }
        setSumValue(sum);
        mode = value();
        System.out.println("Hi got here");
    }

    public List<RequestModel> value() {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg;

            String stuValue = null;
            List<RequestModel> request = (List<RequestModel>) ctx.getExternalContext().getApplicationMap().get("request");

            if (request != null || !request.isEmpty()) {
                return request;
            } else {
                setMessangerOfTruth("Session Expired for this Student. Please select student and try again1!!");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessangerOfTruth(), getMessangerOfTruth());
                ctx.addMessage(null, msg);
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String generateRefNo() {

        try {

            String timeStamp = new SimpleDateFormat("yyMMddHHmmss").format(Calendar.getInstance().getTime());

            int rnd = new Random().nextInt(99999753);
            String temp_val = String.valueOf(rnd).concat(timeStamp);
            return temp_val;

        } catch (Exception ex) {

            ex.printStackTrace();
            return null;

        }

    }//end generateRefNo(...)

    public void makepayment() throws SQLException {
        FacesContext context = FacesContext.getCurrentInstance();
        DbConnectionX dbConnections = new DbConnectionX();
        Recipient receipt = new Recipient();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FacesMessage msg;
        Transactions trans = new Transactions();
        LoadPPTfile load= new LoadPPTfile();

        try {
            con = dbConnections.mySqlDBconnection();
            UserDetails userObj = (UserDetails) context.getExternalContext().getSessionMap().get("sessn_nums");
            String on = String.valueOf(userObj);
            int createdby = userObj.getId();

            if (userObj == null) {
                setMessangerOfTruth("Expired Session, please - login " + on);
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        getMessangerOfTruth(), getMessangerOfTruth());
                context.addMessage(null, msg);
            } else {
                String tranAmount = String.valueOf(getSumValue() * 100);
                String ref=tranAmount+generateRefNo();
                JSONObject bn = trans.initializeTransaction(ref, tranAmount, model.getEmailAddress(), null, load.callback());
                ObjectMapper mapp = new ObjectMapper();
                InitialisePojo initial= mapp.readValue(bn.toString(), InitialisePojo.class);
                System.out.println("I am here big head: "+ bn);
            }

        } catch (Exception e) {

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

    public List<RequestModel> getMode() {
        return mode;
    }

    public void setMode(List<RequestModel> mode) {
        this.mode = mode;
    }

    public String getMessangerOfTruth() {
        return messangerOfTruth;
    }

    public void setMessangerOfTruth(String messangerOfTruth) {
        this.messangerOfTruth = messangerOfTruth;
    }

    public double getSumValue() {
        return sumValue;
    }

    public void setSumValue(double sumValue) {
        this.sumValue = sumValue;
    }

    public SenderModel getModel() {
        return model;
    }

    public void setModel(SenderModel model) {
        this.model = model;
    }

}
