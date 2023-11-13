/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.utilidades;

import com.espol.app.contactos.modelo.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author mfalvarez
 */
public class ManejoArchivos {

    public static boolean logIn(String user, String password) {
        try {
            Usuario u = getDatos(user);
            if (u.getUser().equals(user) && u.getPassword().equals(password)) {

                return true;
            }
        } catch (Exception ex) {
            return false;
        }

        return false;

    }

    public static Usuario getDatos(String user) {
        for (Usuario u : getUsuarios()) {
            if (u.getUser().equals(user)) {
                return u;
            }
        }
        return null;

    }

    public static ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        final File carpeta = new File("usuarios");
        for (final File ficheroEntrada : carpeta.listFiles()) {

            try {
                ObjectInputStream ob = new ObjectInputStream(new FileInputStream(ficheroEntrada));
                usuarios.add((Usuario) ob.readObject());
                ob.close();
            } catch (IOException | ClassNotFoundException ex) {

                ex.printStackTrace();
            }

        }
        return usuarios;
    }

    public static boolean validarUsuario(String user) {
        return getDatos(user) != null;
    }

    public static void agregarUsuario(String usuario, String password, String nombre) {
        try {

            FileOutputStream fout = new FileOutputStream("usuarios/" + usuario + ".ser");

            ObjectOutputStream obj = new ObjectOutputStream(fout);

            obj.writeObject(new Usuario(usuario, password, nombre));

            obj.flush();
            obj.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void guardarDatos(Usuario usuarioLogeado) {
        try {

            FileOutputStream fout = new FileOutputStream("usuarios/" + usuarioLogeado.getUser()+ ".ser");
            ObjectOutputStream obj = new ObjectOutputStream(fout);
            obj.writeObject(usuarioLogeado);

            obj.flush();
            obj.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
