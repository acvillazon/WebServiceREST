/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progmovil.webservice.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author villa
 */
public final class SQLiteJDBCDriverConnection {
     /**
     * Connect to a sample database
     */
    private static Connection conn= null;
    
    private SQLiteJDBCDriverConnection(){
        
    }
    
    public static String connect() {   
        if(conn==null){
            try {
                // db parameters
                //String url = "jdbc:derby://localhost:1527/DataApp";
                Class.forName("org.sqlite.JDBC");
                String url = "jdbc:sqlite:C:\\Universidad\\movil\\parcial1ultimo\\WebServiceREST\\TrackU.db";
                System.out.println(System.getProperty("user.dir"));
                conn = DriverManager.getConnection(url);

                return ("Connection to SQLite has been established.");

            } catch (Exception e) {
                System.out.println("No conneccion");
                return e.getMessage();
            }
        }else{
           conn = conn;
        }
        return null;
    }
    
    public static boolean getConnexionState(){
        if(conn==null){
            return false;
        }else{
            return true;
        }
    }
    
    public static Connection connexion(){
        return conn;
    }
}
