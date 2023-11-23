package org.iesbelen.model;

import org.iesbelen.dao.UsuarioDAO;
import org.iesbelen.dao.UsuarioDAOImpl;
import org.iesbelen.util.ResultadoDeCreacion;
import org.iesbelen.util.ResultadoDeValidacion;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class Usuario {

    private int idUsuario;
    private String usuario;
    private String password;
    private String rol;

    public static ResultadoDeCreacion<Usuario> login(String usuario,
            String password) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        Optional<Usuario> user = usuarioDAO.findByName(usuario);
        return new ResultadoDeCreacion<>(user.orElse(new Usuario()), validarLogin(user, password));
    }

    public static ResultadoDeValidacion validarLogin(Optional<Usuario> usuario,
            String password) {
        ResultadoDeValidacion res = new ResultadoDeValidacion();

        if (usuario.isEmpty()) {
            res.addError("usuarioNoExiste", "El usuario no existe en la base de datos");
            return res;
        }

        if (!usuario.get().getPassword().equals(hashPassword(password))) {
            res.addError("passwordNoEsCorrecta", "La contraseña no coincide con el usuarioº");
        }

        return res;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Usuario usuario = (Usuario) o;
        return idUsuario == usuario.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
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

        ResultadoDeValidacion resVal = validar(nombre, password, rol);

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

    public Usuario esAdministrador(Consumer<Usuario> esAdmin) {
        if (this.rol.equals("Administrador")) {
            esAdmin.accept(this);
        }

        return this;
    }

    public Usuario esVendedor(Consumer<Usuario> esAdmin) {
        if (this.rol.equals("Administrador")) {
            esAdmin.accept(this);
        }

        return this;
    }

    public Usuario esCliente(Consumer<Usuario> esAdmin) {
        if (this.rol.equals("Administrador")) {
            esAdmin.accept(this);
        }

        return this;
    }
}
