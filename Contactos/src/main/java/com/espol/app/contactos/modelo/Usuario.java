/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.modelo;



import com.espol.app.contactos.utilidades.DoublyCircularLinkedList;
import com.espol.app.contactos.utilidades.List;
import java.io.Serializable;

/**
 *
 * @author mfalvarez
 */
public class Usuario implements Serializable{
    private String nombre;
    private String user;
    private String password;
    private List<Contacto> contactos;

    public Usuario(String user, String password, String nombre) {
        this.user = user;
        this.password = password;
        this.nombre=nombre;
        contactos = new DoublyCircularLinkedList<>();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", user=" + user + ", password=" + password + ", contactos=" + contactos + '}';
    }
    
    
    

    
}
