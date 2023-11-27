package com.espol.app.contactos.utilidades;

import com.espol.app.contactos.modelo.Usuario;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ManejoArchivos {

    public static boolean logIn(String user, String password) {
        try {
            Usuario u = getDatos(user);
            return u != null && u.getUser().equals(user) && u.getPassword().equals(password);
        } catch (IOException | ClassNotFoundException ex) {
            // Manejar la excepción de manera adecuada, por ejemplo, registrando el error.
            return false;
        }
    }

    public static Usuario getDatos(String user) throws IOException, ClassNotFoundException {
        for (Usuario u : getUsuarios()) {
            if (u.getUser().equals(user)) {
                return u;
            }
        }
        return null;
    }

    public static ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        final Path carpeta = Paths.get("usuarios");
        for (final File ficheroEntrada : carpeta.toFile().listFiles()) {
            try (ObjectInputStream ob = new ObjectInputStream(new FileInputStream(ficheroEntrada))) {
                usuarios.add((Usuario) ob.readObject());
            } catch (IOException | ClassNotFoundException ex) {
                // Manejar la excepción de manera adecuada, por ejemplo, registrando el error.
                ex.printStackTrace();
            }
        }
        return usuarios;
    }

    public static boolean validarUsuario(String user) throws ClassNotFoundException {
        try {
            return getDatos(user) != null;
        } catch (IOException ex) {
        }
        return false;
    }

    public static void agregarUsuario(String usuario, String password, String nombre) {
        try {
            Path filePath = Paths.get("usuarios", usuario + ".ser");
            try (FileOutputStream fout = new FileOutputStream(filePath.toFile());
                 ObjectOutputStream obj = new ObjectOutputStream(fout)) {
                obj.writeObject(new Usuario(usuario, password, nombre));
            }
        } catch (IOException ex) {
            // Manejar la excepción de manera adecuada, por ejemplo, registrando el error.
            ex.printStackTrace();
        }
    }

    public static void guardarDatos(Usuario usuarioLogeado) {
        try {
            Path filePath = Paths.get("usuarios", usuarioLogeado.getUser() + ".ser");
            try (FileOutputStream fout = new FileOutputStream(filePath.toFile());
                 ObjectOutputStream obj = new ObjectOutputStream(fout)) {
                obj.writeObject(usuarioLogeado);
            }
        } catch (IOException ex) {
            // Manejar la excepción de manera adecuada, por ejemplo, registrando el error.
            ex.printStackTrace();
        }
    }
}
