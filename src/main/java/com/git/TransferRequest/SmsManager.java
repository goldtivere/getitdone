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

   

    @Override
    public void run() {
        try {

            //smsManage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void smsManage() {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message = Message.creator(
//                new com.twilio.type.PhoneNumber("+2348131248746"),
//                new com.twilio.type.PhoneNumber("+16267278682"),
//                "Your message")
//                .create();
//
//        System.out.println(message.getSid() + " big head");
//    }
}
