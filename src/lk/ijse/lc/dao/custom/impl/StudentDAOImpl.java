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
import lk.ijse.lc.core.dto.StudentDTO;
import lk.ijse.lc.dao.custom.StudentDAO;
import lk.ijse.lc.dao.db.DBConnection;

/**
 *
 * @author LahiruPG
 */
public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean add(StudentDTO student) throws Exception {
        Connection conn=DBConnection.getInstance().getConnection();
        String SQL="INSERT INTO student VALUES (?,?,?,?,?,?,?);";
        PreparedStatement pstm = conn.prepareStatement(SQL);
        pstm.setObject(1, student.getSid());
        pstm.setObject(2, student.getName());
        pstm.setObject(3, student.getGender());
        pstm.setObject(4, student.getAddress());
        pstm.setObject(5, student.getContact());
        pstm.setObject(6, student.getDob());
        pstm.setObject(7, student.getNic());
        int afRows=pstm.executeUpdate();
        return(afRows>0);
    }

    @Override
    public boolean update(StudentDTO student) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE student SET name=?, gender=?, address=?, tele=?, dob=?, nic=? WHERE sid=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, student.getName());
        pstm.setObject(2, student.getGender());
        pstm.setObject(3, student.getAddress());
        pstm.setObject(4, student.getContact());
        pstm.setObject(5, student.getDob());
        pstm.setObject(6, student.getNic());
        pstm.setObject(7, student.getSid());
        int affectedRows = pstm.executeUpdate();
        return (affectedRows > 0);
    }

    @Override
    public boolean delete(StudentDTO student) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM student WHERE sid = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, student.getSid());
        int affectedRows = pstm.executeUpdate();
        return (affectedRows > 0);
    }

    @Override
    public StudentDTO search(StudentDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<StudentDTO> getAll() throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String SQL = "SELECT * FROM student";
        Statement stm = conn.createStatement();
        ResultSet rst  = stm.executeQuery(SQL);
        
         ArrayList<StudentDTO> allStudent = null;
         while (rst.next()) {
            if (allStudent == null) {
                allStudent = new ArrayList<>();
            }

            allStudent.add(new StudentDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            ));

        }

        return allStudent;
    }
    
    
}
