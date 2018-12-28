/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.filter;

/**
 *
 * @author Gold
 */
public class Authorization {
    private String exp_year;

    private String exp_month;

    private String card_type;

    private String reusable;

    private String brand;

    private String bank;

    private String bin;

    private String last4;

    private String authorization_code;

    private String country_code;

    private String channel;

    private String signature;

    public String getExp_year ()
    {
        return exp_year;
    }

    public void setExp_year (String exp_year)
    {
        this.exp_year = exp_year;
    }

    public String getExp_month ()
    {
        return exp_month;
    }

    public void setExp_month (String exp_month)
    {
        this.exp_month = exp_month;
    }

    public String getCard_type ()
    {
        return card_type;
    }

    public void setCard_type (String card_type)
    {
        this.card_type = card_type;
    }

    public String getReusable ()
    {
        return reusable;
    }

    public void setReusable (String reusable)
    {
        this.reusable = reusable;
    }

    public String getBrand ()
    {
        return brand;
    }

    public void setBrand (String brand)
    {
        this.brand = brand;
    }

    public String getBank ()
    {
        return bank;
    }

    public void setBank (String bank)
    {
        this.bank = bank;
    }

    public String getBin ()
    {
        return bin;
    }

    public void setBin (String bin)
    {
        this.bin = bin;
    }

    public String getLast4 ()
    {
        return last4;
    }

    public void setLast4 (String last4)
    {
        this.last4 = last4;
    }

    public String getAuthorization_code ()
    {
        return authorization_code;
    }

    public void setAuthorization_code (String authorization_code)
    {
        this.authorization_code = authorization_code;
    }

    public String getCountry_code ()
    {
        return country_code;
    }

    public void setCountry_code (String country_code)
    {
        this.country_code = country_code;
    }

    public String getChannel ()
    {
        return channel;
    }

    public void setChannel (String channel)
    {
        this.channel = channel;
    }

    public String getSignature ()
    {
        return signature;
    }

    public void setSignature (String signature)
    {
        this.signature = signature;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [exp_year = "+exp_year+", exp_month = "+exp_month+", card_type = "+card_type+", reusable = "+reusable+", brand = "+brand+", bank = "+bank+", bin = "+bin+", last4 = "+last4+", authorization_code = "+authorization_code+", country_code = "+country_code+", channel = "+channel+", signature = "+signature+"]";
    }
}
