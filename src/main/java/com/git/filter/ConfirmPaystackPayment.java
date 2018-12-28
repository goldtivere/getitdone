/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.filter;

import com.git.core.Transactions;
import com.git.dbcon.DbConnectionX;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Gold
 */
public class ConfirmPaystackPayment implements Runnable{

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
      public List<String> referenceName() throws SQLException {
        Connection con = null;
        DbConnectionX dbCon = new DbConnectionX();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = dbCon.mySqlDBconnection();
            String queryPaystackDetails = "select reference from tbtransaction where iscompleted=false";                  
            //
            pstmt = con.prepareStatement(queryPaystackDetails);
            rs = pstmt.executeQuery();
            List<String> dbval = new ArrayList<>();
            while (rs.next()) {
                dbval.add(rs.getString("reference"));
            }

            return dbval;
        } catch (Exception e) {

            System.out.print("Exception from doTransaction method.....");
            e.printStackTrace();
            return null;

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

    }//end doTransaction...
      
      public void checkIfTrue()
      {
          Transactions trans = new Transactions();
          try
          {
              for(String val: referenceName())
              {
                  JSONObject bn = trans.verifyTransaction(val);
              }
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
      }
}
