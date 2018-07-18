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
import lk.ijse.lc.core.dto.PaymentDTO;
import lk.ijse.lc.dao.DAOFactory;
import lk.ijse.lc.dao.custom.CourseDAO;
import lk.ijse.lc.dao.custom.PaymentDAO;
import lk.ijse.lc.view.util.Notifi;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class ManagePaymentsController implements Initializable {

    ObservableList<PaymentDTO> list = FXCollections.observableArrayList();
    private static PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @FXML
    private JFXTextField txtPid;
    @FXML
    private JFXTextField txtRid;
    @FXML
    private JFXTextField txtCid;
    @FXML
    private JFXTextField txtAmount;
    @FXML
    private JFXTextField txtDate;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<PaymentDTO> tblPayments;
    @FXML
    private TableColumn<PaymentDTO, String> colPid;
    @FXML
    private TableColumn<PaymentDTO, String> colRid;
    @FXML
    private TableColumn<PaymentDTO, String> colCid;
    @FXML
    private TableColumn<PaymentDTO, String> colAmount;
    @FXML
    private TableColumn<PaymentDTO, String> colDate;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnClear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initCol();
        loadTable();
        tblGetItem();
    }

    @FXML
    private void btnAddAction(ActionEvent event) {
        if (txtPid.getText().matches("^P[0-9]{1,9}$")) {
            if (txtRid.getText().matches("^R[0-9]{1,9}$")) {
                if (txtCid.getText().matches("^C[0-9]{1,9}$")) {
                    if (txtAmount.getText().matches("^[0-9]{1,}[.][0-9]{2}$")) {
                        if (txtDate.getText().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
                            addData();
                        } else {
                            Notifi n1 = new Notifi("Invalid input", "Check date", "asd");
                        }
                    } else {
                        Notifi n1 = new Notifi("Invalid Input", "Check Amount", "asd");
                    }
                } else {
                    Notifi n1 = new Notifi("Invalid Input", "Check Course ID", "asd");
                }

            } else {
                Notifi n1 = new Notifi("Invalid Input", "Check Regestration ID", "asd");
            }

        } else {

            Notifi n1 = new Notifi("Invalid Input", "Check Payment ID", "asd");
        }
    }

    @FXML
    private void btnUpdateAction(ActionEvent event) {
        if (txtPid.getText().matches("^P[0-9]{1,9}$")) {
            if (txtRid.getText().matches("^R[0-9]{1,9}$")) {
                if (txtCid.getText().matches("^C[0-9]{1,9}$")) {
                    if (txtAmount.getText().matches("^[0-9]{1,}[.][0-9]{2}$")) {
                        if (txtDate.getText().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
                            updateData();
                        } else {
                            Notifi n1 = new Notifi("Invalid input", "Check date", "asd");
                        }
                    } else {
                        Notifi n1 = new Notifi("Invalid Input", "Check Amount", "asd");
                    }
                } else {
                    Notifi n1 = new Notifi("Invalid Input", "Check Course ID", "asd");
                }

            } else {
                Notifi n1 = new Notifi("Invalid Input", "Check Regestration ID", "asd");
            }

        } else {

            Notifi n1 = new Notifi("Invalid Input", "Check Payment ID", "asd");
        }
    }

    @FXML
    private void btnDeleteAction(ActionEvent event) {
        list.clear();
        try {
            PaymentDTO payment = new PaymentDTO(txtPid.getText(), txtRid.getText(), txtCid.getText(), new BigDecimal(txtAmount.getText()), txtDate.getText());
            boolean result = paymentDAO.delete(payment);
            if (result) {
                Notifi n1 = new Notifi("Delete Sucess", "Sucessfully delete a payment");
            } else {
                System.out.println("0");
            }
        } catch (Exception ex) {

            Logger.getLogger(ManagePaymentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }

    //------------Table--------------------------
    private void initCol() {
        colPid.setCellValueFactory(new PropertyValueFactory<>("pid"));
        colRid.setCellValueFactory(new PropertyValueFactory<>("rid"));
        colCid.setCellValueFactory(new PropertyValueFactory<>("cid"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void loadTable() {
        try {
            ArrayList<PaymentDTO> all = paymentDAO.getAll();
            for (PaymentDTO paymentDTO : all) {
                list.add(paymentDTO);
            }
            tblPayments.getItems().setAll(list);
        } catch (Exception ex) {
            Logger.getLogger(ManageCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //------------------------------------------------
    private void tblGetItem() {
        tblPayments.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                txtPid.setText(tblPayments.getSelectionModel().getSelectedItem().getPid());
                txtRid.setText(tblPayments.getSelectionModel().getSelectedItem().getRid());
                txtCid.setText(tblPayments.getSelectionModel().getSelectedItem().getCid());
                txtAmount.setText(tblPayments.getSelectionModel().getSelectedItem().getAmount().toString());
                txtDate.setText(tblPayments.getSelectionModel().getSelectedItem().getDate());
            }
        });
    }

    private void addData() {
        list.clear();
        try {

            PaymentDTO payment = new PaymentDTO(txtPid.getText(), txtRid.getText(), txtCid.getText(), new BigDecimal(txtAmount.getText()), txtDate.getText());
            boolean result = paymentDAO.add(payment);
            if (result) {
                Notifi n1 = new Notifi("Added Sucess", "Sucessfully added new payment");
            } else {
                System.out.println("0");
            }

        } catch (Exception ex) {
            Notifi n1 = new Notifi("Duplicate Input", "Check Payment ID", "asd");

            Logger.getLogger(ManagePaymentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }

    private void updateData() {
        list.clear();
        try {
            PaymentDTO payment = new PaymentDTO(txtPid.getText(), txtRid.getText(), txtCid.getText(), new BigDecimal(txtAmount.getText()), txtDate.getText());
            boolean result = paymentDAO.update(payment);
            if (result) {
                Notifi n1 = new Notifi("Update Sucess", "Sucessfully update a payment");
            } else {
                System.out.println("0");
            }
        } catch (Exception ex) {
            Notifi n1 = new Notifi("Duplicate Input", "Check Payment ID", "asd");

            Logger.getLogger(ManagePaymentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }

    @FXML
    private void btnClearAction(ActionEvent event) {
        txtPid.clear();
        txtRid.clear();
        txtCid.clear();
        txtAmount.clear();
        txtDate.clear();

    }
}
