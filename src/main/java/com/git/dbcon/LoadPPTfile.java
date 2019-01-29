/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.dbcon;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 *
 * @author: micheal abobade
 * @email: pagims2003@yahoo.com
 * @mobile: 234-8065-711-043
 * @date: 2016-07-31
 */
public class LoadPPTfile implements Serializable {

    private Properties pptFile;
    private String messangerOfTruth;

    public String ImagePath() {
        Properties prop = new Properties();
        try {
            //System.out.println("test");
            prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
            //System.out.println("user.home: " + System.getProperty("user.home"));
            String host = prop.getProperty("host").toString();
            String username = prop.getProperty("username").toString();
            String password = prop.getProperty("password").toString();
            String driver = prop.getProperty("driver").toString();
            String path = prop.getProperty("path").toString();
            return path;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String SitePath() {
        Properties prop = new Properties();
        try {
            //System.out.println("test");
            prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
            //System.out.println("user.home: " + System.getProperty("user.home"));
            String host = prop.getProperty("host").toString();
            String username = prop.getProperty("username").toString();
            String password = prop.getProperty("password").toString();
            String driver = prop.getProperty("driver").toString();
            String path = prop.getProperty("sitepath").toString();
            return path;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String callback() {
        Properties prop = new Properties();
        try {
            //System.out.println("test");
            prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
            //System.out.println("user.home: " + System.getProperty("user.home"));        
            String path = prop.getProperty("callback").toString();
            return path;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String xmlFolder() {
        Properties prop = new Properties();
        try {
            //System.out.println("test");
            prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
            //System.out.println("user.home: " + System.getProperty("user.home"));        
            String path = prop.getProperty("xmlFolder").toString();
            return path;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String authString() {
        Properties prop = new Properties();
        try {
            //System.out.println("test");
            prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
            //System.out.println("user.home: " + System.getProperty("user.home"));        
            String path = prop.getProperty("accountSid").toString();
            return path;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String authToken() {
        Properties prop = new Properties();
        try {
            //System.out.println("test");
            prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
            //System.out.println("user.home: " + System.getProperty("user.home"));        
            String path = prop.getProperty("authToken").toString();
            return path;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String authToken() {
        Properties prop = new Properties();
        try {
            //System.out.println("test");
            prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
            //System.out.println("user.home: " + System.getProperty("user.home"));        
            String path = prop.getProperty("authToken").toString();
            return path;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String from() {
        Properties prop = new Properties();
        try {
            //System.out.println("test");
            prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
            //System.out.println("user.home: " + System.getProperty("user.home"));        
            String path = prop.getProperty("from").toString();
            return path;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String[] phoneNumbers() {
        Properties prop = new Properties();
        try {
            //System.out.println("test");
            prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
            //System.out.println("user.home: " + System.getProperty("user.home"));        
            String phonenumber = prop.getProperty("phonenumber").toString();
            return phonenumber.split(",");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String transactionConfirm() {
        Properties prop = new Properties();
        try {
            //System.out.println("test");
            prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
            //System.out.println("user.home: " + System.getProperty("user.home"));        
            String confirmTransaction = prop.getProperty("confirmTransaction").toString();
            return confirmTransaction;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean isLoadPPtFile() throws Exception {

        //
        InputStream inp = null;
        String resource_file = "/opt/tomcat/webapps/images/";
        Properties dPPT = null;

        try {

            dPPT = new Properties();
            dPPT.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
            inp = getClass().getClassLoader().getResourceAsStream(resource_file);
            dPPT.load(inp);

            setPptFile(dPPT);

            return true;

        } catch (Exception ex) {

            setPptFile(null);
            ex.printStackTrace();
            setMessangerOfTruth(ex.getMessage());

            return false;

        } finally {

            if (!(inp == null)) {
                inp.close();
            }
        }

    }//end isLoadPPtFile()

    /**
     * @return the pptFile
     */
    public Properties getPptFile() {
        return pptFile;
    }

    /**
     * @param pptFile the pptFile to set
     */
    public void setPptFile(Properties pptFile) {
        this.pptFile = pptFile;
    }

    /**
     * @return the messangerOfTruth
     */
    public String getMessangerOfTruth() {
        return messangerOfTruth;
    }

    /**
     * @param messangerOfTruth the messangerOfTruth to set
     */
    public void setMessangerOfTruth(String messangerOfTruth) {
        this.messangerOfTruth = messangerOfTruth;
    }

}
