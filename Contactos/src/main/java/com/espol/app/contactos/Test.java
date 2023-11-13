/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos;

import com.espol.app.contactos.modelo.Atributo;
import com.espol.app.contactos.modelo.Contacto;
import com.espol.app.contactos.modelo.Tipo;
import com.espol.app.contactos.modelo.Usuario;
import com.espol.app.contactos.utilidades.DoublyCircularLinkedList;
import com.espol.app.contactos.utilidades.List;


/**
 *
 * @author mfalvarez
 */
public class Test {
    public static void main(String[] args) {
        Usuario u = new Usuario("admin","123","administrador");
        List<Contacto> lista = new DoublyCircularLinkedList<>();
        Contacto c = new Contacto();
        c.setNombre("Fernando");
        c.setApellidos("Alvarez");
        Atributo a = new Atributo();
        a.setNombre("Celular");
        a.setTipo(Tipo.TELEFONO);
        a.setValor("0664249600");
        c.addAtributo(a);
         Atributo b = new Atributo();
        b.setNombre("Correo");
        b.setTipo(Tipo.TELEFONO);
        b.setValor("mfalvare@espol.edu.ec");
        c.addAtributo(b);
        lista.add(c);
        u.setContactos(lista);
        System.out.println(u.getContactos());
    }
    
}
