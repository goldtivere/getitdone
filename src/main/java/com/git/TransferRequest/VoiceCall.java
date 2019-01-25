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

    public static final String ACCOUNT_SID = "ACb3c3535ba81885b306956b481f6e4ac7";
    public static final String AUTH_TOKEN = "91697c8e6c4ae3ebc860c618e0e6d8d8";

    public void runIt() throws URISyntaxException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String from = "+16267278682";
        //String to = "+2348123757061";
        String to = "+2348131248746";   

        Call call = Call.creator(new PhoneNumber(to), new PhoneNumber(from),
                new URI("http://wedeycome.com/doc/voice.xml")).create();

        System.out.println(call.getSid() + " sent");

    }
}
