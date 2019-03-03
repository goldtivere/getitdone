/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import com.git.dbcon.DbConnectionX;
import com.git.getitdone.SelectOptionMenu;
import com.git.getitdone.CategoryModel;
import com.git.getitdone.SessionTest;
import com.git.request.RequestModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 *
 * @author Gold
 */
@ManagedBean
@ViewScoped
public class DisplayVendor implements Serializable {

    private ItemModel itemModel = new ItemModel();
    private List<RequestModel> requestList;
    private String messangerOfTruth;
    private SessionTest test = new SessionTest();
    private SelectOptionMenu selectOptionMenu = new SelectOptionMenu();

    public List<RequestModel> requestLst(int val) throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        SessionTest test = new SessionTest();

        try {

            con = dbConnections.mySqlDBconnection();
            String query = "select distinct g.vendorfk,g.category,g.quantity,g.itemname,g.locationfk,p.phonenumber,p.corporatename,p.coveragelocation, g.amount from "
                    + "tbvendoritem g inner join tbvendor p on g.vendorfk=p.id "
                    + " where p.isdeleted=false and g.category=? and g.locationfk=?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, itemModel.getLocationfk());
            rs = pstmt.executeQuery();
            //
            List<RequestModel> lst = new ArrayList<>();
            while (rs.next()) {

                RequestModel coun = new RequestModel();
                coun.setVendorfk(rs.getInt("vendorfk"));
                coun.setCatId(rs.getInt("category"));
                coun.setAmount(rs.getDouble("amount"));
                coun.setCorporatename(rs.getString("corporatename"));
                coun.setCoverageLocation(rs.getString("coveragelocation"));
                coun.setQuan(rs.getInt("quantity"));
                coun.setItemname(rs.getString("itemname"));
                coun.setPhoneNumber(rs.getString("phonenumber"));
                coun.setLocationfk(rs.getInt("locationfk"));
                System.out.println(coun.isCompleted() + " hi " + coun.isRequestStatus() + " yeah " + coun.isTrxnpaid());
                //                
                lst.add(coun);

            }

            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {

            if (!(con == null)) {
                con.close();
                con = null;
            }
            if (!(pstmt == null)) {
                pstmt.close();
                pstmt = null;
            }

        }
    }

    public void makeVisible(int locationFk) {

        FacesMessage message;
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (test.test()) {
                requestList = requestLst(locationFk);
                System.out.println(locationFk + " yeah im here");
            } else {
                setMessangerOfTruth("User Session not found please sign out and back in ");
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessangerOfTruth(), getMessangerOfTruth());
                context.addMessage(null, message);
            }
        } catch (NullPointerException e) {
            setMessangerOfTruth("User Session not found please sign out and back in ");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessangerOfTruth(), getMessangerOfTruth());
            context.addMessage(null, message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ItemModel getItemModel() {
        return itemModel;
    }

    public void setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }

    public List<RequestModel> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<RequestModel> requestList) {
        this.requestList = requestList;
    }

    public String getMessangerOfTruth() {
        return messangerOfTruth;
    }

    public void setMessangerOfTruth(String messangerOfTruth) {
        this.messangerOfTruth = messangerOfTruth;
    }

    public SelectOptionMenu getSelectOptionMenu() {
        return selectOptionMenu;
    }

    public void setSelectOptionMenu(SelectOptionMenu selectOptionMenu) {
        this.selectOptionMenu = selectOptionMenu;
    }

}
