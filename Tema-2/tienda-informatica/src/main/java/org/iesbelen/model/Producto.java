package org.iesbelen.model;

import org.iesbelen.util.ResultadoDeValidacion;

public class Producto {

    private int idProducto;
    private String nombre;
    private double precio;
    private int codigo_fabricante;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCodigo_fabricante() {
        return codigo_fabricante;
    }

    public void setCodigo_fabricante(int codigo_fabricante) {
        this.codigo_fabricante = codigo_fabricante;
    }

    public static ResultadoDeValidacion validar(String nombre,
            Double precio) {
        ResultadoDeValidacion validaciones = new ResultadoDeValidacion();

        if (nombre == null || nombre.trim().isBlank()) {
            validaciones.addError("nombre", "El nombre del producto no puede estar vacío.");
        }

        else if (nombre.length() < 3 || nombre.length() > 50) {
            validaciones.addError("nombre", "El nombre del producto debe tener entre 3 y 50 caracteres.");
        }

        if (precio <= 0) {
            validaciones.addError("precio", "El precio del producto debe ser mayor que 0.");
        }

        if (precio > 10000) {
            validaciones.addError("precio", "El precio del producto no puede exceder los 10000.");
        }

        return validaciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Producto))
            return false;

        Producto producto = (Producto) o;

        return getIdProducto() == producto.getIdProducto();
    }

    @Override
    public int hashCode() {
        return getIdProducto();
    }
}
