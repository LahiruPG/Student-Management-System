/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.lc.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import lk.ijse.lc.core.dto.GurdianDTO;
import lk.ijse.lc.dao.DAOFactory;
import lk.ijse.lc.dao.custom.GurdianDAO;
import lk.ijse.lc.view.util.Notifi;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class ManageGuardianController implements Initializable {

    private static GurdianDAO gurdianDAO = (GurdianDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.GURDIAN);
    ObservableList<GurdianDTO> list = FXCollections.observableArrayList();

    @FXML
    private JFXComboBox<GurdianDTO> comboBoxSid;
    @FXML
    private TableView<GurdianDTO> tblGurdian;
    @FXML
    private TableColumn<GurdianDTO, String> colGid;
    @FXML
    private TableColumn<GurdianDTO, String> ColSid;
    @FXML
    private TableColumn<GurdianDTO, String> colName;
    @FXML
    private TableColumn<GurdianDTO, String> ColContact;
    @FXML
    private TableColumn<GurdianDTO, String> colAddress;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXTextField txtGid;
    @FXML
    private JFXTextField txtSid;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextArea txtAddress;
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
        //  loadToCmb();
        // TODO
        initCol();
        loadTable();
        tblGetItem();
    }

    @FXML
    private void btnAddAction(ActionEvent event) {
        if (txtGid.getText().matches("^G[0-9]{1,9}$")) {
            if (txtSid.getText().matches("^S[0-9]{1,9}$")) {
                if (txtName.getText().matches("^([A-Z a-z0-9.]){1,95}")) {
                    if (txtContact.getText().matches("^[0-9]{3}-[0-9]{7}$")) {
                        if (txtAddress.getText().matches("^([A-Z a-z0-9.,\\n]){1,95}.$")) {
                            addData();
                        } else {
                            Notifi n1 = new Notifi("Invalid Address", "Check Address", "asd");
                        }
                    } else {
                        Notifi n1 = new Notifi("Invalid Input", "Check Contact", "asd");
                    }
                } else {
                    Notifi n1 = new Notifi("Invalid Input", "Check name", "asd");
                }

            } else {
                Notifi n1 = new Notifi("Invalid Input", "Check Student ID", "asd");
            }

        } else {

            Notifi n1 = new Notifi("Invalid Input", "Check Guridian ID", "asd");
        }
    }

    @FXML
    private void btnUpdateAction(ActionEvent event) {
        if (txtGid.getText().matches("^G[0-9]{1,9}$")) {
            if (txtSid.getText().matches("^S[0-9]{1,9}$")) {
                if (txtName.getText().matches("^([A-Z a-z0-9.]){1,95}")) {
                    if (txtContact.getText().matches("^[0-9]{3}-[0-9]{7}$")) {
                        if (txtAddress.getText().matches("^([A-Z a-z0-9.,\\n]){1,95}.$")) {
                            updateData();
                        } else {
                            Notifi n1 = new Notifi("Invalid Address", "Check Address", "asd");
                        }
                    } else {
                        Notifi n1 = new Notifi("Invalid Input", "Check Contact", "asd");
                    }
                } else {
                    Notifi n1 = new Notifi("Invalid Input", "Check name", "asd");
                }

            } else {
                Notifi n1 = new Notifi("Invalid Input", "Check Student ID", "asd");
            }

        } else {

            Notifi n1 = new Notifi("Invalid Input", "Check Gurdian ID", "asd");
        }

    }

    @FXML
    private void btnDeleteAction(ActionEvent event) {
        list.clear();
        try {
            GurdianDTO gurdian = new GurdianDTO(txtGid.getText(), txtSid.getText(), txtName.getText(), txtContact.getText(), txtAddress.getText());
            boolean result = gurdianDAO.delete(gurdian);
            if (result) {
                Notifi n1 = new Notifi("Delete Sucess", "Sucessfully delete a row");
            } else {
                System.out.println("0");
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageGuardianController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }

    private void loadToCmb() {
        try {
            ArrayList<GurdianDTO> allGudian = gurdianDAO.getAll();
            //comboBoxSid.getItems().removeAll(comboBoxSid.getItems());
            comboBoxSid.getItems().clear();
            if (allGudian == null) {
                return;
            }

            for (GurdianDTO item : allGudian) {
                list.add(item);
            }
            comboBoxSid.setItems(list);
        } catch (Exception ex) {
            Logger.getLogger(ManageGuardianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //--------------table-----------------------------
    private void initCol() {
        colGid.setCellValueFactory(new PropertyValueFactory<>("gid"));
        ColSid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void loadTable() {

        try {
            ArrayList<GurdianDTO> all = gurdianDAO.getAll();
            for (GurdianDTO gurdianDTO : all) {
                list.add(gurdianDTO);
            }
            tblGurdian.getItems().setAll(list);
        } catch (Exception ex) {
            Logger.getLogger(ManageCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //--------------------------------------------------------------

    private void tblGetItem() {
        tblGurdian.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                txtGid.setText(tblGurdian.getSelectionModel().getSelectedItem().getGid());
                txtSid.setText(tblGurdian.getSelectionModel().getSelectedItem().getSid());
                txtName.setText(tblGurdian.getSelectionModel().getSelectedItem().getName());
                txtContact.setText(tblGurdian.getSelectionModel().getSelectedItem().getContact());
                txtAddress.setText(tblGurdian.getSelectionModel().getSelectedItem().getAddress());
            }
        });
    }

    private void addData() {
        list.clear();
        try {
            GurdianDTO gurdian = new GurdianDTO(txtGid.getText(), txtSid.getText(), txtName.getText(), txtContact.getText(), txtAddress.getText());
            boolean result = gurdianDAO.add(gurdian);
            if (result) {
                Notifi n1 = new Notifi("Added Sucess", "Sucessfully added a new Gurdian");
            } else {
                System.out.println("0");
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageGuardianController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }

    private void updateData() {
        list.clear();
        try {
            GurdianDTO gurdian = new GurdianDTO(txtGid.getText(), txtSid.getText(), txtName.getText(), txtContact.getText(), txtAddress.getText());
            boolean result = gurdianDAO.update(gurdian);
            if (result) {
                Notifi n1 = new Notifi("Update Sucess", "Sucessfully update a row");
            } else {
                System.out.println("0");
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageGuardianController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }

    @FXML
    private void btnClearAction(ActionEvent event) {
        txtGid.clear();
        txtSid.clear();
        txtName.clear();
        txtContact.clear();
        txtAddress.clear();

    }

}
