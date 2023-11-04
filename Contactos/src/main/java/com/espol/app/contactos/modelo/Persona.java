/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.modelo;

import com.espol.app.contactos.utilidades.List;
import java.util.Date;

/**
 *
 * @author mfalvarez
 */
public class Persona extends Contacto{
    private String apellidos;
    private Date bday;

    public Persona(String apellidos, Date bday, List atributos, List fotos, List contactos_relacionados, String descripcion) {
        super(atributos, fotos, contactos_relacionados, descripcion);
        this.apellidos = apellidos;
        this.bday = bday;
    }

    public Persona(String apellidos, Date bday, List atributos, List fotos) {
        super(atributos, fotos);
        this.apellidos = apellidos;
        this.bday = bday;
    }


    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }
    
    
}
