/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.filter;

import com.git.core.Transactions;
import com.git.dbcon.DateManipulation;
import com.git.dbcon.DbConnectionX;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

/**
 *
 * @author Gold
 */
public class ConfirmPaystackPayment implements Runnable {

    @Override
    public void run() {
        checkIfTrue();
    }

    public List<String> referenceName() throws SQLException {
        Connection con = null;
        DbConnectionX dbCon = new DbConnectionX();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = dbCon.mySqlDBconnection();
            String queryPaystackDetails = "select reference from tbtransaction where iscompleted=false";
            //
            pstmt = con.prepareStatement(queryPaystackDetails);
            rs = pstmt.executeQuery();
            List<String> dbval = new ArrayList<>();
            while (rs.next()) {
                dbval.add(rs.getString("reference"));
            }

            return dbval;
        } catch (Exception e) {

            System.out.print("Exception from doTransaction method.....");
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

    public void checkIfTrue() {
        Connection con = null;
        DbConnectionX dbCon = new DbConnectionX();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Transactions trans = new Transactions();
        try {
            con = dbCon.mySqlDBconnection();
            String updateStatus = "update tbtransaction set iscompleted=true where reference=?";

            String statusUpdate = "update tbpayment set ispaid=true where trxnreference=?";
            int i = 0;
            System.out.println("this guy head big");
            for (String val : referenceName()) {
                JSONObject bn = trans.verifyTransaction(val);
                ObjectMapper mapp = new ObjectMapper();
                ConfirmPayment confirm = mapp.readValue(bn.toString(), ConfirmPayment.class);

                if (confirm.getData().getGateway_response().equalsIgnoreCase("successful")) {
                    pstmt = con.prepareStatement(updateStatus);
                    pstmt.setString(1, val);
                    pstmt.executeUpdate();

                    pstmt = con.prepareStatement(statusUpdate);
                    pstmt.setString(1, val);
                    pstmt.executeUpdate();
                    confirmPayment(confirm);
                    System.out.println("the eye big: " + i);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void confirmPayment(ConfirmPayment confirm) throws SQLException {
        FacesContext context = FacesContext.getCurrentInstance();
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dbConnections.mySqlDBconnection();
            String insert = "insert into tbpaymentresponse (trxnreference,gatewayresponse,amount,channel,ipaddress,authorcode,customercode,customerid,createdat,datecreated)"
                    + "values(?,?,?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(insert);

            pstmt.setString(1, confirm.getData().getReference());
            pstmt.setString(2, confirm.getData().getGateway_response());
            pstmt.setString(3, confirm.getData().getAmount());
            pstmt.setString(4, confirm.getData().getChannel());
            pstmt.setString(5, confirm.getData().getIp_address());
            pstmt.setString(6, confirm.getData().getAuthorization().getAuthorization_code());
            pstmt.setString(7, confirm.getData().getCustomer().getCustomer_code());
            pstmt.setString(8, confirm.getData().getCustomer().getId());
            pstmt.setString(9, confirm.getData().getCreatedAt());
            pstmt.setString(10, DateManipulation.dateAndTime());
            pstmt.executeUpdate();
            System.out.println("the eye big: ");
        } catch (Exception e) {
            e.printStackTrace();

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
}
