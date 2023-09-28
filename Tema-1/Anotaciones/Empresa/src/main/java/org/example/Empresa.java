package org.example;

import java.util.ArrayList;
import java.util.List;

import annotations.Empleado;
import annotations.Empleados;

@Empleados(empleados = {
        @Empleado(nombre = "1", dni = "1", apellidos = "1", clase = "Directivo", codigoDespacho = 1),
        @Empleado(nombre = "2", dni = "2", apellidos = "2", clase = "Tecnico", codigoTaller = 2, perfil = "2"),
        @Empleado(nombre = "3", dni = "3", apellidos = "3", clase = "Oficial", codigoTaller = 3, categoria = "3"),
})
public class Empresa {

    private String nombre;
    private List<org.example.Empleado> empleados;

    public Empresa(String nombre) {
        this.empleados = new ArrayList<>();
        this.nombre = nombre;
    }

    public static Empresa cargadorDeContexto(String nombre) {
        Empresa empresa = new Empresa(nombre);
        Class<Empresa> empresaClass = Empresa.class;
        Empleados empleados = empresaClass.getAnnotation(annotations.Empleados.class);

        if (empleados != null) {
            for (annotations.Empleado e : empleados.empleados()) {
                switch (e.clase().toLowerCase()) {
                    case "directivo" -> empresa.add(
                            new Directivo(
                                    e.dni(),
                                    e.nombre(),
                                    e.apellidos(),
                                    e.codigoDespacho()));
                    case "tecnico" -> empresa.add(
                            new Tecnico(
                                    e.dni(),
                                    e.nombre(),
                                    e.apellidos(),
                                    e.codigoTaller(),
                                    e.perfil()));
                    case "oficial" -> empresa.add(
                            new Oficial(
                                    e.dni(),
                                    e.nombre(),
                                    e.apellidos(),
                                    e.codigoTaller(),
                                    e.categoria()));
                }
            }
        }

        return empresa;
    }

    public boolean add(org.example.Empleado e) {
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
                .map(org.example.Empleado::toString)
                .reduce((s1, s2) -> s1 + "\n" + s2)
                .orElse("Vacio");
    }
}
