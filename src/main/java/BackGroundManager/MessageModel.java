 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackGroundManager;

import java.io.Serializable;

/**
 *
 * @author Gold
 */
public class MessageModel implements Serializable{
    private int id;
    private String phonenumber;
    private String code;
    private String message;
    private String filename;
    private String xmlfilename;
    private String rpnum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getXmlfilename() {
        return xmlfilename;
    }

    public void setXmlfilename(String xmlfilename) {
        this.xmlfilename = xmlfilename;
    }

    public String getRpnum() {
        return rpnum;
    }

    public void setRpnum(String rpnum) {
        this.rpnum = rpnum;
    }
    
    
}
