package org.iesbelen.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.HttpMethod;

import org.iesbelen.dao.FabricanteDAO;
import org.iesbelen.dao.FabricanteDAOImpl;
import org.iesbelen.dao.ProductoDAO;
import org.iesbelen.dao.ProductoDAOImpl;
import org.iesbelen.model.Fabricante;
import org.iesbelen.service.ProductoService;
import org.iesbelen.util.Enrutador;
import org.iesbelen.util.HttpRequestUtils;

import java.io.IOException;

import static org.iesbelen.util.HttpRequestUtils.*;

@WebServlet(name = "productosServlet", value = "/tienda/productos/*")
public class ProductosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Enrutador enrutador;

    @Override
    public void init() {
        enrutador = new Enrutador();
        enrutador.addRuta(HttpMethod.GET, "/tienda/productos/", this::mostrarProductos);
        enrutador.addRuta(HttpMethod.GET, "/tienda/productos/editar/\\d+/?", this::devolverProducto);
        enrutador.addRuta(HttpMethod.GET, "/tienda/productos/crear/", this::devolverJSPCrearProducto);

        enrutador.addRuta(HttpMethod.POST, "/tienda/productos/", this::crearProducto);

        enrutador.addRuta(HttpMethod.PUT, "/tienda/productos/editar/", this::modificarProducto);

        enrutador.addRuta(HttpMethod.DELETE, "/tienda/productos/borrar/", this::eliminarProducto);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        enrutador.manejarRequest(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        enrutador.manejarRequest(req, res);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) {
        enrutador.manejarRequest(req, res);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) {
        enrutador.manejarRequest(req, res);
    }

    private void mostrarProductos(HttpServletRequest req, HttpServletResponse res) {
        ProductoDAO fabDAO = new ProductoDAOImpl();

        String searchFormHidden = req.getParameter("searchFormHidden");
        String nombreFiltro = HttpRequestUtils.getCadenaODefault(req, "filtrar-por-nombre");

        req.setAttribute("listaProductos", fabDAO.getAll(nombreFiltro));
        req.setAttribute("searchQuery", nombreFiltro);
        req.setAttribute("searchFormHidden", searchFormHidden);

        forwardToJsp("/WEB-INF/jsp/productos/productos.jsp", req, res);
    }

    private void devolverProducto(HttpServletRequest req, HttpServletResponse res) {

        ProductoDAO productoDAO = new ProductoDAOImpl();
        FabricanteDAO fabDAO = new FabricanteDAOImpl();
        String pathInfo = req.getPathInfo();

        int idProducto = Integer.parseInt(pathInfo.substring(pathInfo.lastIndexOf("/") + 1));

        productoDAO.find(idProducto)
                .ifPresentOrElse((producto) -> {
                    req.setAttribute("fabricantes", fabDAO.getAll());
                    req.setAttribute("producto", producto);
                    forwardToJsp("/WEB-INF/jsp/productos/editar-producto.jsp", req, res);
                },
                        () -> {
                            try {
                                res.sendError(404, "No se ha encontrado el producto");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
    }

    private void devolverJSPCrearProducto(HttpServletRequest req, HttpServletResponse res) {
        FabricanteDAO fabDAO = new FabricanteDAOImpl();

        req.setAttribute("fabricantes", fabDAO.getAll()
                .stream()
                .map(Fabricante::getNombre)
                .toList());

        forwardToJsp("/WEB-INF/jsp/productos/crear-producto.jsp", req, res);
    }

    private void crearProducto(HttpServletRequest req, HttpServletResponse res) {
        ProductoDAO prodDAO = new ProductoDAOImpl();

        String nombre = getCadenaODefault(req, "nombre");
        Double precio = getDoubleODefault(req, "precio");
        String fabricante = getCadenaODefault(req, "fabricante");

        ProductoService.crearProducto(nombre, precio, fabricante)
                .esCorrecto((producto) -> {
                    prodDAO.create(producto);
                    sendRedirectTo("/tienda/productos/", req, res);
                })
                .esIncorrecto((error) -> {
                    try {
                        res.sendError(400, error.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

    }

    private void modificarProducto(HttpServletRequest req, HttpServletResponse res) {
        ProductoDAO prodDAO = new ProductoDAOImpl();

        int codigo = getIntegerODefault(req, "codigo");
        String nombre = getCadenaODefault(req, "nombre");
        Double precio = getDoubleODefault(req, "precio");
        Integer idFabricante = getIntegerODefault(req, "fabricante");

        ProductoService.crearProducto(nombre, precio, idFabricante)
                .esCorrecto((producto) -> {
                    if (codigo > 0) {
                        producto.setIdProducto(codigo);
                        prodDAO.update(producto);
                    }
                    sendRedirectTo("/tienda/productos/", req, res);
                })
                .esIncorrecto((error) -> {
                    try {
                        res.sendError(400, error.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

    }

    private void eliminarProducto(HttpServletRequest req, HttpServletResponse res) {
        ProductoDAO fabDAO = new ProductoDAOImpl();
        int codigo = getIntegerODefault(req, "codigo");

        fabDAO.find(codigo)
                .ifPresentOrElse((p) -> {
                    fabDAO.delete(codigo);
                    sendRedirectTo("/tienda/productos/", req, res);
                },
                        () -> {
                            try {
                                res.sendError(400, "No se ha eliminado el producto");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
    }
}
