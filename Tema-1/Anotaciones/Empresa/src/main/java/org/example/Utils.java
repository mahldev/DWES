package org.example;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import annotations.aEmpleado;

public class Utils {

    public static Directivo crearDirectivo(aEmpleado d) {
        return new Directivo(
                d.dni(),
                d.nombre(),
                d.apellidos(),
                d.codigoDespacho());
    }

    public static Tecnico crearTecnico(aEmpleado t) {
        return new Tecnico(
                t.dni(),
                t.nombre(),
                t.apellidos(),
                t.codigoTaller(),
                t.perfil());
    }

    public static Oficial crearOficial(aEmpleado o) {
        return new Oficial(
                o.dni(),
                o.nombre(),
                o.apellidos(),
                o.codigoTaller(),
                o.categoria());
    }

    public static <A extends Annotation> List<A> recuperaAnotaciones(Class<A> clase) {
        Class<Empresa> empresaClass = Empresa.class;
        return Arrays.asList(empresaClass.getAnnotationsByType(clase));
    }
}
