/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.lc.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.lc.core.dto.CourseDTO;
import lk.ijse.lc.dao.DAOFactory;
import lk.ijse.lc.dao.custom.CourseDAO;
import lk.ijse.lc.view.util.Notifi;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class ManageCourseController implements Initializable {

    ObservableList<CourseDTO> list = FXCollections.observableArrayList();
    private static CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);

    @FXML
    private TableView<CourseDTO> tblCourse;
    @FXML
    private TableColumn<CourseDTO, String> colCid;
    @FXML
    private TableColumn<CourseDTO, String> colName;
    @FXML
    private TableColumn<CourseDTO, String> colFee;
    @FXML
    private TableColumn<CourseDTO, String> colDuration;

    /*public ManageCourseController() {
    }*/
    @FXML
    private JFXTextField txtCid;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtFee;
    @FXML
    private JFXTextField txtDuration;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnClear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initCol();
        loadTable();
        tblGetItem();
        // TODO

    }

    @FXML
    private void btnAddAction(ActionEvent event) throws Exception {
        if (txtCid.getText().matches("^C[0-9]{1,9}$")) {
            if (txtName.getText().matches("^([A-Z a-z.]){1,95}")) {
                if (txtFee.getText().matches("^[0-9]{1,}[.][0-9]{2}$")) {
                    if (txtDuration.getText().matches("^[0-9A-Za-z .]{3,}")) {
                        insertData();
                    } else {
                        txtDuration.requestFocus();
                        txtDuration.selectAll();
                        Notifi n1 = new Notifi("Invalid Input", "Check Duration", "sdf");
                    }

                } else {
                    txtFee.requestFocus();
                    txtFee.selectAll();
                    Notifi n1 = new Notifi("Invalid Input", "Check Fee", "saf");
                }

            } else {
                txtName.requestFocus();
                txtName.selectAll();
                Notifi n1 = new Notifi("Invalid Input", "Check Course Name", "sdf");
            }

        } else {
            txtCid.requestFocus();
            txtCid.selectAll();
            //txtCid.setStyle("-fx-background-color:#ff7d86;");
            Notifi n1 = new Notifi("Invalid Input", "Check Course ID", "sdf");
        }
    }

    private void initCol() {
        colCid.setCellValueFactory(new PropertyValueFactory<>("cid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("Fee"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("Duration"));
    }

    private void loadTable() {
        list.clear();
        try {
            ArrayList<CourseDTO> all = courseDAO.getAll();
            for (CourseDTO courseDTO : all) {
                list.add(courseDTO);
            }
            tblCourse.getItems().setAll(list);
        } catch (Exception ex) {
            Logger.getLogger(ManageCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnDeleteAction(ActionEvent event) {
        list.clear();
        try {
            CourseDTO course = new CourseDTO(txtCid.getText(), txtName.getText(), new BigDecimal(txtFee.getText()), txtDuration.getText());
            boolean result = courseDAO.delete(course);
            if (result) {
                Notifi n1 = new Notifi("Delete Sucess", "Sucessfully Delete data");
            } else {
                System.out.println("0");
            }

        } catch (Exception ex) {
            Logger.getLogger(ManageCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();

    }

    private void tblGetItem() {
        tblCourse.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    txtCid.setText(tblCourse.getSelectionModel().getSelectedItem().getCid());
                    txtName.setText(tblCourse.getSelectionModel().getSelectedItem().getName());
                    txtFee.setText(tblCourse.getSelectionModel().getSelectedItem().getFee().toString());
                    txtDuration.setText(tblCourse.getSelectionModel().getSelectedItem().getDuration());
                } catch (NullPointerException e) {

                }
            }
        });
    }

    @FXML
    private void btnUpdateAction(ActionEvent event) {

        if (txtCid.getText().matches("^C[0-9]{1,9}$")) {
            if (txtName.getText().matches("^([A-Z a-z.]){1,95}")) {
                if (txtFee.getText().matches("^[0-9]{1,}[.][0-9]{2}$")) {
                    if (txtDuration.getText().matches("^[0-9A-Za-z .]{3,}")) {
                        updateData();
                    } else {
                        Notifi n1 = new Notifi("Invalid Input", "Check Duration", "asd");
                    }

                } else {
                    Notifi n1 = new Notifi("Invalid Input", "Check Fee", "asd");
                }

            } else {
                Notifi n1 = new Notifi("Invalid Input", "Check Course Name", "asd");
            }

        } else {

            Notifi n1 = new Notifi("Invalid Input", "Check Course ID", "asd");
        }
    }

    private void insertData() {
        list.clear();
        try {

            CourseDTO course = new CourseDTO(txtCid.getText(), txtName.getText(), new BigDecimal(txtFee.getText()), txtDuration.getText());
            boolean result = courseDAO.add(course);
            if (result) {
                Notifi n1 = new Notifi("Added Sucess", "Sucessfully added new course");
            } else {
                System.out.println("0");
            }

        } catch (Exception ex) {
            Logger.getLogger(ManageCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }

    private void updateData() {
        list.clear();
        try {
            CourseDTO course = new CourseDTO(txtCid.getText(), txtName.getText(), new BigDecimal(txtFee.getText()), txtDuration.getText());
            boolean result = courseDAO.update(course);
            if (result) {
                Notifi n1 = new Notifi("Update Sucess", "Sucessfully update data");
            } else {
                System.out.println("0");
            }
        } catch (Exception ex) {
             Notifi n1 = new Notifi("Duplicate Input", "Check Course ID","asd");
            Logger.getLogger(ManageCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }

    @FXML
    private void btnClearAction(ActionEvent event) {
        txtCid.clear();
        txtDuration.clear();
        txtFee.clear();
        txtName.clear();
    }
}
