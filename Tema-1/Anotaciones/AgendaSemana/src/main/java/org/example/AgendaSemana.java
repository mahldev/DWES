package org.example;

import java.util.*;
import java.util.stream.Collectors;

import org.example.annotations.aTarea;

@aTarea(titulo = "1", descripcion = "1", diaDeLaSemana = "1", hora = "1")
@aTarea(titulo = "2", descripcion = "2", diaDeLaSemana = "2", hora = "2")
@aTarea(titulo = "3", descripcion = "3", diaDeLaSemana = "3", hora = "3")
public class AgendaSemana {

    List<Tarea> tareas;

    public AgendaSemana() {
        this.tareas = new ArrayList<>();
    }

    public boolean add(Tarea tarea) {
        return tareas.add(tarea);
    }

    public Tarea get(String titulo) {
        Optional<Tarea> tarea = tareas.stream()
                .filter(t -> Objects.equals(t.getTitulo(), titulo))
                .findFirst();

        return tarea.orElse(null);
    }

    public static AgendaSemana cargadorAgendaSemana() {
        AgendaSemana agenda = new AgendaSemana();
        List<aTarea> tareas = Arrays.asList(
                AgendaSemana.class.getAnnotationsByType(aTarea.class));

        tareas.stream()
                .map(AgendaSemana::crearNuevaTarea)
                .forEach(agenda::add);

        return agenda;
    }

    private static Tarea crearNuevaTarea(aTarea t) {
        return new Tarea(
                t.titulo(),
                t.descripcion(),
                t.diaDeLaSemana(),
                t.hora());
    }

    @Override
    public String toString() {
        return tareas.stream()
                .map(Tarea::toString)
                .collect(Collectors.joining("\n", "", tareas.isEmpty() ? "Vacio" : ""));
    }
}
