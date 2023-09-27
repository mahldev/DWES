package org.example;

public class Operario extends Empleado {

    private Integer codigoTaller;

    public Operario(String dni, String nombre, String apellidos, Integer codigoTaller) {
        super(dni, nombre, apellidos);
        this.codigoTaller = codigoTaller;
    }

    public Integer getCodigoTaller() {
        return codigoTaller;
    }

    public void setCodigoTaller(Integer codigoTaller) {
        this.codigoTaller = codigoTaller;
    }

    @Override
    public String toString() {
        return super.toString() + " Operario [codigoTaller=" + codigoTaller + "]";
    }
}
