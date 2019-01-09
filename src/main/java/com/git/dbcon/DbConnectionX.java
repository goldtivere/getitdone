/**
 * @author Osawota Gold Tivere
 * @email:goldtive@gmail.com
 * @version 1.0
 * @mobile 08131248746
 * @Date 2017-01-28
 */
package com.git.dbcon;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectionX implements Serializable {

    LoadPPTfile loadPPTfile = new LoadPPTfile();
    private String messangerOfTruth;
    private boolean testconnection;

    public Connection mySqlDBconnection() {
        try {

            Properties prop = new Properties();
            //System.out.println("test");
            prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
            //System.out.println("user.home: " + System.getProperty("user.home"));
            String host = prop.getProperty("host").toString();
            String username = prop.getProperty("username").toString();
            String password = prop.getProperty("password").toString();
            String driver = prop.getProperty("driver").toString();

//            System.out.println("host: " + host + "\nusername: " + username + "\npassword: " + password + "\ndriver: " + driver);
            Class.forName(driver);
//             System.out.println("--------------------------");
//            System.out.println("DRIVER: " + driver);
            Connection connection = DriverManager.getConnection(host, username, password);
//            System.out.println("CONNECTION: " + connection);

            return connection;
        } catch (Exception e) {

            setTestconnection(false);
            setMessangerOfTruth("Error from DbConnection.class " + e.getMessage());
            e.printStackTrace();
            return null;

        } 
    }

    public Connection mySqlDBconnections() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/bookings";
            //String dbName = "bookings";
            Statement stmt = null;
            ResultSet rs = null;
            PreparedStatement pstmt;
            String driver = "com.mysql.jdbc.Driver";
            String databaseUserName = "root";
            String databasePassword = "Caroline93*";

            Class.forName(driver);
            conn = DriverManager.getConnection(url, databaseUserName, databasePassword);

            return conn;

        } catch (Exception e) {

            setTestconnection(false);
            setMessangerOfTruth("Error from DbConnection.class " + e.getMessage());
            return null;

        }

    }//end myConnection()

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

    /**
     * @return the testconnection
     */
    public boolean isTestconnection() {
        return testconnection;
    }

    /**
     * @param testconnection the testconnection to set
     */
    public void setTestconnection(boolean testconnection) {
        this.testconnection = testconnection;
    }

}
