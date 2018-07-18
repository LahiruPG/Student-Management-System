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
import lk.ijse.lc.core.dto.GurdianDTO;
import lk.ijse.lc.dao.custom.GurdianDAO;
import lk.ijse.lc.dao.db.DBConnection;

/**
 *
 * @author LahiruPG
 */
public class GuardianDAOImpl implements GurdianDAO{

    @Override
    public boolean add(GurdianDTO gurdian) throws Exception {
         Connection conn=DBConnection.getInstance().getConnection();
        String SQL="INSERT INTO gurdian VALUES (?,?,?,?,?);";
        PreparedStatement pstm = conn.prepareStatement(SQL);
        pstm.setObject(1, gurdian.getGid());
        pstm.setObject(2, gurdian.getSid());
        pstm.setObject(3, gurdian.getName());
        pstm.setObject(4, gurdian.getContact());
        pstm.setObject(5, gurdian.getAddress());
        int afRows=pstm.executeUpdate();
        return(afRows>0);
    }

    @Override
    public boolean update(GurdianDTO gurdian) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE gurdian SET sid=?, name=?, address=?, tele=?  WHERE gid=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, gurdian.getSid());
        pstm.setObject(2, gurdian.getName());
        pstm.setObject(3, gurdian.getContact());
        pstm.setObject(4, gurdian.getAddress());
        pstm.setObject(5, gurdian.getGid());
        int affectedRows = pstm.executeUpdate();
        return (affectedRows > 0);
    }

    @Override
    public boolean delete(GurdianDTO gurdian) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM gurdian WHERE gid = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, gurdian.getGid());
        int affectedRows = pstm.executeUpdate();
        return (affectedRows > 0);
    }

    @Override
    public GurdianDTO search(GurdianDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<GurdianDTO> getAll() throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String SQL = "SELECT * FROM gurdian";
        Statement stm = conn.createStatement();
        ResultSet rst  = stm.executeQuery(SQL);
        
         ArrayList<GurdianDTO> allGurdian = null;
         while (rst.next()) {
            if (allGurdian == null) {
                allGurdian = new ArrayList<>();
            }

            allGurdian.add(new GurdianDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));

        }

        return allGurdian;
    }

    
    
}
