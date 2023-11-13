/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.modelo;

import java.io.Serializable;

/**
 *
 * @author mfalvarez
 * 
 */
public class Atributo implements Serializable{

    private String nombre;
    private String valor;
    private Tipo tipo;

    public Atributo(String nombre, String valor, Tipo tipo) {
        this.nombre = nombre;
        this.valor = valor;
        this.tipo = tipo;
    }
    public Atributo() {

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Atributo{" + "nombre=" + nombre + ", valor=" + valor + ", tipo=" + tipo + '}';
    }
   

}
