/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.request;

/**
 *
 * @author Gold paystack payment initialiser response
 */
public class DataPojo {

    private String access_code;

    private String authorization_url;

    private String reference;

    public String getAccess_code() {
        return access_code;
    }

    public void setAccess_code(String access_code) {
        this.access_code = access_code;
    }

    public String getAuthorization_url() {
        return authorization_url;
    }

    public void setAuthorization_url(String authorization_url) {
        this.authorization_url = authorization_url;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "ClassPojo [access_code = " + access_code + ", authorization_url = " + authorization_url + ", reference = " + reference + "]";
    }
}
