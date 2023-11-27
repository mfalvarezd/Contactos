/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.modelo;

/**
 *
 * @author mfalvarez
 */
public class UsuarioSingleton {

    private static Usuario instancia;

    private UsuarioSingleton() {
        // Constructor privado para evitar instanciación directa
    }

    public static Usuario getInstancia() {
        if (instancia == null) {
            // Puedes lanzar una excepción aquí si se intenta acceder antes de que se haya iniciado sesión
            // o inicializar la instancia con un usuario predeterminado.
        }
        return instancia;
    }

    public static void setInstancia(Usuario usuario) {
        instancia = usuario;
    }
}
