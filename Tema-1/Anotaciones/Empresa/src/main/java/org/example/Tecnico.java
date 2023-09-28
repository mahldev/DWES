package org.example;

public class Tecnico extends Operario {

    private String perfil;

    public Tecnico(String dni, String nombre, String apellidos, Integer codigoTaller, String perfil) {
        super(dni, nombre, apellidos, codigoTaller);
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return super.toString() + " - Perfil: " + perfil;
    }

}
