/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.transaction;

import com.git.getitdone.SelectOptionMenu;
import com.git.getitdone.TrxnStatusModel;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Gold
 */
@ManagedBean
@ViewScoped
public class ViewTransactions implements Serializable {

    private List<TrxnStatusModel> model;
    private SelectOptionMenu menu= new SelectOptionMenu();

    @PostConstruct
    public void init() {
        try {
            model = menu.trxnStatus();            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TrxnStatusModel> getModel() {
        return model;
    }

    public void setModel(List<TrxnStatusModel> model) {
        this.model = model;
    }

}
