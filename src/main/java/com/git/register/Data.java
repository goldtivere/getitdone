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
    private String updatedAt;

    private String id;

    private String is_deleted;

    private Details details;

    private String integration;

    private String description;

    private String createdAt;

    private String name;

    private String domain;

    private String active;

    private String type;

    private String recipient_code;

    private Metadata metadata;

    private String currency;

    public String getUpdatedAt ()
    {
        return updatedAt;
    }

    public void setUpdatedAt (String updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getIs_deleted ()
    {
        return is_deleted;
    }

    public void setIs_deleted (String is_deleted)
    {
        this.is_deleted = is_deleted;
    }

    public Details getDetails ()
    {
        return details;
    }

    public void setDetails (Details details)
    {
        this.details = details;
    }

    public String getIntegration ()
    {
        return integration;
    }

    public void setIntegration (String integration)
    {
        this.integration = integration;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (String createdAt)
    {
        this.createdAt = createdAt;
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

    public Metadata getMetadata ()
    {
        return metadata;
    }

    public void setMetadata (Metadata metadata)
    {
        this.metadata = metadata;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [updatedAt = "+updatedAt+", id = "+id+", is_deleted = "+is_deleted+", details = "+details+", integration = "+integration+", description = "+description+", createdAt = "+createdAt+", name = "+name+", domain = "+domain+", active = "+active+", type = "+type+", recipient_code = "+recipient_code+", metadata = "+metadata+", currency = "+currency+"]";
    }
}
