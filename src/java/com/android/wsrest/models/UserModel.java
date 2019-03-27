/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.android.wsrest.models;

/**
 *
 * @author villa
 */
public class UserModel {
    
    private String email;
    private String password_hash;
    //private int estado;
    private int id;

    public UserModel(String Email, String Password, int id) {
        this.email = Email;
        this.password_hash = Password;
        //this.estado = estado;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getPassword() {
        return password_hash;
    }

    public void setPassword(String Password) {
        this.password_hash = Password;
    }

    /*public int getEstado() {
        return estado;
    }*/

    /*public void setEstado(int estado) {
        this.estado = estado;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
