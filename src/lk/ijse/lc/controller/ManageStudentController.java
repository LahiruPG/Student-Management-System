/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.lc.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lk.ijse.lc.core.dto.StudentDTO;
import lk.ijse.lc.dao.DAOFactory;
import lk.ijse.lc.dao.custom.StudentDAO;
import lk.ijse.lc.view.util.Notifi;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class ManageStudentController implements Initializable {

    private static StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    ObservableList<StudentDTO> studentList = FXCollections.observableArrayList();

    private String gender = "";

    @FXML
    private AnchorPane apnManageStudent;
    @FXML
    private AnchorPane apnStuddentReg;
    @FXML
    private JFXTextField txtSid;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXRadioButton rdbMale;
    @FXML
    private JFXTextArea txtAddress;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtDob;
    @FXML
    private JFXTextField txtNic;
    @FXML
    private JFXButton btnSDelete;
    @FXML
    private JFXTextField txtSerch;
    @FXML
    private JFXRadioButton rdbFemale;
    @FXML
    private JFXButton btnSAdd;
    @FXML
    private TableView<StudentDTO> tblStudent;
    @FXML
    private TableColumn<StudentDTO, String> colSid;
    @FXML
    private TableColumn<StudentDTO, String> colSName;
    @FXML
    private TableColumn<StudentDTO, String> colSGender;
    @FXML
    private TableColumn<StudentDTO, String> colSAddress;
    @FXML
    private TableColumn<StudentDTO, String> colSContact;
    @FXML
    private TableColumn<StudentDTO, String> colSDob;
    @FXML
    private TableColumn<StudentDTO, String> colSNic;
    @FXML
    private Tab tabGuardian;
    @FXML
    private AnchorPane apNGuardian;
    @FXML
    private BorderPane bpnGuardian;
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
    private void rdbMaleAction(ActionEvent event) {
        rdbFemale.setSelected(false);
        gender = "Male";
    }

    @FXML
    private void rdbFemaleAction(ActionEvent event) {
        rdbMale.setSelected(false);
        gender = "Female";
    }

    @FXML
    private void btnSAddAction(ActionEvent event) {
        if (txtSid.getText().matches("^S[0-9]{1,9}$")) {
            if (txtName.getText().matches("^([A-Z a-z0-9.]){1,95}")) {
                if (rdbFemale.isSelected() || rdbMale.isSelected()) {

                    if (txtAddress.getText().matches("^([A-Z a-z0-9.,\\n]){1,95}.$")) {
                        if (txtContact.getText().matches("^[0-9]{3}-[0-9]{7}$")) {
                            if (txtDob.getText().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
                                if (txtNic.getText().matches("^([A-Z a-z0-9.]){1,95}")) {
                                    addData();
                                } else {
                                    Notifi n1 = new Notifi("Invalid Input", "Check NIC", "asd");
                                }
                            } else {
                                Notifi n1 = new Notifi("Invalid Input", "Check DOB", "asd");
                            }
                        } else {
                            Notifi n1 = new Notifi("Invalid Input", "Check Contact", "asd");
                        }
                    } else {
                        Notifi n1 = new Notifi("Invalid Input", "Check Address", "asd");
                    }
                } else {
                    Notifi n1 = new Notifi("Invalid Input", "Select Gender", "asd");
                }
            } else {
                Notifi n1 = new Notifi("Invalid Input", "Check Name", "asd");
            }

        } else {

            Notifi n1 = new Notifi("Invalid Input", "Check Student ID", "asd");
        }
    }

    @FXML
    private void btnUpdateAction(ActionEvent event) {
        if (txtSid.getText().matches("^S[0-9]{1,9}$")) {
            if (txtName.getText().matches("^([A-Z a-z0-9.]){1,95}")) {
                if (rdbFemale.isSelected() || rdbMale.isSelected()) {

                    if (txtAddress.getText().matches("^([A-Z a-z0-9.,\\n]){1,95}.$")) {
                        if (txtContact.getText().matches("^[0-9]{3}-[0-9]{7}$")) {
                            if (txtDob.getText().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
                                if (txtNic.getText().matches("[0-9]{9}V")) {
                                    updateData();
                                } else {
                                    Notifi n1 = new Notifi("Invalid Input", "Check NIC", "asd");
                                }
                            } else {
                                Notifi n1 = new Notifi("Invalid Input", "Check DOB", "asd");
                            }
                        } else {
                            Notifi n1 = new Notifi("Invalid Input", "Check Contact", "asd");
                        }
                    } else {
                        Notifi n1 = new Notifi("Invalid Input", "Check Address", "asd");
                    }
                } else {
                    Notifi n1 = new Notifi("Invalid Input", "Select Gender", "asd");
                }
            } else {
                Notifi n1 = new Notifi("Invalid Input", "Check Name", "asd");
            }

        } else {

            Notifi n1 = new Notifi("Invalid Input", "Check Student ID", "asd");
        }

    }

    @FXML
    private void btnSDeleteAction(ActionEvent event) {
        studentList.clear();
        try {
            StudentDTO student = new StudentDTO(txtSid.getText(), txtName.getText(), gender, txtAddress.getText(), txtContact.getText(), txtDob.getText(), txtNic.getText());
            boolean result = studentDAO.delete(student);
            if (result) {
                Notifi n1 = new Notifi("Delete Sucess", "Sucessfully delete a Student");
            } else {
                System.out.println("0");
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }

    //------------Table-----------------------
    private void initCol() {
        colSid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colSAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
    }

    private void loadTable() {

        try {
            ArrayList<StudentDTO> all = studentDAO.getAll();
            for (StudentDTO studentDTO : all) {
                studentList.add(studentDTO);
            }
            tblStudent.getItems().setAll(studentList);
        } catch (Exception ex) {
            Logger.getLogger(ManageStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //--------------------------------------------

    @FXML
    private void tabGuardianAction(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/lc/view/ManageGuardian.fxml"));
            Pane cmdPane = (Pane) loader.load();
            bpnGuardian.setCenter(cmdPane);
        } catch (IOException ex) {
            Logger.getLogger(ManageStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void tblGetItem() {
        tblStudent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String gen = "";

                //boolean male=
                txtSid.setText(tblStudent.getSelectionModel().getSelectedItem().getSid());
                txtName.setText(tblStudent.getSelectionModel().getSelectedItem().getName());
                gen = tblStudent.getSelectionModel().getSelectedItem().getGender();
                txtAddress.setText(tblStudent.getSelectionModel().getSelectedItem().getAddress());
                txtContact.setText(tblStudent.getSelectionModel().getSelectedItem().getContact());
                txtDob.setText(tblStudent.getSelectionModel().getSelectedItem().getDob());
                txtNic.setText(tblStudent.getSelectionModel().getSelectedItem().getNic());

                if (gen.matches("Male")) {
                    rdbMale.setSelected(true);
                    rdbFemale.setSelected(false);
                    gender = "Male";
                } else {

                    rdbMale.setSelected(false);
                    rdbFemale.setSelected(true);
                    gender = "Female";
                }

                //rdbMale.setSelected(true);
            }
        });
    }

    private void addData() {
        studentList.clear();
        try {
            StudentDTO student = new StudentDTO(txtSid.getText(), txtName.getText(), gender, txtAddress.getText(), txtContact.getText(), txtDob.getText(), txtNic.getText());
            boolean result = studentDAO.add(student);
            if (result) {
                Notifi n1 = new Notifi("Added Sucess", "Sucessfully added new Student");
            } else {
                System.out.println("0");
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }

    private void updateData() {
        studentList.clear();
        try {
            StudentDTO student = new StudentDTO(txtSid.getText(), txtName.getText(), gender, txtAddress.getText(), txtContact.getText(), txtDob.getText(), txtNic.getText());
            boolean result = studentDAO.update(student);
            if (result) {
                Notifi n1 = new Notifi("Update Sucess", "Sucessfully update a student");
            } else {
                System.out.println("0");
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }

    @FXML
    private void btnClearAction(ActionEvent event) {
        txtSid.clear();
        txtName.clear();
        rdbFemale.setSelected(false);
        rdbMale.setSelected(false);
        txtAddress.clear();
        txtContact.clear();
        txtDob.clear();
        txtNic.clear();
    }
}
