/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.register;

/**
 *
 * @author Gold
 */
public class Details {
    private String bank_name;

    private String account_number;

    private String bank_code;

    private String authorization_code;

    private String account_name;

    public String getBank_name ()
    {
        return bank_name;
    }

    public void setBank_name (String bank_name)
    {
        this.bank_name = bank_name;
    }

    public String getAccount_number ()
    {
        return account_number;
    }

    public void setAccount_number (String account_number)
    {
        this.account_number = account_number;
    }

    public String getBank_code ()
    {
        return bank_code;
    }

    public void setBank_code (String bank_code)
    {
        this.bank_code = bank_code;
    }

    public String getAuthorization_code() {
        return authorization_code;
    }

    public void setAuthorization_code(String authorization_code) {
        this.authorization_code = authorization_code;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

   

    @Override
    public String toString()
    {
        return "ClassPojo [bank_name = "+bank_name+", account_number = "+account_number+", bank_code = "+bank_code+", authorization_code = "+authorization_code+", account_name = "+account_name+"]";
    }
}
