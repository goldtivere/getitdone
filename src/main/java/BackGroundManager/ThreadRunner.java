/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackGroundManager;

import com.git.TransferRequest.VoiceCall;
import com.git.dbcon.DateManipulation;
import com.git.dbcon.DbConnectionX;
import com.git.getitdone.SendSms;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
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
            List<MessageModel> mode = new ArrayList<>();
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

    public List<MessageModel> doTransaction() throws Exception {

        Connection con = null;
        DbConnectionX dbCon = new DbConnectionX();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sms_url;
        try {

            con = dbCon.mySqlDBconnection();

            //
            List<MessageModel> mode = new ArrayList<>();
            String querySMSDetails = "select * from tbtempregistration "
                    + "where verified=false and iscalled=false";
            //
            pstmt = con.prepareStatement(querySMSDetails);            
            rs = pstmt.executeQuery();

            //
            String _val = null;

            while (rs.next()) {
                MessageModel messageModel = new MessageModel();
//                System.out.println(rs.getString("verificationcode") + "  ok");
//                String value = " Please enter this code " + rs.getString("verificationcode") + " to complete your registration on GetItDone.";
//                _val = value.replace(" ", "%20");
//                _val = _val.replace(",", "%2C");
//                _val = _val.replace(":", "%3A");
//                _val = _val.replace(";", "%3B");
//                _val = _val.replace("'", "%27");
//                _val = _val.replace("(", "%28");
//                _val = _val.replace(")", "%29");
//                _val = _val.replace("#", "%23");
                String realVal = rs.getString("phonenumber");
                messageModel.setPhonenumber("+234" + realVal.substring(1));
                messageModel.setMessage(rs.getString("verificationXML"));
                messageModel.setFilename(rs.getString("filename"));
                messageModel.setXmlfilename(rs.getString("xmlfilename"));
                messageModel.setId(rs.getInt("id"));
                messageModel.setMessage(_val);

                setValueGet(true);
                mode.add(messageModel);

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

    public void updateSmsTable(String statusCode, String description, String phonenumber, int id) {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = dbConnections.mySqlDBconnection();
            String updateSmsTable = "update tbtempregistration set verified=?,statuscode=?,statusdescription=?,datesent=? where phonenumber=? and id=?";
            pstmt = con.prepareStatement(updateSmsTable);
            pstmt.setBoolean(1, true);
            pstmt.setString(2, statusCode);
            pstmt.setString(3, description);
            pstmt.setString(4, DateManipulation.dateAndTime());
            pstmt.setString(5, phonenumber);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateTab(String phonenumber, int id) {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = dbConnections.mySqlDBconnection();
            String updateSmsTable = "update tbtempregistration set iscalled=? where phonenumber=? and id=?";
            pstmt = con.prepareStatement(updateSmsTable);
            pstmt.setBoolean(1, true);
            pstmt.setString(2, phonenumber);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void runValue(List<MessageModel> model) throws NullPointerException, IOException {
        SendSms sms = new SendSms();
        int i = 0;
        VoiceCall call = new VoiceCall();
        try {

            for (MessageModel messageModel : model) {
                String val = call.runIt(messageModel.getPhonenumber(), messageModel.getFilename());
                updateTab(messageModel.getPhonenumber(), messageModel.getId());
                call.deleteXML(messageModel.getXmlfilename());

                updateSmsTable(val, messageModel.getMessage(), messageModel.getPhonenumber(), messageModel.getId() );
                System.out.println("ID: " + messageModel.getPhonenumber() + " sent. Message: " + messageModel.getMessage());
                System.out.println("done");

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
