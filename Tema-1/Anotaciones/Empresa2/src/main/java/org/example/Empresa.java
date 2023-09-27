package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.annotations.Directivo;
import org.example.annotations.Empleado;
import org.example.annotations.Empleados;
import org.example.annotations.Oficial;
import org.example.annotations.Tecnico;

@Directivo(value = @Empleado(dni = "1", nombre = "1", apellidos = "1"), codigoDespacho = 1)
@Tecnico(value = @Empleado(dni = "2", nombre = "2", apellidos = "2"), perfil = "2")
@Oficial(value = @Empleado(dni = "3", nombre = "3", apellidos = "3"), categoria = "3")
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

        Directivo directivos = empresaClass.getAnnotations(org.example.annotations.Directivo.class);

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
                .reduce((s1, s2) -> s1 + " " + s2)
                .orElse("Vacio");
    }
}
