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
 */
public class Contacto implements Serializable{
    private String nombre;  
    private List<Atributo> atributos;
    private List<Foto> fotos;
    private List<Relacion> contactos_relacionados;
    private boolean esFavorito;
    private String descripcion;
    private boolean esEmpresa;    
    private Etiqueta etiqueta;

    public Contacto() {
        this.atributos = new ArrayList<>();
        this.fotos = new DoublyCircularLinkedList<>();
        this.esFavorito = false;
        this.contactos_relacionados = new ArrayList<>();
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

    public List<Relacion> getContactos_relacionados() {
        return contactos_relacionados;
    }

    public void setContactos_relacionados(List<Relacion> contactos_relacionados) {
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

    public void addAtributo(Atributo a){
        this.atributos.add(a);
    }
    
    public boolean addContacto_relacionado(Relacion relacion) {
        return this.contactos_relacionados.add(relacion);
    }

    @Override
    public String toString() {
        return "Contacto{" + "nombre=" + nombre + ", atributos=" + atributos + ", fotos=" + fotos + ", contactos_relacionados=" + contactos_relacionados + ", esFavorito=" + esFavorito + ", descripcion=" + descripcion + '}';
    }

    public boolean isEsEmpresa() {
        return esEmpresa;
    }

    public void setEsEmpresa(boolean esEmpresa) {
        this.esEmpresa = esEmpresa;
    }
    
    public List<Foto> copy() {
        List<Foto> copia = new DoublyCircularLinkedList<>();
        for(Foto ft : this.getFotos()){
            copia.add(ft);
        }
        return copia;
    }    

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }
    
}
