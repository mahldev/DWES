package org.iesbelen.service;

import jakarta.servlet.http.HttpServletRequest;
import org.iesbelen.dao.FabricanteDAO;
import org.iesbelen.dao.FabricanteDAOImpl;
import org.iesbelen.dao.ProductoDAO;
import org.iesbelen.model.Fabricante;
import org.iesbelen.model.Producto;
import org.iesbelen.util.ResultadoDeCreacion;
import org.iesbelen.util.ResultadoDeValidacion;

import java.util.Optional;

import static org.iesbelen.model.Producto.validar;


public class ProductoService {

    private ProductoDAO productoDAO;

    public static ResultadoDeCreacion<Producto> crearProducto(String nombre,
                                                              Double precio,
                                                              String fabricante) {
        FabricanteDAO fabDAO = new FabricanteDAOImpl();
        ResultadoDeValidacion validaciones = new ResultadoDeValidacion();
        Optional<Fabricante> fabricanteOpt = fabDAO.findByName(fabricante);

        return getProductoResultadoDeCreacion(nombre, precio, validaciones, fabricanteOpt);
    }

    public static ResultadoDeCreacion<Producto> crearProducto(String nombre,
                                                              Double precio,
                                                              Integer idFabricante) {
        FabricanteDAO fabDAO = new FabricanteDAOImpl();
        ResultadoDeValidacion validaciones = new ResultadoDeValidacion();
        Optional<Fabricante> fabricanteOpt = fabDAO.find(idFabricante);

        return getProductoResultadoDeCreacion(nombre, precio, validaciones, fabricanteOpt);
    }

    private static ResultadoDeCreacion<Producto> getProductoResultadoDeCreacion(String nombre,
                                                                                Double precio,
                                                                                ResultadoDeValidacion validaciones,
                                                                                Optional<Fabricante> fabricanteOpt) {
        int idFabricante;
        Producto producto;

        if (fabricanteOpt.isEmpty()) {
            validaciones.addError("fabricante", "Fabricante Invalido");
            return new ResultadoDeCreacion<>(null, validaciones);
        }

        idFabricante = fabricanteOpt.get().getIdFabricante();
        validaciones = validar(nombre, precio);

        if (!validaciones.esValido()) {
            return new ResultadoDeCreacion<>(null, validaciones);
        }

        producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setCodigo_fabricante(idFabricante);
        return new ResultadoDeCreacion<>(producto, new ResultadoDeValidacion());
    }
}
