package org.example;

public class Directivo extends Empleado {

    private Integer codigoDespacho;

    public Directivo(String dni, String nombre, String apellidos, Integer codigoDespacho) {
        super(dni, nombre, apellidos);
        this.codigoDespacho = codigoDespacho;
    }

    public Integer getCodigoDespacho() {
        return codigoDespacho;
    }

    public void setCodigoDespacho(Integer codigoDespacho) {
        this.codigoDespacho = codigoDespacho;
    }

    @Override
    public String toString() {
        return super.toString() + " - Codigo despacho: " + codigoDespacho;
    }

}
