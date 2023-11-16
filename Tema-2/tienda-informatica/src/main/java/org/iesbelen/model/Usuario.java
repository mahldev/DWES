package org.iesbelen.model;

import org.iesbelen.util.ResultadoDeCreacion;
import org.iesbelen.util.ResultadoDeValidacion;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Usuario {


    private int id;
    private String usuario;
    private String password;
    private String rol;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static ResultadoDeValidacion validar(String nombre,
                                                String password,
                                                String rol) {

        ResultadoDeValidacion validaciones = new ResultadoDeValidacion();

        if (nombre == null || nombre.isBlank() || password == null || password.isBlank()) {
            validaciones.addError("todos", "Todos los campos son requeridos");
        }
        return validaciones;
    }

    public static ResultadoDeCreacion<Usuario> crearUsuario(String nombre,
                                                            String password,
                                                            String rol) {

        if (!"Administrador".equals(rol) && !"Vendedor".equals(rol)) {
            rol = "Cliente";
        }

        Usuario usuario = new Usuario();
        usuario.setUsuario(nombre);
        usuario.setPassword(hashPassword(password));
        usuario.setRol(rol);

        ResultadoDeValidacion resVal =
                validar(nombre, password, rol);

        return new ResultadoDeCreacion<>(usuario, resVal);
    }

    public static String hashPassword(String password) {
        MessageDigest digest;

        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] encodedhash = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));

        return bytesToHex(encodedhash);
    }

    private static String bytesToHex(byte[] byteHash) {

        StringBuilder hexString = new StringBuilder(2 * byteHash.length);
        for (int i = 0; i < byteHash.length; i++) {
            String hex = Integer.toHexString(0xff & byteHash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();

    }


}
