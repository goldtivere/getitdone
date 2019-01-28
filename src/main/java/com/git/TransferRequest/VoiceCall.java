/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.TransferRequest;

import com.git.dbcon.LoadPPTfile;
import java.net.URI;
import java.net.URISyntaxException;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import java.io.File;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Gold
 */
//@ManagedBean
//@ViewScoped
public class VoiceCall {

 

    public String runIt(String pnum, String location) throws URISyntaxException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String from = "+16267278682";
        //String to = "+2348123757061";
        String to = pnum;

        Call call = Call.creator(new PhoneNumber(to), new PhoneNumber(from),
                new URI(location)).create();

        System.out.println(call.getSid() + " sent");
        return call.getSid();

    }

    public void deleteXML(String filedir) {

        LoadPPTfile loadPPTfile = new LoadPPTfile();

        try {

           
            //
            Properties ppt = loadPPTfile.getPptFile();
            String url = ppt.getProperty("xmlFolder");

            File file = new File(filedir);
            file.delete();
           

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
