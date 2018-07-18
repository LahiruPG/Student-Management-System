/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.lc.dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.lc.core.dto.CourseDTO;
import lk.ijse.lc.core.dto.RegestrationDTO;
import lk.ijse.lc.dao.custom.RegestrationDAO;
import lk.ijse.lc.dao.db.DBConnection;

/**
 *
 * @author LahiruPG
 */
public class RegestrationDAOImpl implements RegestrationDAO{

    @Override
    public boolean add(RegestrationDTO regestration) throws Exception {
        Connection conn=DBConnection.getInstance().getConnection();
        String SQL="INSERT INTO regestration VALUES (?,?,?,?,?);";
        PreparedStatement pstm = conn.prepareStatement(SQL);
        pstm.setObject(1, regestration.getRid());
        pstm.setObject(2, regestration.getSid());
        pstm.setObject(3, regestration.getBid());
        pstm.setObject(4, regestration.getFee());
        pstm.setObject(5, regestration.getDate());
        int afRows=pstm.executeUpdate();
        return(afRows>0);
    }

    @Override
    public boolean update(RegestrationDTO regestration) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE regestration SET sid=?, bid=?, reg_fee=?, date=? WHERE rid=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, regestration.getSid());
        pstm.setObject(2, regestration.getBid());
        pstm.setObject(3, regestration.getFee());
        pstm.setObject(4, regestration.getDate());
        pstm.setObject(5, regestration.getRid());
        int affectedRows = pstm.executeUpdate();
        return (affectedRows > 0);
    }

    @Override
    public boolean delete(RegestrationDTO regestration) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM regestration WHERE rid = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, regestration.getRid());
        int affectedRows = pstm.executeUpdate();
        return (affectedRows > 0);
    }

    @Override
    public RegestrationDTO search(RegestrationDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<RegestrationDTO> getAll() throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String SQL = "SELECT * FROM regestration";
        Statement stm = conn.createStatement();
        ResultSet rst  = stm.executeQuery(SQL);
        
         ArrayList<RegestrationDTO> allRegestration = null;
         while (rst.next()) {
            if (allRegestration == null) {
                allRegestration = new ArrayList<>();
            }

            allRegestration.add(new RegestrationDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getBigDecimal(4),
                    rst.getString(5)
            ));

        }

        return allRegestration;
    }
    
}
