/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.request;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gold
 */
@ManagedBean
@ViewScoped
public class PaymentDetails implements Serializable{
    private String messangerOfTruth;
    private List<RequestModel> mode;
    private double sumValue;
    
    @PostConstruct
     public void init() {
         double sum=0;
         for(RequestModel mod: value())
         {
             sum+=mod.getAmount();
         }
         setSumValue(sum);
       mode=value();
       System.out.println("Hi got here");
    }

    public List<RequestModel> value()
    {
         try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg;

            String stuValue = null;
            List<RequestModel> request = (List<RequestModel>) ctx.getExternalContext().getApplicationMap().get("request");

            if (request != null || !request.isEmpty()) {                
              return request;
            } else {
                setMessangerOfTruth("Session Expired for this Student. Please select student and try again1!!");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessangerOfTruth(), getMessangerOfTruth());
                ctx.addMessage(null, msg);
                return null;
            }

           

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public List<RequestModel> getMode() {
        return mode;
    }

    public void setMode(List<RequestModel> mode) {
        this.mode = mode;
    }

    public String getMessangerOfTruth() {
        return messangerOfTruth;
    }

    public void setMessangerOfTruth(String messangerOfTruth) {
        this.messangerOfTruth = messangerOfTruth;
    }

    public double getSumValue() {
        return sumValue;
    }

    public void setSumValue(double sumValue) {
        this.sumValue = sumValue;
    }
    
     
    
}
