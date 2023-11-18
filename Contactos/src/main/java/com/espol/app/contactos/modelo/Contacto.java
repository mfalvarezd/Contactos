/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.modelo;

import com.espol.app.contactos.utilidades.ArrayList;
import com.espol.app.contactos.utilidades.DoublyCircularLinkedList;
import com.espol.app.contactos.utilidades.List;
import java.io.Serializable;

/**
 *
 * @author mfalvarez
 * @param <T>
 */
public class Contacto implements Serializable{
    private String nombre;
    private String apellidos;
    private List<Atributo> atributos;
    private List<Foto> fotos;
    private List<Contacto> contactos_relacionados;
    private boolean esFavorito;
    private String descripcion;

    public Contacto() {
        this.atributos = new ArrayList<>();
        this.fotos = new DoublyCircularLinkedList<>();
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public List<Contacto> getContactos_relacionados() {
        return contactos_relacionados;
    }

    public void setContactos_relacionados(List<Contacto> contactos_relacionados) {
        this.contactos_relacionados = contactos_relacionados;
    }

    public boolean isEsFavorito() {
        return esFavorito;
    }

    public void setEsFavorito(boolean esFavorito) {
        this.esFavorito = esFavorito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public void addAtributo(Atributo a){
        this.atributos.add(a);
    }

    @Override
    public String toString() {
        return "Contacto{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", atributos=" + atributos + ", fotos=" + fotos + ", contactos_relacionados=" + contactos_relacionados + ", esFavorito=" + esFavorito + ", descripcion=" + descripcion + '}';
    }
    
    

}
