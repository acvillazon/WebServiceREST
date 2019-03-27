/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progmovil.webservice.rest;

/**
 *
 * @author Francisco
 */

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import static java.time.Clock.system;

public class SqliteConnection {

     public Connection connect() {
        Connection conn = null;
        
            // db parameters
            
 
        
        String url = "jdbc:sqlite:TrackU.db";
        //System.out.println(System.getProperty("user.dir"));
            // create a connection to the database
            
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
        
        
     }
     
     public void selectAll(){
        String sql = "SELECT * FROM usuarios";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("userId") +  "\t" + 
                                   rs.getString("email") + "\t" +
                                   rs.getDouble("latitud"));
            }
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
     
     public void insert(String email,int userId,String passwordHash) {
        String sql = "INSERT INTO usuarios(userId,email,passwordHash) VALUES(?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,userId);
            pstmt.setString(2,email);
            pstmt.setString(3,passwordHash);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     
     
    public  void createNewDatabase() throws SQLException, ClassNotFoundException {
 
        
        String path=System.getProperty("user.dir");
            
        Class.forName("org.sqlite.JDBC");
        
        try (Connection conn = DriverManager.getConnection(path+"\\TrackU.db")) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
            System.out.println("llegue");
      
    }
     
//    public static void main(String[] args) {
//        SqliteConnection tracku = new SqliteConnection();
//        tracku.selectAll();
//        tracku.insert("franciscovegac97@gmail.com,",20,"34f4423");
//        
//    }
    
}
