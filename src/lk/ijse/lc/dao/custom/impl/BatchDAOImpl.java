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
import lk.ijse.lc.core.dto.BatchDTO;
import lk.ijse.lc.dao.custom.BatchDAO;
import lk.ijse.lc.dao.db.DBConnection;

/**
 *
 * @author LahiruPG
 */
public class BatchDAOImpl implements BatchDAO {

    @Override
    public boolean add(BatchDTO batch) throws Exception {
        Connection conn=DBConnection.getInstance().getConnection();
        String SQL="INSERT INTO batch VALUES (?,?,?);";
        PreparedStatement pstm = conn.prepareStatement(SQL);
        pstm.setObject(1, batch.getBid());
        pstm.setObject(2, batch.getName());
        pstm.setObject(3, batch.getCid());
        int afRows=pstm.executeUpdate();
        return(afRows>0);
    }

    @Override
    public boolean update(BatchDTO batch) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE batch SET name=?, cid=? WHERE bid=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, batch.getName());
        pstm.setObject(2, batch.getCid());
        pstm.setObject(3, batch.getBid());
        int affectedRows = pstm.executeUpdate();
        return (affectedRows > 0);
    }

    @Override
    public boolean delete(BatchDTO batch) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM batch WHERE bid = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, batch.getBid());
        int affectedRows = pstm.executeUpdate();
        return (affectedRows > 0);
    }

    @Override
    public BatchDTO search(BatchDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<BatchDTO> getAll() throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String SQL = "SELECT * FROM batch";
        Statement stm = conn.createStatement();
        ResultSet rst  = stm.executeQuery(SQL);
        
         ArrayList<BatchDTO> allBatch = null;
         while (rst.next()) {
            if (allBatch == null) {
                allBatch = new ArrayList<>();
            }

            allBatch.add(new BatchDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            ));

        }

        return allBatch;
    }

}
