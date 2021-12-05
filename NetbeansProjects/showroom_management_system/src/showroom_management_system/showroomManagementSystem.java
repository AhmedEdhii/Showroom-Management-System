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

    /**
     * @return
     */
    static int i = 0;

    public Connection getConnection() {

//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orclpdb", "hr", "hr");
//            if (conn == null) {
//                System.out.println("Unable to connect with database");
//                return null;
//            } else {
//                if (i == 0) {
//                    //System.out.println(i);
//                    System.out.println("Connected to database");
//                    i++;
//                    //System.out.println(i);
//                }
//            }
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(showroomManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
//        }
        String url = "jdbc:mysql://localhost:3306/DbProject";
        String username = "root";
        //String password = "IBA@2244";
        String password ="";

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
        new mainPage().setVisible(true);
    }

}
