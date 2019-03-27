/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.android.wsrest.models;

import java.sql.Date;

/**
 *
 * @author villa
 */
public class GeolocationModel {
    
    private int id;
    private String email;
    private float latitude;
    private float longitude;
    private Long time;

    public GeolocationModel(int id, String email, float latitude, float longitude, Long date) {
        this.id = id;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Long getDate() {
        return time;
    }

    public void setDate(Long date) {
        this.time = date;
    }

    
}
