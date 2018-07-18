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
import lk.ijse.lc.core.dto.PaymentDTO;
import lk.ijse.lc.dao.custom.PaymentDAO;
import lk.ijse.lc.dao.db.DBConnection;

/**
 *
 * @author LahiruPG
 */
public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean add(PaymentDTO payment) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String SQL = "INSERT INTO payment VALUES (?,?,?,?,?);";
        PreparedStatement pstm = conn.prepareStatement(SQL);
        pstm.setObject(1, payment.getPid());
        pstm.setObject(2, payment.getRid());
        pstm.setObject(3, payment.getCid());
        pstm.setObject(4, payment.getAmount());
        pstm.setObject(5, payment.getDate());
        int afRows = pstm.executeUpdate();
        return (afRows > 0);
    }

    @Override
    public boolean update(PaymentDTO payment) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE payment SET rid=?, cid=?, amount=?, date=?  WHERE pid=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, payment.getRid());
        pstm.setObject(2, payment.getCid());
        pstm.setObject(3, payment.getAmount());
        pstm.setObject(4, payment.getDate());
        pstm.setObject(5, payment.getPid());
        int affectedRows = pstm.executeUpdate();
        return (affectedRows > 0);
    }

    @Override
    public boolean delete(PaymentDTO payment) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM payment WHERE pid = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, payment.getPid());
        int affectedRows = pstm.executeUpdate();
        return (affectedRows > 0);
    }

    @Override
    public PaymentDTO search(PaymentDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PaymentDTO> getAll() throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String SQL = "SELECT * FROM payment";
        Statement stm = conn.createStatement();
        ResultSet rst = stm.executeQuery(SQL);

        ArrayList<PaymentDTO> allPayments = null;
        while (rst.next()) {
            if (allPayments == null) {
                allPayments = new ArrayList<>();
            }

            allPayments.add(new PaymentDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getBigDecimal(4),
                    rst.getString(5)
            ));

        }

        return allPayments;
    }

}
