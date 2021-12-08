/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showroom_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String password = "";
        //String password = "IBA@2244";
        try {
            conn = DriverManager.getConnection(url, username, password);
            if (i == 0) {
                System.out.println("Database Connected");
                i++;
            }
        } catch (SQLException ex) {
            System.out.println("Database not connected");
            Logger.getLogger(showroomManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        showroomManagementSystem con = new showroomManagementSystem();
        con.getConnection();
    }

}
