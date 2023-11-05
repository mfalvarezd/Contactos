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
 */
public class Empresa extends Contacto implements Serializable{

    public Empresa(List atributos, List fotos, List contactos_relacionados, String descripcion) {
        super(atributos, fotos, contactos_relacionados, descripcion);
    }

    public Empresa(List atributos, List fotos) {
        super(atributos, fotos);
    }
    

    
}
