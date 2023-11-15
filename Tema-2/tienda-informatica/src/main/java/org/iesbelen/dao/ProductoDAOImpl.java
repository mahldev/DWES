package org.iesbelen.dao;

import org.iesbelen.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDAOImpl extends AbstractDAOImpl implements ProductoDAO {

    /**
     * Inserta en base de datos el nuevo fabricante, actualizando el id en el bean fabricante.
     */
    @Override
    public synchronized void create(Producto producto) {

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
            ps = conn.prepareStatement("INSERT INTO productos (nombre, precio, idFabricante) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, producto.getNombre());
            ps.setDouble(idx++, producto.getPrecio());
            ps.setInt(idx++, producto.getCodigo_fabricante());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de fabricante con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                producto.setIdProducto(rsGenKeys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
    }

    /**
     * Devuelve lista con todos loa fabricantes.
     */
    @Override
    public List<Producto> getAll(String nombre) {

        String sql = "SELECT * FROM productos WHERE nombre LIKE ?";

        List<Producto> listProd = new ArrayList<>();

        try (Connection conn = connectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto prod = new Producto();
                    int idx = 1;
                    prod.setIdProducto(rs.getInt(idx++));
                    prod.setNombre(rs.getString(idx++));
                    prod.setPrecio(rs.getDouble(idx++));
                    prod.setCodigo_fabricante(rs.getInt(idx));
                    listProd.add(prod);
                }
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        return listProd;
    }

    /**
     * Devuelve Optional de fabricante con el ID dado.
     */
    @Override
    public Optional<Producto> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM productos WHERE idProducto = ?");

            int idx = 1;
            ps.setInt(idx, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Producto prod = new Producto();
                idx = 1;
                prod.setIdProducto(rs.getInt(idx++));
                prod.setNombre(rs.getString(idx++));
                prod.setPrecio(rs.getDouble(idx++));
                prod.setCodigo_fabricante(rs.getInt(idx++));

                return Optional.of(prod);
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

    /**
     * Actualiza fabricante con campos del bean fabricante según ID del mismo.
     */
    @Override
    public void update(Producto producto) {

        String sql = "UPDATE productos " +
                "SET idProducto = ?, nombre = ?, precio = ?, idFabricante = ? " +
                "WHERE idProducto = ?";

        try (Connection conn = connectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            int index = 1;

            ps.setInt(index++, producto.getIdProducto());
            ps.setString(index++, producto.getNombre());
            ps.setDouble(index++, producto.getPrecio());
            ps.setInt(index++, producto.getCodigo_fabricante());
            ps.setInt(index, producto.getIdProducto());

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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

            ps = conn.prepareStatement("DELETE FROM productos WHERE idProducto = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de fabricante con 0 registros eliminados.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

}
