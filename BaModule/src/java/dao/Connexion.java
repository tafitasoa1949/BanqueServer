/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Tafitasoa-P15B-140
 */
public class Connexion {
    public static Connection getconnection() throws Exception{
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/banqueserver", "postgres", "admin");	
            return con;
        }catch (SQLException e) {
            e.getMessage();
        }
	return null;
    }
     public static Connection getconnectionMysql() throws ClassNotFoundException, Exception {
        Connection con = null;
        // Connexion à MySQL
        String mysqlUrl = "jdbc:mysql://localhost:3306/foncierserver";
        String mysqlUser = "root";
        String mysqlPassword = "";
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(mysqlUrl, mysqlUser, mysqlPassword);
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Connex Mysql erreur"+e.getMessage());
        }
    }
}
