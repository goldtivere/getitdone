/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.TransferRequest;

import com.git.core.Transactions;
import com.git.filter.*;
import com.git.dbcon.DateManipulation;
import com.git.dbcon.DbConnectionX;
import com.git.dbcon.LoadPPTfile;
import com.git.getitdone.SendSms;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;

/**
 *
 * @author Gold
 */
public class TransferInitiate implements Runnable {

    private boolean valueGet;

    @Override
    public void run() {
        try {

            runValue(doTransaction());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sessionIdGet() throws SQLException {
        Connection con = null;
        DbConnectionX dbCon = new DbConnectionX();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sms_url;

        try {

            con = dbCon.mySqlDBconnection();

            //            
            String querySMSDetails = "select * from tbsmsconfig limit 1";
            //
            pstmt = con.prepareStatement(querySMSDetails);
            rs = pstmt.executeQuery();

            //
            String _val = null;

            if (rs.next()) {
                _val = rs.getString("sessionid");

            }

            return _val;

        } catch (Exception e) {

            //System.out.print("Exception from doTransaction method.....");
            e.printStackTrace();
            return null;

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

    public List<TransferinitiateModel> doTransaction() throws Exception {

        Connection con = null;
        DbConnectionX dbCon = new DbConnectionX();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sms_url;
        LoadPPTfile load = new LoadPPTfile();
        try {

            con = dbCon.mySqlDBconnection();

            //
            List<TransferinitiateModel> mode = new ArrayList<>();
            String queryPayment = "SELECT p.id,p.vendorfk,p.trxnreference,r.recepientcode,v.amount,v.agentpercentage FROM tbpayment p inner join tbrecepient r on p.vendorfk=r.vendorfk inner join "
                    + "tbvendoritem v on p.vendorfk=v.vendorfk where p.ispaid=true and p.trxncompleted=true and p.trxnpaid=false and p.itemname=v.itemname";
            //
            pstmt = con.prepareStatement(queryPayment);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                TransferinitiateModel sms = new TransferinitiateModel();

                sms.setId(rs.getInt("id"));
                sms.setVendorfk(rs.getInt("vendorfk"));
                sms.setRef(rs.getString("trxnreference"));
                sms.setRecipientCode(rs.getString("recepientcode"));
                sms.setAmount(rs.getDouble("amount"));
                sms.setPercent(rs.getDouble("agentpercentage"));

                mode.add(sms);

            }

            return mode;

        } catch (Exception e) {

            //System.out.print("Exception from doTransaction method.....");
            e.printStackTrace();
            return null;

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

    }//end doTransaction...

    public void updatePayment(TransferinitiateModel tran) {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = dbConnections.mySqlDBconnection();
            String updateSmsTable = "update tbpayment set trxnpaid=?,datetrxncompleted=? where id=? and trxnreference=?";
            pstmt = con.prepareStatement(updateSmsTable);
            pstmt.setBoolean(1, true);
            pstmt.setString(2, DateManipulation.dateAndTime());
            pstmt.setInt(3, tran.getId());
            pstmt.setString(4, tran.getRef());
            pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateTransfer(TransferinitiateModel mode, InitiateTransfer tran) {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = dbConnections.mySqlDBconnection();
            String insertTransfer = "insert into tbconfirmtransfer (reference,domain,amount,currency,reason,recipient,status,transfercode,createdid,createdat,updatedat,"
                    + "datecreated,recipientid,referenceid,vendorfk) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(insertTransfer);
            pstmt.setString(1, tran.getData().getReference());
            pstmt.setString(2, tran.getData().getDomain());
            pstmt.setString(3, tran.getData().getAmount());
            pstmt.setString(4, tran.getData().getCurrency());
            pstmt.setString(5, tran.getData().getReason());
            pstmt.setString(6, tran.getData().getRecipient());
            pstmt.setString(7, tran.getData().getStatus());
            pstmt.setString(8, tran.getData().getTransfer_code());
            pstmt.setString(9, tran.getData().getId());
            pstmt.setString(10, tran.getData().getCreatedAt());
            pstmt.setString(11, tran.getData().getUpdatedAt());
            pstmt.setString(12, DateManipulation.dateAndTime());
            pstmt.setString(13, mode.getRecipientCode());
            pstmt.setString(14, mode.getRef());
            pstmt.setInt(15, mode.getVendorfk());

            pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //method sends notification to admin notifying them of request made.
    public void sendToAdmin(String sessionid, Smsmodel sms, String vals) throws ProtocolException, MalformedURLException, IOException {
        SendSms smss = new SendSms();
        String val = null;
        // System.out.println("hello boy " + vals);
        String sender = "DND_BYPASSGetItDone";
        String respond = smss.sendMessage(sessionid, sms.getVendorMessage(), sender, vals);
    }

    public void runValue(List<TransferinitiateModel> model) throws NullPointerException, IOException {

        int i = 0;
        Transactions trans = new Transactions();
        LoadPPTfile load = new LoadPPTfile();
        try {
            if (model != null && !model.isEmpty()) {
                for (TransferinitiateModel sms : model) {
                    double val = sms.getAmount() * (sms.getPercent() / 100);
                    double vval = (sms.getAmount() - val) * 100;
                    int amount = (int) vval;
                    JSONObject bn = trans.initializeTranfer(sms.Balance, "Fund transfer for service delivered", amount, sms.getRecipientCode());
                    ObjectMapper mapp = new ObjectMapper();
                    InitiateTransfer initial = mapp.readValue(bn.toString(), InitiateTransfer.class);
                    //System.out.println(bn+ " hello");
                    if (("true").equalsIgnoreCase(initial.getStatus())) {
                        //System.out.println(amount + "  " + sms.getRecipientCode() + " " + sms.Balance + " * * * * * * *" + bn);
                        sms.setAmount(amount);
                        updateTransfer(sms, initial);
                        updatePayment(sms);
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("this one nor suppose affect am now");
            Thread.currentThread().interrupt();

        }

    }//end of run method...

    public boolean isValueGet() {
        return valueGet;
    }

    public void setValueGet(boolean valueGet) {
        this.valueGet = valueGet;
    }

}
