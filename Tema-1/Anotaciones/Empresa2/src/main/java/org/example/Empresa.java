package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.example.annotations.Directivo;
import org.example.annotations.Empleado;
import org.example.annotations.Oficial;
import org.example.annotations.Tecnico;

@Directivo(value = @Empleado(dni = "1", nombre = "1", apellidos = "1"), codigoDespacho = 1)
@Tecnico(value = @Empleado(dni = "2", nombre = "2", apellidos = "2"), codigoTaller = 2, perfil = "2")
@Oficial(value = @Empleado(dni = "3", nombre = "3", apellidos = "3"), codigoTaller = 3, categoria = "3")
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

        List<org.example.annotations.Directivo> directivos = Arrays.asList(empresaClass
                .getAnnotationsByType(org.example.annotations.Directivo.class));
        List<org.example.annotations.Tecnico> tecnicos = Arrays.asList(empresaClass
                .getAnnotationsByType(org.example.annotations.Tecnico.class));
        List<org.example.annotations.Oficial> oficiales = Arrays.asList(empresaClass
                .getAnnotationsByType(org.example.annotations.Oficial.class));

        directivos.stream()
                .map(d -> new org.example.Directivo(
                        d.value().dni(),
                        d.value().nombre(),
                        d.value().apellidos(),
                        d.codigoDespacho()))
                .forEach(d -> empresa.add(d));

        tecnicos.stream()
                .map(t -> new org.example.Tecnico(
                        t.value().dni(),
                        t.value().nombre(),
                        t.value().apellidos(),
                        t.codigoTaller(),
                        t.perfil()))
                .forEach(t -> empresa.add(t));

        oficiales.stream()
                .map(o -> new org.example.Oficial(
                        o.value().dni(),
                        o.value().nombre(),
                        o.value().apellidos(),
                        o.codigoTaller(),
                        o.categoria()))
                .forEach(o -> empresa.add(o));
        return empresa;
    }

    public <T extends org.example.Empleado> boolean add(T e) {
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
                .reduce((s1, s2) -> s1 + " " + s2)
                .orElse("Vacio");
    }
}
