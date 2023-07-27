/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fvgprinc.app.crud.data;

import com.fvgprinc.app.crud.model.Student;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javax.sql.StatementEvent;

/**
 *
 * @author garfi
 */
public class AppQuery {
    private DBConnection  c= new DBConnection();
    
    public void addStudent(Student student) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("insert into student  (firstname, middlename, lastname) values (?,?,?)");
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getMiddleName());
            ps.setString(3, student.getLastName());
            ps.execute();
            ps.close();
            c.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ObservableList<Student> getStudentList() {
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        
        try {
            String query = "Select id, firstname, middlename, lastname from student order by lastname asc";
            c.getDBConn();
            Statement st = c.getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            Student s;
            while (rs.next()) {                
                s = new Student(rs.getInt("id"), rs.getString("firstname"), 
                             rs.getString("middlename"), rs.getString("lastname"));
                studentList.add(s);
            }
            rs.close();
            st.close();
            c.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }
}