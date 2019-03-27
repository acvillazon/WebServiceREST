/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progmovil.webservice.rest;

import com.android.wsrest.models.UserModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author aruge
 */
@Path("user")
public class UserController {
    
    @PUT 
    @Path("/register")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String SignUp(String usuario) { 
       String[] datos = usuario.split("-");
       System.out.println(usuario);
       SQLiteJDBCDriverConnection.connect();
        try {

            PreparedStatement ps = SQLiteJDBCDriverConnection.connexion().prepareStatement("INSERT INTO PruebaUsuario (email, password, estado) VALUES (?,?,?)");
            ps.setString(1, datos[0]);
            ps.setString(2, datos[1]); 
            ps.setInt(3, 0);
            ps.executeUpdate();
            String exitoso="1";
            System.out.println("1");
            return exitoso;
            
        } catch (SQLException ex) {
            System.out.println("0");
            String fallo="0";
            return fallo;
        }     
    }
    
    
    
    @PUT
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String Login(String usuario){
      String[] datos = usuario.split("-");
      String exitoso="1";
      String fallo = "0";
      int rows=0;
       System.out.println("Login");
        
        try{
            SQLiteJDBCDriverConnection.connect();
            ResultSet a = SQLiteJDBCDriverConnection.connexion().createStatement().executeQuery("SELECT * FROM PruebaUsuario WHERE email =" + "'" + datos[0] + "'");

           while (a.next()) {
                if (a.getString("email").equals(datos[0]) && a.getString("password").equals(datos[1])) {
                    rows = SQLiteJDBCDriverConnection.connexion().createStatement().executeUpdate("UPDATE PruebaUsuario SET estado='1' WHERE email =" + "'" + datos[0] + "'");
                }
            }
            
            if (rows > 0) {
                System.out.println(rows);
                return exitoso;
            }else{
                return fallo;
            }
            
        } catch (SQLException e) {
            return fallo;
        } 
    }
    
    @PUT
    @Path("/getUsers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getUsers(String usuario){
      System.out.println("GetUsers");
      String fallo="0";
      Gson gson = new Gson();
      ArrayList<String> usuarios = new ArrayList<String>();
      int rows;
        try{
            SQLiteJDBCDriverConnection.connect();
            ResultSet a = SQLiteJDBCDriverConnection.connexion().createStatement().executeQuery("SELECT * FROM PruebaUsuario");

           while (a.next()) {
             usuarios.add(a.getString("email"));
           }
           
           String ListaUsers = gson.toJson(usuarios);
           System.out.println(ListaUsers);
           
           return (ListaUsers);
        } catch (SQLException e) {
            return fallo;
        } 
    }
    
    
    
    @PUT
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String LogOut(String email){
        String exitoso="1";
        String fallo = "0";
        int rows=0;
        
        try{
            SQLiteJDBCDriverConnection.connect();
            rows = SQLiteJDBCDriverConnection.connexion().createStatement().executeUpdate("UPDATE PruebaUsuario SET estado='0' WHERE email =" + "'" + email+ "'");
            
            if (rows > 0) {
                System.out.println(rows);
                return exitoso;
            }else{
                return fallo;
            }
            
        }catch(SQLException e){
            return fallo;
        }
        
    }
   
}
