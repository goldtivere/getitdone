/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Gold
 */
public class AddServiceModel {
    private int id;
    private String serviceName;
    private String serviceDesc;
    private String img_location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getImg_location() {
        return img_location;
    }

    public void setImg_location(String img_location) {
        this.img_location = img_location;
    }
    
    
}
