package org.iesbelen.dao;

import org.iesbelen.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDAOImpl extends AbstractDAOImpl implements UsuarioDAO {

    @Override
    public void create(Usuario usuario) {
        String sql = "INSERT INTO usuarios (usuario, password, rol) VALUES (?, ?, ?)";

        try (Connection conn = connectDB();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;

            ps.setString(index++, usuario.getUsuario());
            ps.setString(index++, usuario.getPassword());
            ps.setString(index, usuario.getRol());
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Usuario> getAll() {

        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarioList = new ArrayList<>();

        try (Connection conn = connectDB();
                Statement s = conn.createStatement();
                ResultSet rs = s.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = crearUsuario(rs);
                usuarioList.add(usuario);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return usuarioList;
    }

    @Override
    public Optional<Usuario> find(int id) {

        String sql = "SELECT * FROM usuarios WHERE idUsuario = ?";

        try (Connection conn = connectDB();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = crearUsuario(rs);
                    return Optional.of(usuario);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> findByName(String nombre) {

        String sql = "SELECT * FROM usuarios WHERE usuario = ?";

        try (Connection conn = connectDB();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return Optional.of(crearUsuario(rs));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public void update(Usuario usuario) {

        String sql = """
                UPDATE usuarios
                SET
                    idUsuario = ?,
                    usuario = ?,
                    password = ?,
                    rol = ?
                WHERE idUsuario = ?
                """;

        try (Connection conn = connectDB();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            int index = 1;

            ps.setInt(index++, usuario.getIdUsuario());
            ps.setString(index++, usuario.getUsuario());
            ps.setString(index++, usuario.getPassword());
            ps.setString(index++, usuario.getRol());
            ps.setInt(index, usuario.getIdUsuario());

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM usuarios WHERE idUsuario = ?";

        try (Connection conn = connectDB();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private Usuario crearUsuario(ResultSet rs) throws SQLException {
        int index = 1;

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(rs.getInt(index++));
        usuario.setUsuario(rs.getString(index++));
        usuario.setPassword(rs.getString(index++));
        usuario.setRol(rs.getString(index));

        return usuario;
    }
}
