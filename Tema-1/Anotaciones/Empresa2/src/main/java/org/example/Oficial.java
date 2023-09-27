package org.example;

public class Oficial extends Operario {

    private String categoria;

    public Oficial(String dni, String nombre, String apellidos, Integer codigoTaller, String categoria) {
        super(dni, nombre, apellidos, codigoTaller);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return super.toString() + "Oficial [categoria=" + categoria + "]";
    }
}
