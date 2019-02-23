/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.MakeServiceWork;

import com.git.dbcon.DateManipulation;
import com.git.dbcon.DbConnectionX;
import com.git.register.UserDetails;
import static com.sun.faces.facelets.util.Path.context;
import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class Service implements Serializable {

    /**
     * this class will be used to create an image directory to store all
     * category pngs * first it will check if the directory exists. if it doesnt
     * it will create a new one. it will check the db to know if directory link
     * already exists.
     */
    private boolean buttonVisible;
    private String direct;
    private String directoryFolder;

    @PostConstruct
    public void init() {

        System.out.println(System.getProperty("user.home") + " how far boss big head");
    }

    private boolean checkFileExist() {
        directoryFolder = "serviceImage";
        File file = new File(System.getProperty("user.home") + "/" + directoryFolder);
        if (file.exists()) {
            setButtonVisible(false);
            return false;
        } else {

            return true;
        }
    }

    private boolean createFile() {
        setDirectoryFolder("serviceImage");
        String direct = System.getProperty("user.home") + getDirectoryFolder();
        setDirect(direct);
        File file = new File(direct);
        return file.mkdirs();
    }

    public void directoryCreated() {
        if (createFile()) {
            FacesContext context = FacesContext.getCurrentInstance();
            saveDirectory();
            context.addMessage(null, new FacesMessage("Directory Created!!"));

        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Directory doesnt exist!"));
        }
    }

    public void saveDirectory() {
        FacesContext context = FacesContext.getCurrentInstance();
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            UserDetails userObj = (UserDetails) context.getExternalContext().getSessionMap().get("sessn_nums");
            String on = String.valueOf(userObj);
            int createdby = userObj.getId();
            con = dbConnections.mySqlDBconnection();
            String insert = "insert into tbdirectory (folderlink,datecreated,createdby)"
                    + "values(?,?,?)";
            pstmt = con.prepareStatement(insert);

            pstmt.setString(1, direct);
            pstmt.setString(2, DateManipulation.dateAndTime());
            pstmt.setInt(3, createdby);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (!(con == null)) {
                con.close();
            }

            if (!(pstmt == null)) {
                pstmt.close();
            }

            if (!(rs == null)) {
                rs.close();
            }

        }
    }

//    public boolean checkFolderUrlExist() {
//        FacesContext context = FacesContext.getCurrentInstance();
//
//        DbConnectionX dbConnections = new DbConnectionX();
//        Connection con = null;
//        ResultSet rs = null;
//        PreparedStatement pstmt = null;
//
//        try {
//
//            con = dbConnections.mySqlDBconnection();
//            String query = "SELECT * FROM tbcategory";
//            pstmt = con.prepareStatement(query);
//            rs = pstmt.executeQuery();
//            //
//            List<CategoryModel> lst = new ArrayList<>();
//            while (rs.next()) {
//
//                CategoryModel coun = new CategoryModel();
//                coun.setId(rs.getInt("id"));
//                coun.setCategory(rs.getString("category"));
//
//                //
//                lst.add(coun);
//            }
//
//            return lst;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//
//        } finally {
//
//            if (!(con == null)) {
//                con.close();
//                con = null;
//            }
//            if (!(pstmt == null)) {
//                pstmt.close();
//                pstmt = null;
//            }
//
//        }
//    }
    public boolean isButtonVisible() {
        return buttonVisible;
    }

    public void setButtonVisible(boolean buttonVisible) {
        this.buttonVisible = buttonVisible;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getDirectoryFolder() {
        return directoryFolder;
    }

    public void setDirectoryFolder(String directoryFolder) {
        this.directoryFolder = directoryFolder;
    }

}
