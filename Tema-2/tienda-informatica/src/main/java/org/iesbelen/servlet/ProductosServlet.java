package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
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
import org.iesbelen.model.Producto;
import org.iesbelen.service.ProductoService;
import org.iesbelen.util.HTTPRequestUtil;
import org.iesbelen.util.ResultadoDeCreacion;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.iesbelen.util.HTTPRequestUtil.*;

@WebServlet(name = "productosServlet", value = "/tienda/productos/*")
public class ProductosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * HTTP Method: GET
     * Paths:
     * /productos/
     * /productos/{id}
     * /productos/editar{id}
     * /productos/crear
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        String pathInfo = request.getPathInfo(); //

        if (pathInfo == null || "/".equals(pathInfo)) {
            ProductoDAO fabDAO = new ProductoDAOImpl();

            //GET
            //	/productos/
            //	/productos
            String nombreFiltro = HTTPRequestUtil.getCadenaODefault(request, "filtrar-por-nombre");

            request.setAttribute("listaProductos", fabDAO.getAll(nombreFiltro));
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/productos.jsp");

        } else {
            // GET
            // 		/productos/{id}
            // 		/productos/{id}/
            // 		/productos/edit/{id}
            // 		/productos/edit/{id}/
            // 		/productos/crear
            // 		/productos/crear/

            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {

                // GET
                // /productos/crear
                FabricanteDAO fabDAO = new FabricanteDAOImpl();

                List<String> listaNombreFabricante = fabDAO.getAll()
                        .stream()
                        .map(Fabricante::getNombre)
                        .toList();

                request.setAttribute("fabricantes", listaNombreFabricante);
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/crear-producto.jsp");

            } else if (pathParts.length == 2) {
                ProductoDAO fabDAO = new ProductoDAOImpl();
                // GET
                // /productos/{id}
                try {
                    request.setAttribute("producto", fabDAO.find(Integer.parseInt(pathParts[1])));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/detalle-producto.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/productos.jsp");
                }

            } else if (pathParts.length == 3 && "editar".equals(pathParts[1])) {
                ProductoDAO prodDAO = new ProductoDAOImpl();
                FabricanteDAO fabDao = new FabricanteDAOImpl();
                // GET
                // /productos/editar/{id}
                try {
                    request.setAttribute("fabricantes", fabDao.getAll());
                    request.setAttribute("producto", prodDAO.find(Integer.parseInt(pathParts[2])));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/editar-producto.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/productos.jsp");
                }


            } else {

                System.out.println("Opción POST no soportada.");
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/productos.jsp");

            }

        }

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String __method__ = request.getParameter("__method__");

        if (__method__ == null) {
            // Crear uno nuevo
            ProductoDAO prodDAO = new ProductoDAOImpl();
            String nombre = getCadenaODefault(request, "nombre");
            Double precio = getDoubleODefault(request, "precio");
            String fabricante = getCadenaODefault(request, "fabricante");

            ResultadoDeCreacion<Producto> prod =
                    ProductoService.crearProducto(nombre, precio, fabricante);

            if (prod.esValido()) prodDAO.create(prod.get());

        } else if ("put".equalsIgnoreCase(__method__)) {
            // Actualizar uno existente
            //Dado que los forms de html sólo soportan method GET y POST utilizo parámetro oculto para indicar la operación de actulización PUT.
            doPut(request, response);

        } else if ("delete".equalsIgnoreCase(__method__)) {
            // Actualizar uno existente
            //Dado que los forms de html sólo soportan method GET y POST utilizo parámetro oculto para indicar la operación de actulización DELETE.
            doDelete(request, response);

        } else {
            System.out.println("Opción POST no soportada.");
        }

        //response.sendRedirect("../../../tienda/productos");
        response.sendRedirect(request.getContextPath() + "/tienda/productos");
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductoDAO prodDAO = new ProductoDAOImpl();

        int codigo = getIntegerODefault(request, "codigo");
        String nombre = getCadenaODefault(request, "nombre");
        Double precio = getDoubleODefault(request, "precio");
        Integer idFabricante = getIntegerODefault(request, "fabricante");

        ResultadoDeCreacion<Producto> producto = ProductoService.crearProducto(nombre, precio, idFabricante);

        if (producto.esValido() && codigo != 0) {
            producto.get().setIdProducto(codigo);
            prodDAO.update(producto.get());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher;
        ProductoDAO fabDAO = new ProductoDAOImpl();
        String codigo = request.getParameter("codigo");

        try {

            int id = Integer.parseInt(codigo);

            fabDAO.delete(id);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }
}
