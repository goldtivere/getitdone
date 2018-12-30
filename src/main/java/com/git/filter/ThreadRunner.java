/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.filter;

import com.git.dbcon.DateManipulation;
import com.git.dbcon.DbConnectionX;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
import org.xml.sax.InputSource;
import org.w3c.dom.Document;

/**
 *
 * @author Gold
 */
public class ThreadRunner implements Runnable {

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
                System.out.println(_val + " hi Gold");
            }
            System.out.println(_val + " hi Gold");
            return _val;

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

    }

    public List<Smsmodel> doTransaction() throws Exception {

        Connection con = null;
        DbConnectionX dbCon = new DbConnectionX();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sms_url;        
        try {

            con = dbCon.mySqlDBconnection();

            //
            List<Smsmodel> mode = new ArrayList<>();
            String querySMSDetails = "select l.*,t.receiverphone from tbpayment l inner join tbtransaction t on t.reference=l.trxnreference "
                    + "where l.ispaid=true and l.trxncompleted=false and l.trxnpaid=false and l.smssent=false";
            //
            pstmt = con.prepareStatement(querySMSDetails);
            
            rs = pstmt.executeQuery();

            //
            String _val = null;

            while (rs.next()) {
                Smsmodel sms = new Smsmodel();
                System.out.println(rs.getString("verificationcode") + "  ok");
                String value = rs.getString("smscontent");
                _val = value.replace(" ", "%20");
                _val = _val.replace(",", "%2C");
                _val = _val.replace(":", "%3A");
                _val = _val.replace(";", "%3B");
                _val = _val.replace("'", "%27");
                _val = _val.replace("(", "%28");
                _val = _val.replace(")", "%29");
                _val = _val.replace("#", "%23");
                sms.setId(rs.getInt("id"));
                sms.setAmount(rs.getDouble("amount"));
                sms.setPaid(rs.getBoolean("ispaid"));
                sms.setSmssent(rs.getBoolean("smssent"));
                sms.setTrxncomp(rs.getBoolean("trxncompleted"));
                sms.setTrxnpaid(rs.getBoolean("trxnpaid"));
                sms.setTrxnref(rs.getString("trxnreference"));
                sms.setPhone(rs.getString("receiverphone"));
                sms.setSmscontent(value);

                
                mode.add(sms);

            }

            return mode;

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

    public void updateSmsTable(String ref, int id,String response) {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = dbConnections.mySqlDBconnection();
            String updateSmsTable = "update tbpayment set smssent=?,datesmssent=?,apiresponse=? where trxnreference=? and id=?";
            pstmt = con.prepareStatement(updateSmsTable);
            pstmt.setBoolean(1, true);     
            pstmt.setString(2, DateManipulation.dateAndTime());
            pstmt.setString(3, response);
            pstmt.setString(4, ref);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void runValue(List<Smsmodel> model) throws NullPointerException, IOException {

        int i = 0;

        try {

            for (Smsmodel sms : model) {

                String val = null;
                String sessionid = "54abd51e-e240-4e0c-b899-991f08829897";
                String sender = "DND_BYPASSGetItDone";
                URL url = new URL("http://www.smslive247.com/http/index.aspx?cmd=sendmsg&sessionid=" + sessionIdGet() + "&message=" + sms.getSmscontent()+ "&sender=" + sender + "&sendto=" + sms.getPhone()+ "&msgtype=0");
                //http://www.bulksmslive.com/tools/geturl/Sms.php?username=abc&password=xyz&sender="+sender+"&message="+message+"&flash=0&sendtime=2009-10- 18%2006:30&listname=friends&recipients="+recipient; 
                //URL gims_url = new URL("http://smshub.lubredsms.com/hub/xmlsmsapi/send?user=loliks&pass=GJP8wRTs&sender=nairabox&message=Acct%3A5073177777%20Amt%3ANGN1%2C200.00%20CR%20Desc%3ATesting%20alert%20Avail%20Bal%3ANGN%3A1%2C342%2C158.36&mobile=08065711040&flash=0");
                final String USER_AGENT = "Mozilla/5.0";

                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", USER_AGENT);
                int responseCode = con.getResponseCode();
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                // System.out.println(messageModel.getBody() + " dude");
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                String responseCod = response.toString();

//                if (responseCod.equalsIgnoreCase("-1")) {
//                    val = "Incorrect / badly formed URL data";
//                } else if (responseCod.equalsIgnoreCase("-2")) {
//                    val = "Incorrect username and/or password";
//                } else if (responseCod.equalsIgnoreCase("-3")) {
//                    val = "Not enough credit units in user account";
//                } else if (responseCod.equalsIgnoreCase("-4")) {
//                    val = "Invalid sender name";
//                } else if (responseCod.equalsIgnoreCase("-5")) {
//                    val = "No valid recipient ";
//                } else if (responseCod.equalsIgnoreCase("-6")) {
//                    val = "Invalid message length/No message content";
//                } else if (responseCod.equalsIgnoreCase("-10")) {
//                    val = "Unknown/Unspecified error";
//                } else if (responseCod.equalsIgnoreCase("100")) {
//                    val = "Send successful";
//                }
                // in.close(); unremark
                //System.out.println("God is my Strength:" + i++  );
                //  System.out.println("The URL:" + gims_url);
                //doTransaction();
                updateSmsTable(sms.getTrxnref(),sms.getId(), responseCod);
                System.out.println("ID: " + sms.getPhone() + " sent. Message: " + sms.getSmscontent());
                System.out.println("Present");

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("this one nor suppose affect am now");
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
