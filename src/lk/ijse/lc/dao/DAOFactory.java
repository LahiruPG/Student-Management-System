/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.lc.dao;

import lk.ijse.lc.dao.custom.impl.BatchDAOImpl;
import lk.ijse.lc.dao.custom.impl.CourseDAOImpl;
import lk.ijse.lc.dao.custom.impl.GuardianDAOImpl;
import lk.ijse.lc.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.lc.dao.custom.impl.RegestrationDAOImpl;
import lk.ijse.lc.dao.custom.impl.StudentDAOImpl;

/**
 *
 * @author LahiruPG
 */
public class DAOFactory {

    public enum DAOTypes {
        STUDENT, COURSE, BATCH, PAYMENT, GURDIAN, REGESTRATION
    }

    public static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes daoType) {
        switch (daoType) {
            case STUDENT:
                return new StudentDAOImpl();
            case COURSE:
                return new CourseDAOImpl();
            case BATCH:
                return new BatchDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case GURDIAN:
                return new GuardianDAOImpl();
            case REGESTRATION:
                return new RegestrationDAOImpl();
            default:
                return null;
        }
    }

}
