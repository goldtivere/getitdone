/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.TransferRequest;

import java.net.URI;
import java.net.URISyntaxException;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Gold
 */
//@ManagedBean
//@ViewScoped
public class VoiceCall {

    public static final String ACCOUNT_SID = "ACccd5d9435d1e0d38e98ff177335aeba2";
    public static final String AUTH_TOKEN = "a5c7460c3b9ae10f09381e723f2316e5";

    public void runIt() throws URISyntaxException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String from = "+14783775151";
        //String to = "+2348123757061";
        String to = "+2348131248746";

        Call call = Call.creator(new PhoneNumber(to), new PhoneNumber(from),
                new URI("http://demo.twilio.com/docs/voice.xml")).create();

        System.out.println(call.getSid() + " sent");

    }
}
