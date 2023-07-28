/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fvgprinc.app.crud;

import com.fvgprinc.app.crud.data.AppQuery;
import com.fvgprinc.app.crud.model.Student;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

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
        fieldSearch.textProperty().addListener((ObservableList, oldValue, newValue) ->{
            filterData(newValue);
        });
    }

    @FXML
    public TextField fieldFirstName;

    @FXML
    public TextField fieldMiddleName;

    @FXML
    public TextField fieldLastName;

    @FXML
    public TextField fieldSearch;

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
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add Confirmation");
        dialog.setHeaderText("Are you sure you want to save ?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Name: " + fieldFirstName.getText() + "  " + fieldLastName.getText());
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            Student student = new Student(fieldFirstName.getText(), fieldMiddleName.getText(), fieldLastName.getText());
            AppQuery query = new AppQuery();
            query.addStudent(student);
            showStudents();
        }

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
            Student student1 = tableView.getSelectionModel().getSelectedItem();
            student1 = new Student(student1.getId(), student1.getFirstName(), student1.getMiddleName(),
                    student1.getLastName());
            this.student = student1;
            fieldFirstName.setText(student1.getFirstName());
            fieldMiddleName.setText(student1.getMiddleName());
            fieldLastName.setText(student1.getLastName());
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
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Update Confirmation");
            dialog.setHeaderText("Are you sure you want to update ?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            Label label = new Label("Name: " + fieldFirstName.getText() + "  " + fieldLastName.getText());
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                AppQuery query = new AppQuery();
                Student student = new Student(this.student.getId(), fieldFirstName.getText(),
                        fieldMiddleName.getText(), fieldLastName.getText());
                query.updateStudent(student);
                showStudents();
                clearFields();
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteStudent() {
        try {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Delete Confirmation");
            dialog.setHeaderText("Are you sure you want to Delete ?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            Label label = new Label("Name: " + fieldFirstName.getText() + "  " + fieldLastName.getText());
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                AppQuery query = new AppQuery();
                Student student = new Student(this.student.getId(), fieldFirstName.getText(),
                        fieldMiddleName.getText(), fieldLastName.getText());
                query.deleteStudent(student);
                showStudents();
                clearFields();
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
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

    private void filterData(String searchName) {
        ObservableList<Student> filterData = FXCollections.observableArrayList();
        AppQuery query = new AppQuery();
        ObservableList<Student> list = query.getStudentList();
        for (Student student1 : list) {
            if (student1.getFirstName().toLowerCase().contains(searchName.toLowerCase())
                    || student1.getMiddleName().toLowerCase().contains(searchName.toLowerCase())
                    || student1.getLastName().toLowerCase().contains(searchName.toLowerCase())) {
                filterData.add(student1);
            }
        }
        tableView.setItems(filterData);
    }
}
