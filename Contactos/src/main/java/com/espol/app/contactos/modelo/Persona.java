/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.modelo;

import com.espol.app.contactos.utilidades.List;

/**
 *
 * @author ander
 */
public class Persona extends Contacto {
    private String apellidos;    

    public Persona() {
        super();
        this.setEsEmpresa(false);
    }    
    
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    @Override
    public String toString() {
        return "Contacto{" + "nombre=" + super.getNombre() + ", apellidos=" + apellidos + ", atributos=" + super.getAtributos() + ", fotos=" + super.getFotos() + ", contactos_relacionados=" + super.getContactos_relacionados() + ", esFavorito=" + super.isEsFavorito() + ", descripcion=" + super.getDescripcion() + '}';
    }    
}
