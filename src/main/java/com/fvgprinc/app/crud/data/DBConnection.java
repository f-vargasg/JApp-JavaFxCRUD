/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fvgprinc.app.crud.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author garfi
 */
public class DBConnection {
    private static Connection con;

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        DBConnection.con = con;
    }
    
    
    public void getDBConn() {
        synchronized ("") {
            try {
                if (this.getCon() == null  || this.getCon().isClosed()) {
                    try {
                        String url = "jdbc:mariadb://10.25.1.86:3306/TESTDB";
                        Class.forName("org.mariadb.jdbc.Driver");
                        setCon(DriverManager.getConnection(url, "root", "valerie5250"));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void closeConnection() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
