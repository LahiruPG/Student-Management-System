/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.lc.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lk.ijse.lc.dao.db.DBConnection;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class MainFormController implements Initializable {

    private AnchorPane apnStuddentReg;
    @FXML
    private Label lblTime;
    @FXML
    private AnchorPane apnAttendences;
    private AnchorPane apnCourse;
    @FXML
    private Button btnAttendence;

    @FXML
    private AnchorPane apnRoot;
    @FXML
    private Button btnManageStudent;
    @FXML
    private BorderPane rootPane;
    @FXML
    private AnchorPane apnMain;
    @FXML
    private Button btnManagePayment;
    @FXML
    private Button btnManageBatch;
    @FXML
    private Button btnStudentReg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTime();
        Connection conn = DBConnection.getInstance().getConnection();
        manageStudent();

    }

    public void setTime() {
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYY-MM-dd");
        lblTime.setText(sdf.format(d1));
    }

    @FXML
    private void btnCourseAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/lc/view/ManageCourse.fxml"));
        Pane cmdPane = (Pane) loader.load();
        rootPane.setCenter(cmdPane);
    }

    @FXML
    private void btnManageStudentAction(ActionEvent event) throws IOException {
        manageStudent();
    }

    @FXML
    private void btnStudentRegAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/lc/view/ManageRegestration.fxml"));
        Pane cmdPane = (Pane) loader.load();
        rootPane.setCenter(cmdPane);
    }

    //------------------------------BLUR Effict-----------------------------------------------------------------------------------------------
    /*public void enableBlur() {
    ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);
    GaussianBlur blur = new GaussianBlur(10); //
    adj.setInput(blur);
    apnMain.setEffect(adj);
    }*/
    //-----------------------------------------------------------------------------------------------------------------------------------------  
    @FXML
    private void btnManagePaymentAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/lc/view/ManagePayments.fxml"));
        Pane cmdPane = (Pane) loader.load();
        rootPane.setCenter(cmdPane);
    }

    @FXML
    private void btnManageBatchAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/lc/view/ManageBatch.fxml"));
        Pane cmdPane = (Pane) loader.load();
        rootPane.setCenter(cmdPane);
    }

    private void manageStudent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/lc/view/ManageStudent.fxml"));
            Pane cmdPane = (Pane) loader.load();
            rootPane.setCenter(cmdPane);
        } catch (IOException ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
