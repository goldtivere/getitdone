/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.TransferRequest;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Gold
 */
public class SmsManager implements Runnable {

    public static final String ACCOUNT_SID = "ACb3c3535ba81885b306956b481f6e4ac7";
    public static final String AUTH_TOKEN = "91697c8e6c4ae3ebc860c618e0e6d8d8";

    @Override
    public void run() {
        try {

            smsManage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void smsManage() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+2348131248746"),
                new com.twilio.type.PhoneNumber("+16267278682"),
                "Your message")
                .create();

        System.out.println(message.getSid() + " big head");
    }
}
