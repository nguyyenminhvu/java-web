/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class DBContext {

    protected Connection connection;
    String instance = "";
    String serverName = "localhost";
    String portNumber = "1433";
    String dbName = "CartDB";
    String userID = "sa";
    String password = "12345";
    String f1 = ";encrypt=false ";

    public DBContext() {
        try {
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
            if (instance == null || instance.trim().isEmpty()) {
                url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                } catch (Exception e) {
                    System.out.println(e);
                }
                connection = DriverManager.getConnection(url, userID, password);
            }
        } catch (SQLException e) {
            System.out.println("Error at DBContext: "+e);
        }

    }

}
