/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progmovil.webservice.rest;

import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import static org.apache.taglibs.standard.functions.Functions.split;

/**
 * REST Web Service
 *
 * @author Administrador
 */
@Path("maincontroller")
public class MainController {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MainController
     */
    public MainController() {
    }

    /**
     * Retrieves representation of an instance of com.progmovil.webservice.rest.MainController
     * @return an instance of java.lang.String
     */
    @PUT
    @Path("operation")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(String parameter) throws SQLException, ClassNotFoundException {
        
       
        /*SqliteConnection tracku = new SqliteConnection();
        String parameters[] = split(parameter,",");
        
        System.out.println(parameters[0]);
        System.out.println(parameters[1]);
       
      
        //tracku.insert(parameters[0], 1,parameters[1]);
        tracku.createNewDatabase();*/
        return "Hola Adriana";
    }

    /**
     * PUT method for updating or creating an instance of MainController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("operation2")
    public void putJson(String content) {
    }
    
    @GET
    //@Consumes(MediaType.APPLICATION_JSON)
    @Path("operation3")
    @Produces(MediaType.TEXT_PLAIN)
    public String test(String content) {
        return "Hola";
    }
     
    @GET
    @Path("/Connect")
    @Produces(MediaType.APPLICATION_JSON)
    public String Connect(){
        return SQLiteJDBCDriverConnection.connect();
    }
    
    @GET
    @Path("/getStateConnexion")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTest(){
        return (SQLiteJDBCDriverConnection.getConnexionState()+"");
    }
    
    @GET
    @Path("/CreateTables")
    @Produces(MediaType.APPLICATION_JSON)
    public String CreateUser() throws SQLException{ 
        String Users = " CREATE TABLE PruebaUsuario (id INTEGER PRIMARY KEY AUTOINCREMENT, email	TEXT NOT NULL UNIQUE, password	TEXT NOT NULL,estado	INTEGER NOT NULL)";
        
        String Geolocation = "CREATE TABLE PruebaGEO (" +
                                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                                "email TEXT NOT NULL," +
                                "lat REAL NOT NULL," +
                                "lng REAL NOT NULL," +
                                "time NUMERIC NOT NULL" +
                                ")" ;
        
        SQLiteJDBCDriverConnection.connexion().createStatement().execute(Users);
        SQLiteJDBCDriverConnection.connexion().createStatement().execute(Geolocation);
        return "Tabla Creada";
    }
    
}
