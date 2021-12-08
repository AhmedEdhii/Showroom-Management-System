/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showroom_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ahmed
 */
public class showroomManagementSystem {

    Connection conn;

    static int i = 0;

    public Connection getConnection() {

        String url = "jdbc:mysql://localhost:3306/DbProject";
        String username = "root";
        //String password = "IBA@2244";
        String password = "";
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
        } catch (SQLException ex) {
            System.out.println("Oops");
            ex.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        showroomManagementSystem con = new showroomManagementSystem();
        con.getConnection();
    }

}
