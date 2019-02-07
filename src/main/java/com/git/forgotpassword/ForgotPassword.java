/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.forgotpassword;

/**
 *
 * @author Gold
 */
public class ForgotPassword {
    
    public boolean checkIfNumExists() {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = dbConnections.mySqlDBconnection();
        try {
            String queryProfile = "select * from tbtempregistration "
                    + "where phonenumber=? and phoneverified=?";
            pstmt = con.prepareStatement(queryProfile);
            pstmt.setString(1, getPnum());
            pstmt.setBoolean(2, true);
            rs = pstmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
