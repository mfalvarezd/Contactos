/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.modelo;

import java.io.Serializable;

/**
 *
 * @author mfalvarez
 */
public class Foto implements Serializable{
    private String url;

    public Foto(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
    
    public Foto() {
        this.url = "file:imagenes\\user.png";
    }    

    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public boolean equals(Object foto) {
        if (this == foto) {
            return true;
        }
        
        if (foto==null || this.getClass()!=foto.getClass()) {
            return false;
        }        
        Foto f = (Foto) foto;
        return this.url.equals(f.getUrl());
    }
    
    
}
