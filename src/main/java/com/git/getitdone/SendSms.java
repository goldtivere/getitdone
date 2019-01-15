/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.getitdone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 * @author Gold
 */
public class SendSms {
    
    public String sendMessage(String sessionid,String message,String sender,String sendto) throws MalformedURLException, ProtocolException, IOException
    {
         URL url = new URL("http://www.smslive247.com/http/index.aspx?cmd=sendmsg&sessionid=" + sessionid + "&message=" + message + "&sender=" + sender + "&sendto=" + sendto + "&msgtype=0");
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
                return response.toString();
    }
    
}
