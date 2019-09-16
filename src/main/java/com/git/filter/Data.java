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
public class Data {
    private String paidAt;

    private String status;

    private Subaccount subaccount;

    private String transaction_date;

    private Authorization authorization;
    
    private String order_id;

    private String fees;

    private String paid_at;

    private Customer customer;

    private Log log;

    private String reference;

    private String currency;

    private String id;

    private String amount;

    private String message;

    private String ip_address;

    private String plan;

    private String createdAt;

    private String domain;

    private String fees_split;

    private String gateway_response;

    private String created_at;

    private Plan_object plan_object;

    private String channel;

    private String metadata;

    public String getPaidAt ()
    {
        return paidAt;
    }
public String getOrder_id()
{
    return order_id;
}
    public void setOrder_id(String order_id)
    {
        this.order_id=order_id;
    }
    public void setPaidAt (String paidAt)
    {
        this.paidAt = paidAt;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public Subaccount getSubaccount ()
    {
        return subaccount;
    }

    public void setSubaccount (Subaccount subaccount)
    {
        this.subaccount = subaccount;
    }

    public String getTransaction_date ()
    {
        return transaction_date;
    }

    public void setTransaction_date (String transaction_date)
    {
        this.transaction_date = transaction_date;
    }

    public Authorization getAuthorization ()
    {
        return authorization;
    }

    public void setAuthorization (Authorization authorization)
    {
        this.authorization = authorization;
    }

    public String getFees ()
    {
        return fees;
    }

    public void setFees (String fees)
    {
        this.fees = fees;
    }

    public String getPaid_at ()
    {
        return paid_at;
    }

    public void setPaid_at (String paid_at)
    {
        this.paid_at = paid_at;
    }

    public Customer getCustomer ()
    {
        return customer;
    }

    public void setCustomer (Customer customer)
    {
        this.customer = customer;
    }

    public Log getLog ()
    {
        return log;
    }

    public void setLog (Log log)
    {
        this.log = log;
    }

    public String getReference ()
    {
        return reference;
    }

    public void setReference (String reference)
    {
        this.reference = reference;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getIp_address ()
    {
        return ip_address;
    }

    public void setIp_address (String ip_address)
    {
        this.ip_address = ip_address;
    }

    public String getPlan ()
    {
        return plan;
    }

    public void setPlan (String plan)
    {
        this.plan = plan;
    }

    public String getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (String createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getDomain ()
    {
        return domain;
    }

    public void setDomain (String domain)
    {
        this.domain = domain;
    }

    public String getFees_split ()
    {
        return fees_split;
    }

    public void setFees_split (String fees_split)
    {
        this.fees_split = fees_split;
    }

    public String getGateway_response ()
    {
        return gateway_response;
    }

    public void setGateway_response (String gateway_response)
    {
        this.gateway_response = gateway_response;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public Plan_object getPlan_object ()
    {
        return plan_object;
    }

    public void setPlan_object (Plan_object plan_object)
    {
        this.plan_object = plan_object;
    }

    public String getChannel ()
    {
        return channel;
    }

    public void setChannel (String channel)
    {
        this.channel = channel;
    }

    public String getMetadata ()
    {
        return metadata;
    }

    public void setMetadata (String metadata)
    {
        this.metadata = metadata;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [paidAt = "+paidAt+", status = "+status+", subaccount = "+subaccount+", transaction_date = "+transaction_date+", authorization = "+authorization+", fees = "+fees+", paid_at = "+paid_at+", customer = "+customer+", log = "+log+", reference = "+reference+", currency = "+currency+", id = "+id+", amount = "+amount+", message = "+message+", ip_address = "+ip_address+", plan = "+plan+", createdAt = "+createdAt+", domain = "+domain+", fees_split = "+fees_split+", gateway_response = "+gateway_response+", created_at = "+created_at+", plan_object = "+plan_object+", channel = "+channel+", metadata = "+metadata+"]";
    }
}
