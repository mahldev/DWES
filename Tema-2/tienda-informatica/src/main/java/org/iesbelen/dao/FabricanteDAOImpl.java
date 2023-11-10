package org.iesbelen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.iesbelen.model.Fabricante;
import org.iesbelen.model.FabricanteDTO;

import static org.iesbelen.model.FabricanteDTO.crearFabricanteDTOdeFabricante;

public class FabricanteDAOImpl extends AbstractDAOImpl implements FabricanteDAO {

    /**
     * Inserta en base de datos el nuevo fabricante, actualizando el id en el bean fabricante.
     */
    @Override
    public synchronized void create(Fabricante fabricante) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();


            //1 alternativas comentadas:
            //ps = conn.prepareStatement("INSERT INTO fabricantes (nombre) VALUES (?)", new String[] {"codigo"});
            //Ver también, AbstractDAOImpl.executeInsert ...
            //Columna fabricante.codigo es clave primaria auto_increment, por ese motivo se omite de la sentencia SQL INSERT siguiente.
            ps = conn.prepareStatement("INSERT INTO fabricantes (nombre) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, fabricante.getNombre());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de fabricante con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                fabricante.setIdFabricante(rsGenKeys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    /**
     * Devuelve lista con todos los fabricantes.
     */
    @Override
    public List<Fabricante> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Fabricante> listFab = new ArrayList<>();

        try {
            conn = connectDB();

            // Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM fabricantes");
            while (rs.next()) {
                Fabricante fab = new Fabricante();
                int idx = 1;
                fab.setIdFabricante(rs.getInt(idx++));
                fab.setNombre(rs.getString(idx));
                listFab.add(fab);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listFab;

    }

    /**
     * Devuelve Optional de fabricante con el ID dado.
     */
    @Override
    public Optional<Fabricante> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM fabricantes WHERE idFabricante = ?");

            int idx = 1;
            ps.setInt(idx, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Fabricante fab = new Fabricante();
                idx = 1;
                fab.setIdFabricante(rs.getInt(idx++));
                fab.setNombre(rs.getString(idx));

                return Optional.of(fab);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();

    }

    @Override
    public Optional<Fabricante> findByName(String nombre) {
        String sql = "SELECT * FROM fabricantes WHERE nombre = ?";


        try (Connection conn = connectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Fabricante fab = new Fabricante();
                    fab.setIdFabricante(rs.getInt(1));
                    fab.setNombre(rs.getString(2));
                    return Optional.of(fab);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    /**
     * Actualiza fabricante con campos del bean fabricante según ID del mismo.
     */
    @Override
    public void update(Fabricante fabricante) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE fabricantes SET nombre = ?  WHERE idFabricante = ?");
            int idx = 1;
            ps.setString(idx++, fabricante.getNombre());
            ps.setInt(idx, fabricante.getIdFabricante());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de fabricante con 0 registros actualizados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    /**
     * Borra fabricante con ID proporcionado.
     */
    @Override
    public void delete(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM fabricantes WHERE idFabricante = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de fabricante con 0 registros eliminados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public Optional<Integer> getCountProductos(int idFabricante) {

        String sql = "SELECT COUNT(*) FROM `tienda`.`productos` WHERE idFabricante = ?";
        Optional<Integer> count = Optional.empty();

        try (Connection conn = connectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idFabricante);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) count = Optional.of(rs.getInt(1));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<FabricanteDTO> getAllDTOPlusCountProductos(String ordPor, String ascDesc) {
        List<FabricanteDTO> listFabDTO = new ArrayList<>();

        String dirOrde = "desc".equals(ascDesc) ? "DESC" : "ASC";

        String sql = """
             SELECT f.idFabricante as 'codigo', f.nombre, count(p.idProducto)
             FROM fabricantes f
             LEFT OUTER JOIN productos p
             USING(idFabricante)
             GROUP BY f.idFabricante
             ORDER BY %s %s
             """.formatted(ordPor, dirOrde);

        try (Connection conn = connectDB();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Fabricante fab = new Fabricante();

                fab.setIdFabricante(rs.getInt(1));
                fab.setNombre(rs.getString(2));
                int numeroProductos = rs.getInt(3);

                listFabDTO.add(crearFabricanteDTOdeFabricante(fab, numeroProductos));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listFabDTO;
    }

}
