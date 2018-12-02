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
public class Data {
     private String id;

    private Details details;

    private String email;

    private String description;

    private String integration;

    private String name;

    private String domain;

    private String active;

    private String type;

    private String recipient_code;

    private String currency;

    private String metadata;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Details getDetails ()
    {
        return details;
    }

    public void setDetails (Details details)
    {
        this.details = details;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getIntegration ()
    {
        return integration;
    }

    public void setIntegration (String integration)
    {
        this.integration = integration;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getDomain ()
    {
        return domain;
    }

    public void setDomain (String domain)
    {
        this.domain = domain;
    }

    public String getActive ()
    {
        return active;
    }

    public void setActive (String active)
    {
        this.active = active;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getRecipient_code ()
    {
        return recipient_code;
    }

    public void setRecipient_code (String recipient_code)
    {
        this.recipient_code = recipient_code;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
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
        return "ClassPojo [id = "+id+", details = "+details+", email = "+email+", description = "+description+", integration = "+integration+", name = "+name+", domain = "+domain+", active = "+active+", type = "+type+", recipient_code = "+recipient_code+", currency = "+currency+", metadata = "+metadata+"]";
    }
}
