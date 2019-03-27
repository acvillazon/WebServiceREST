/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progmovil.webservice.rest;

import com.android.wsrest.models.GeolocationModel;
import com.android.wsrest.models.UserModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author aruge
 */
@Path("location")
public class GeolocationController {

    @PUT
    @Path("/savelocation")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String SaveLocation(String location) throws SQLException {
        String exitoso="1";
        String fallo = "0";
        SQLiteJDBCDriverConnection.connect();

        try {

            Gson gson = new Gson();
            JsonArray json = gson.fromJson(location, JsonArray.class);
            System.out.println("Reponse"+json.size());
            
            for (int i = 0; i < json.size(); i++) {
                GeolocationModel geo = gson.fromJson(json.get(i), GeolocationModel.class);
                PreparedStatement ps = SQLiteJDBCDriverConnection.connexion().prepareStatement("INSERT INTO PruebaGEO (email, lat, lng, time) VALUES (?,?,?,?)");
                ps.setString(1,geo.getEmail() );
                ps.setFloat(2,geo.getLatitude()); 
                ps.setFloat(3,geo.getLongitude());
                ps.setLong(4,geo.getDate());
                ps.executeUpdate();                
            }
            
           return exitoso;
        } catch (Exception ex) {
                return fallo;
        }
    }

    
    @PUT
    @Path("/lastlocation")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String lastLocation( ) throws SQLException {
        String fallo = "0";
        SQLiteJDBCDriverConnection.connect();            
        try{
            //ResultSet dato = SQLiteJDBCDriverConnection.connexion().createStatement().executeQuery("SELECT PruebaGEO FROM PruebaGEO INNER JOIN (SELECT MAX(time) AS date, email FROM PruebaGEO GROUP BY email) AS Last ON Last.date == PruebaGEO.time AND Last.email == PruebaGEO.email");
            //SELECT *,MAX(time) FROM Location GROUP BY email
            //ResultSet dato = SQLiteJDBCDriverConnection.connexion().createStatement().executeQuery("SELECT * FROM PruebaGEO WHERE id IN (SELECT MAX(id) FROM PruebaGEO GROUP BY email)");
            ResultSet dato = SQLiteJDBCDriverConnection.connexion().createStatement().executeQuery("SELECT * , MAX(time) AS date FROM PruebaGEO GROUP BY email");

            Gson gson = new Gson();
           // String datos = gson.toJson(dato);
            System.out.println(dato.toString());
            ArrayList<GeolocationModel> users = new ArrayList<>(); 
            ArrayList<String> estados = new ArrayList<>(); 
            
            while (dato.next()) {                
                GeolocationModel usuario = new GeolocationModel(0,dato.getString("email"),
                        dato.getFloat("lat"),dato.getFloat("lng"),dato.getLong("time"));
                
                ResultSet estado = SQLiteJDBCDriverConnection.connexion().createStatement().executeQuery("SELECT estado FROM PruebaUsuario WHERE email =" + "'" + dato.getString("email") + "'");
                
                while(estado.next()){
                    estados.add(estado.getString("estado"));
                }
                
                users.add(usuario);                
                System.out.println(usuario.toString());

            }
            String data = gson.toJson(users);
            String state = gson.toJson(estados);
            ArrayList<String> dataState = new ArrayList<>();
            dataState.add(data); dataState.add(state);
            String data_state = gson.toJson(dataState);
            System.out.println(data_state);
            return data_state;
            
        }catch(SQLException e){
            return fallo;
        } 
    }

    
    @PUT
    @Path("/getHistory")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String GetHistory(String location) throws SQLException {
        String[] inputs = location.split("-");
        String fallo = "0";
        ArrayList<GeolocationModel> geoModel = new ArrayList<>();
        try {
            
            SQLiteJDBCDriverConnection.connect();
            PreparedStatement preparedStatement = SQLiteJDBCDriverConnection.connexion().prepareStatement("SELECT * FROM PruebaGEO where (email=?) AND (time BETWEEN ? and ?)");
            preparedStatement.setString(1, inputs[0]);
            preparedStatement.setString(2, inputs[1]);
            preparedStatement.setString(3, inputs[2]);
            
            
            ResultSet dato = preparedStatement.executeQuery();
            
            while(dato.next()){
                GeolocationModel usuario = new GeolocationModel(dato.getInt("id"),dato.getString("email"),
                        dato.getFloat("lat"),dato.getFloat("lng"),dato.getLong("time"));
                geoModel.add(usuario);
            }
            Gson gson = new Gson();
            String data = gson.toJson(geoModel);
            System.out.println(data);
            return data;
        } catch (Exception ex) {
                return fallo;
        }
    }
    
    
    
}
