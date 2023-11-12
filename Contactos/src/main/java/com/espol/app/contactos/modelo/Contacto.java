/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.modelo;

import com.espol.app.contactos.utilidades.List;
import java.io.Serializable;

/**
 *
 * @author mfalvarez
 * @param <T>
 */
public class Contacto<T> implements Serializable{

    private List<Atributo<T>> atributos;
    private List<Foto> fotos;
    private List<Contacto<T>> contactos_relacionados;
    private boolean esFavorito;
    private String descripcion;

    public List<Atributo<T>> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo<T>> atributos) {
        this.atributos = atributos;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public List<Contacto<T>> getContactos_relacionados() {
        return contactos_relacionados;
    }

    public void setContactos_relacionados(List<Contacto<T>> contactos_relacionados) {
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

    public Contacto(List<Atributo<T>> atributos, List<Foto> fotos, List<Contacto<T>> contactos_relacionados, String descripcion) {
        this(atributos, fotos);
        this.contactos_relacionados = contactos_relacionados;
        this.descripcion = descripcion;
    }

    public Contacto(List<Atributo<T>> atributos, List<Foto> fotos) {
        this.atributos = atributos;
        this.fotos = fotos;
    }
    public Contacto(){
        
    }

}
