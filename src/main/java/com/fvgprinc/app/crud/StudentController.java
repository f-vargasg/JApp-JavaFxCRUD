/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fvgprinc.app.crud;

import com.fvgprinc.app.crud.data.AppQuery;
import com.fvgprinc.app.crud.model.Student;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author garfi
 */
public class StudentController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showStudents();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    public TextField fieldFirstName;

    @FXML
    public TextField fieldMiddleName;

    @FXML
    public TextField fieldLastName;

    @FXML
    public Button btnNew;

    @FXML
    public Button btnSave;

    @FXML
    public Button btnUpdate;

    @FXML
    public Button btnDelete;

    @FXML
    public TableView<Student> tableView;

    @FXML
    public TableColumn<Student, Integer> colId;

    @FXML
    public TableColumn<Student, String> colFirstName;

    @FXML
    public TableColumn<Student, String> colMiddleName;

    @FXML
    public TableColumn<Student, String> colLastName;

    private Student student;

    @FXML
    private void addStudent() {
        Student student = new Student(fieldFirstName.getText(), fieldMiddleName.getText(), fieldLastName.getText());
        AppQuery query = new AppQuery();
        query.addStudent(student);
    }

    @FXML
    private void showStudents() {
        AppQuery query = new AppQuery();
        ObservableList<Student> list = query.getStudentList();
        colId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        colMiddleName.setCellValueFactory(new PropertyValueFactory<Student, String>("middleName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        tableView.setItems(list);
    }

    @FXML
    private void mouseClicked(MouseEvent e) {
        try {
            Student student = tableView.getSelectionModel().getSelectedItem();
            student = new Student(student.getId(), student.getFirstName(), student.getMiddleName(),
                    student.getLastName());
            this.student = student;
            fieldFirstName.setText(student.getFirstName());
            fieldMiddleName.setText(student.getMiddleName());
            fieldLastName.setText(student.getLastName());
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            btnSave.setDisable(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void updateStudent() {
        try {
            AppQuery query = new AppQuery();
            Student student = new Student(this.student.getId(), fieldFirstName.getText(),
                    fieldMiddleName.getText(), fieldLastName.getText());
            query.updateStudent(student);
            showStudents();
            clearFields();
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteStudent() {
        try {
            AppQuery query = new AppQuery();
            Student student = new Student(this.student.getId(), fieldFirstName.getText(),
                    fieldMiddleName.getText(), fieldLastName.getText());
            query.deleteStudent(student);
            showStudents();
            clearFields();
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        fieldFirstName.setText("");
        fieldMiddleName.setText("");
        fieldLastName.setText("");
    }

    @FXML
    private void clickNew() {
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        clearFields();
        btnSave.setDisable(false);
    }

}
