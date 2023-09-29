package org.example;

public class Tarea {

    private String titulo;
    private String descripcion;
    private String diaDeLaSemana;
    private String hora;

    public Tarea(String titulo, String descripcion, String diaDeLaSemana, String hora) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.diaDeLaSemana = diaDeLaSemana;
        this.hora = hora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDiaDeLaSemana() {
        return diaDeLaSemana;
    }

    public void setDiaDeLaSemana(String diaDeLaSemana) {
        this.diaDeLaSemana = diaDeLaSemana;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;

    }

    @Override
    public String toString() {
        return String.format("Titulo: %s%nDescripcion: %s%nDia de la semana: %s%nHora: %s%n________________", titulo,
                descripcion,
                diaDeLaSemana, hora);
    }
}