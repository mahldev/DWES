package org.iesbelen.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.FabricanteDAO;
import org.iesbelen.dao.FabricanteDAOImpl;
import org.iesbelen.dao.ProductoDAO;
import org.iesbelen.dao.ProductoDAOImpl;
import org.iesbelen.model.Fabricante;
import org.iesbelen.service.ProductoService;
import org.iesbelen.util.HTTPRequestUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static org.iesbelen.util.HTTPRequestUtil.*;

@WebServlet(name = "productosServlet", value = "/tienda/productos/*")
public class ProductosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> rutasGET = new HashMap<>();
    private Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> rutasPOST = new HashMap<>();
    private Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> rutasPUT = new HashMap<>();
    private Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> rutasDELETE = new HashMap<>();

    @Override
    public void init() {
        // GET
        rutasGET.put("/tienda/productos/", this::mostrarProductos);
        rutasGET.put("/tienda/productos/editar/\\d+/?", this::devolverProducto);
        rutasGET.put("/tienda/productos/crear/", this::devolverJSPCrearProducto);

        // POST
        rutasPOST.put("/tienda/productos/", this::crearProducto);

        // PUT
        rutasPUT.put("/tienda/productos/editar/", this::modificarProducto);

        // DELETE
        rutasDELETE.put("/tienda/productos/borrar/", this::eliminarProducto);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        manejarRequest(req, res, rutasGET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String __method__ = req.getParameter("__method__");

        if (__method__ == null)
            manejarRequest(req, res, rutasPOST);

        if ("put".equalsIgnoreCase(__method__))
            doPut(req, res);

        if ("delete".equalsIgnoreCase(__method__))
            doDelete(req, res);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) {
        manejarRequest(req, res, rutasPUT);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) {
        manejarRequest(req, res, rutasDELETE);
    }

    private void mostrarProductos(HttpServletRequest req, HttpServletResponse res) {
        ProductoDAO fabDAO = new ProductoDAOImpl();

        String searchFormHidden = req.getParameter("searchFormHidden");
        String nombreFiltro = HTTPRequestUtil.getCadenaODefault(req, "filtrar-por-nombre");

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
