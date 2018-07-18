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
import lk.ijse.lc.core.dto.RegestrationDTO;
import lk.ijse.lc.dao.DAOFactory;
import lk.ijse.lc.dao.custom.RegestrationDAO;
import lk.ijse.lc.view.util.Notifi;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class ManageRegestrationController implements Initializable {

    ObservableList<RegestrationDTO> list = FXCollections.observableArrayList();
    private static RegestrationDAO regestrationDAO = (RegestrationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.REGESTRATION);

    @FXML
    private TableColumn<RegestrationDTO, String> colRid;
    @FXML
    private TableColumn<RegestrationDTO, String> colSid;
    @FXML
    private TableColumn<RegestrationDTO, String> ColBid;
    @FXML
    private TableColumn<RegestrationDTO, String> colFee;
    @FXML
    private TableColumn<RegestrationDTO, String> colDate;
    @FXML
    private TableView<RegestrationDTO> tblRegestration;
    @FXML
    private JFXTextField txtSid;
    @FXML
    private JFXTextField txtRid;
    @FXML
    private JFXTextField txtBid;
    @FXML
    private JFXTextField txtFee;
    @FXML
    private JFXTextField txtDate;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDelete;
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
        if (txtRid.getText().matches("^R[0-9]{1,9}$")) {
            if (txtSid.getText().matches("^S[0-9]{1,9}$")) {
                if (txtBid.getText().matches("^B[0-9]{1,9}$")) {
                    if (txtFee.getText().matches("^[0-9]{1,}[.][0-9]{2}$")) {
                        if (txtDate.getText().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
                            addData();
                        } else {
                            Notifi n1 = new Notifi("Invalid Input", "Check Date", "asd");
                        }
                    } else {
                        Notifi n1 = new Notifi("Invalid Input", "Check Fee", "asd");
                    }
                } else {
                    Notifi n1 = new Notifi("Invalid Input", "Check Batch ID", "asd");
                }
            } else {
                Notifi n1 = new Notifi("Invalid Input", "Check Student ID", "asd");
            }
        } else {
            Notifi n1 = new Notifi("Invalid Input", "Check Regestration ID", "asd");
        }
    }

    @FXML
    private void btnUpdateAction(ActionEvent event) {
        if (txtRid.getText().matches("^R[0-9]{1,9}$")) {
            if (txtSid.getText().matches("^S[0-9]{1,9}$")) {
                if (txtBid.getText().matches("^B[0-9]{1,9}$")) {
                    if (txtFee.getText().matches("^[0-9]{1,}[.][0-9]{2}$")) {
                        if (txtDate.getText().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
                            updateData();
                        } else {
                            Notifi n1 = new Notifi("Invalid Input", "Check Date", "asd");
                        }
                    } else {
                        Notifi n1 = new Notifi("Invalid Input", "Check Fee", "asd");
                    }
                } else {
                    Notifi n1 = new Notifi("Invalid Input", "Check Batch ID", "asd");
                }
            } else {
                Notifi n1 = new Notifi("Invalid Input", "Check Student ID", "asd");
            }
        } else {
            Notifi n1 = new Notifi("Invalid Input", "Check Regestration ID", "asd");
        }
    }

    @FXML
    private void btnDeleteAction(ActionEvent event) {
        list.clear();
        try {
            RegestrationDTO regestrationDTO = new RegestrationDTO(txtRid.getText(), txtSid.getText(), txtBid.getText(), new BigDecimal(txtFee.getText()), txtDate.getText());
            boolean result = regestrationDAO.delete(regestrationDTO);
            if (result) {
                Notifi n1 = new Notifi("Delete Sucess", "Sucessfully delete row");
            } else {

            }
        } catch (Exception ex) {
            Logger.getLogger(ManageRegestrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }

    private void initCol() {
        colRid.setCellValueFactory(new PropertyValueFactory<>("rid"));
        colSid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        ColBid.setCellValueFactory(new PropertyValueFactory<>("bid"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void loadTable() {
        list.clear();
        try {
            ArrayList<RegestrationDTO> all = regestrationDAO.getAll();
            for (RegestrationDTO regestrationDTO : all) {
                list.add(regestrationDTO);
            }
            tblRegestration.getItems().setAll(list);
        } catch (Exception ex) {
            Logger.getLogger(ManageCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void tblGetItem() {
        tblRegestration.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    txtRid.setText(tblRegestration.getSelectionModel().getSelectedItem().getRid());
                    txtSid.setText(tblRegestration.getSelectionModel().getSelectedItem().getSid());
                    txtBid.setText(tblRegestration.getSelectionModel().getSelectedItem().getBid());
                    txtFee.setText(tblRegestration.getSelectionModel().getSelectedItem().getFee().toString());
                    txtDate.setText(tblRegestration.getSelectionModel().getSelectedItem().getDate());
                } catch (NullPointerException e) {

                }
            }
        });
    }

    private void addData() {
        list.clear();
        try {
            RegestrationDTO regestrationDTO = new RegestrationDTO(txtRid.getText(), txtSid.getText(), txtBid.getText(), new BigDecimal(txtFee.getText()), txtDate.getText());
            boolean result = regestrationDAO.add(regestrationDTO);
            if (result) {
                Notifi n1 = new Notifi("Added Sucess", "Sucessfully added new regestration");
            } else {

            }
        } catch (Exception ex) {
            Notifi n1 = new Notifi("Invalid Input", "Check Date", "asd");

            Logger.getLogger(ManageRegestrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();

    }

    private void updateData() {
        list.clear();
        try {
            RegestrationDTO regestrationDTO = new RegestrationDTO(txtRid.getText(), txtSid.getText(), txtBid.getText(), new BigDecimal(txtFee.getText()), txtDate.getText());
            boolean result = regestrationDAO.update(regestrationDTO);
            if (result) {
                Notifi n1 = new Notifi("Update Sucess", "Sucessfully update row");
            } else {

            }
        } catch (Exception ex) {
            Notifi n1 = new Notifi("Invalid Input", "Check Date", "asd");
            Logger.getLogger(ManageRegestrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();

    }

    @FXML
    private void btnClearAction(ActionEvent event) {
        txtRid.clear();
        txtSid.clear();
        txtBid.clear();
        txtFee.clear();

    }
}
