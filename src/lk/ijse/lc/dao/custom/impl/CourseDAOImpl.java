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
import lk.ijse.lc.dao.custom.CourseDAO;
import lk.ijse.lc.dao.db.DBConnection;

/**
 *
 * @author LahiruPG
 */
public class CourseDAOImpl implements CourseDAO {

    @Override
    public boolean add(CourseDTO Course) throws Exception {
        Connection conn=DBConnection.getInstance().getConnection();
        String SQL="INSERT INTO course VALUES (?,?,?,?);";
        PreparedStatement pstm = conn.prepareStatement(SQL);
        pstm.setObject(1, Course.getCid());
        pstm.setObject(2, Course.getName());
        pstm.setObject(3, Course.getFee());
        pstm.setObject(4, Course.getDuration());
        int afRows=pstm.executeUpdate();
        return(afRows>0);
        
    }

    @Override
    public boolean update(CourseDTO course) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE course SET name=?, fee=?, duration=? WHERE cid=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, course.getName());
        pstm.setObject(2, course.getFee());
        pstm.setObject(3, course.getDuration());
        pstm.setObject(4, course.getCid());
        int affectedRows = pstm.executeUpdate();
        return (affectedRows > 0);
    }

    @Override
    public boolean delete(CourseDTO course) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM course WHERE cid = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, course.getCid());
        int affectedRows = pstm.executeUpdate();
        return (affectedRows > 0);
    }

    @Override
    public CourseDTO search(CourseDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CourseDTO> getAll() throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String SQL = "SELECT * FROM course";
        Statement stm = conn.createStatement();
        ResultSet rst  = stm.executeQuery(SQL);
        
         ArrayList<CourseDTO> allCourse = null;
         while (rst.next()) {
            if (allCourse == null) {
                allCourse = new ArrayList<>();
            }

            allCourse.add(new CourseDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getBigDecimal(3),
                    rst.getString(4)
            ));

        }

        return allCourse;
    }
    
}
