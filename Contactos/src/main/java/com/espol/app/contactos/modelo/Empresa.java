/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.modelo;

/**
 *
 * @author ander
 */
public class Empresa extends Contacto {

    public Empresa() {
        super();
    }
    
    @Override
    public String toString() {
        return "Contacto{" + "nombre=" + super.getNombre() + ", atributos=" + super.getAtributos() + ", fotos=" + super.getFotos() + ", contactos_relacionados=" + super.getContactos_relacionados() + ", esFavorito=" + super.isEsFavorito() + ", descripcion=" + super.getDescripcion() + '}';
    }     
}
