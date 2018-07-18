/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.lc.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.lc.core.dto.BatchDTO;
import lk.ijse.lc.dao.DAOFactory;
import lk.ijse.lc.dao.custom.BatchDAO;
import lk.ijse.lc.view.util.Notifi;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class ManageBatchController implements Initializable {

    private static BatchDAO batchDAO = (BatchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BATCH);
    ObservableList<BatchDTO> list = FXCollections.observableArrayList();

    @FXML
    private JFXTextField txtBid;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtCid;
    @FXML
    private TableView<BatchDTO> tblBatch;
    @FXML
    private TableColumn<BatchDTO, String> colBid;
    @FXML
    private TableColumn<BatchDTO, String> colName;
    @FXML
    private TableColumn<BatchDTO, String> colCid;
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
        initCol();
        loadTable();
        tblGetItem();
        // TODO
    }

    @FXML
    private void btnAddAction(ActionEvent event) {
        if (txtBid.getText().matches("^B[0-9]{1,9}$")) {
            if (txtName.getText().matches("^([A-Z a-z0-9.]){1,95}")) {
                if (txtCid.getText().matches("^C[0-9]{1,9}$")) {

                    addData();

                } else {
                    Notifi n1 = new Notifi("Invalid Input", "Check Cid", "asd");
                }

            } else {
                Notifi n1 = new Notifi("Invalid Input", "Check Batch Name", "asd");
            }

        } else {

            Notifi n1 = new Notifi("Invalid Input", "Check batch ID", "asd");
        }
    }

    @FXML
    private void btnUpdateAction(ActionEvent event) {
        if (txtBid.getText().matches("^B[0-9]{1,9}$")) {
            if (txtName.getText().matches("^([A-Z a-z0-9.]){1,95}")) {
                if (txtCid.getText().matches("^C[0-9]{1,9}$")) {

                    updateData();

                } else {
                    Notifi n1 = new Notifi("Invalid Input", "Check Cid", "asd");
                }

            } else {
                Notifi n1 = new Notifi("Invalid Input", "Check Batch Name", "asd");
            }

        } else {

            Notifi n1 = new Notifi("Invalid Input", "Check batch ID", "asd");
        }

    }

    private void initCol() {
        colBid.setCellValueFactory(new PropertyValueFactory<>("bid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCid.setCellValueFactory(new PropertyValueFactory<>("cid"));
    }

    private void loadTable() {
        try {
            ArrayList<BatchDTO> all = batchDAO.getAll();
            for (BatchDTO batchDTO : all) {
                list.add(batchDTO);
                // list.add(new BatchDTO("C001","English","6 Month"));
            }
            tblBatch.getItems().setAll(list);
        } catch (Exception ex) {
            Logger.getLogger(ManageBatchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void tblGetItem() {
        tblBatch.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                txtBid.setText(tblBatch.getSelectionModel().getSelectedItem().getBid());
                txtName.setText(tblBatch.getSelectionModel().getSelectedItem().getName());
                txtCid.setText(tblBatch.getSelectionModel().getSelectedItem().getCid());
            }
        });
    }

    @FXML
    private void btnDeleteAction(ActionEvent event) {
        list.clear();
        try {
            BatchDTO batch = new BatchDTO(txtBid.getText(), txtName.getText(), txtCid.getText());
            boolean result = batchDAO.delete(batch);
            if (result) {
                Notifi n1 = new Notifi("Delete Sucess", "Sucessfully delete data");
            } else {

            }
        } catch (Exception ex) {
            Notifi n1 = new Notifi("Duplicate Input", "Check Batch ID","asd");
            Logger.getLogger(ManageBatchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }

    private void addData() {
        try {
            list.clear();
            BatchDTO batch = new BatchDTO(txtBid.getText(), txtName.getText(), txtCid.getText());
            boolean result = batchDAO.add(batch);
            if (result) {
                Notifi n1 = new Notifi("Added Sucess", "Sucessfully added new batch");
            } else {
                System.out.println("0");
            }
            loadTable();
        } catch (Exception ex) {
            Notifi n1 = new Notifi("Duplicate Input", "Check Batch ID","asd");
            Logger.getLogger(ManageBatchController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void updateData() {
        list.clear();
        try {
            BatchDTO batch = new BatchDTO(txtBid.getText(), txtName.getText(), txtCid.getText());
            boolean result = batchDAO.update(batch);
            if (result) {
                Notifi n1 = new Notifi("Update Sucess", "Sucessfully update data");
            } else {

            }
        } catch (Exception ex) {
            Logger.getLogger(ManageBatchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }

    @FXML
    private void btnClearAction(ActionEvent event) {
        txtBid.clear();
        txtName.clear();
        txtCid.clear();
    }

}
