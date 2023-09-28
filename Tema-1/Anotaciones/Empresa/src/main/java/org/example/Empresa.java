package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import annotations.aEmpleado;

@aEmpleado(nombre = "1", dni = "1", apellidos = "1", clase = "Directivo", codigoDespacho = 1)
@aEmpleado(nombre = "2", dni = "2", apellidos = "2", clase = "Tecnico", codigoTaller = 2, perfil = "2")
@aEmpleado(nombre = "3", dni = "3", apellidos = "3", clase = "Oficial", codigoTaller = 3, categoria = "3")
public class Empresa {

    private String nombre;
    private List<Empleado> empleados;

    public Empresa(String nombre) {
        this.empleados = new ArrayList<>();
        this.nombre = nombre;
    }

    public static Empresa cargadorDeContexto(String nombre) {
        Empresa empresa = new Empresa(nombre);
        List<aEmpleado> empleados = Utils.recuperaAnotaciones(aEmpleado.class);

        empleados.stream()
                .map(e -> switch (e.clase().toLowerCase()) {
                    case "directivo" -> Utils.crearDirectivo(e);
                    case "tecnico" -> Utils.crearTecnico(e);
                    case "oficial" -> Utils.crearOficial(e);
                    default -> null;
                })
                .filter(Objects::nonNull)
                .forEach(empresa::add);

        return empresa;
    }

    public boolean add(Empleado e) {
        return empleados.add(e);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return empleados.stream()
                .map(Empleado::toString)
                .collect(Collectors.joining("\n", "", empleados.isEmpty() ? "Vacio" : ""));
    }
}
