/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.core;
import com.git.constant.Definitions;

import org.json.JSONObject;
import java.util.HashMap;
/**
 *
 * @author Gold
 */
public class Recipient {
    private ApiConnection apiConnection;
    
    
      public JSONObject createRecipient(String name, String description, String acctNum,
            String bankCode, Object metadata) {
        this.apiConnection = new ApiConnection(Definitions.PAYSTACK_TRANSFER_RECIPIENT);
        ApiQuery apiQuery = new ApiQuery();

        apiQuery.putParams("type", "nuban");
        apiQuery.putParams("name", name);
        apiQuery.putParams("description", description);
        apiQuery.putParams("account_number", acctNum);
        apiQuery.putParams("bankcode", bankCode);
        apiQuery.putParams("currency", "NGN");
        apiQuery.putParams("metadata", metadata);

        return this.apiConnection.connectAndQuery(apiQuery);
    }
}
