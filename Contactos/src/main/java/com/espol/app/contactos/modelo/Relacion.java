/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.modelo;

import com.espol.app.contactos.utilidades.ArrayList;
import com.espol.app.contactos.utilidades.List;
import java.io.Serializable;

/**
 *
 * @author ander
 */
public class Relacion implements Serializable{
    private String t_relacion;
    private Contacto contacto;

    public Relacion(String t_relacion, Contacto contacto) {
        this.t_relacion = t_relacion;
        this.contacto = contacto;
    }
        
    public String getT_relacion() {
        return t_relacion;
    }

    public void setT_relacion(String t_relacion) {
        this.t_relacion = t_relacion;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

}
