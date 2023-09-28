package org.example;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import org.example.annotations.aDirectivo;
import org.example.annotations.aOficial;
import org.example.annotations.aTecnico;

public class Utils {

    public static Directivo crearDirectivo(aDirectivo d) {
        return new Directivo(
                d.value().dni(),
                d.value().nombre(),
                d.value().apellidos(),
                d.codigoDespacho());
    }

    public static Tecnico crearTecnico(aTecnico t) {
        return new Tecnico(
                t.value().dni(),
                t.value().nombre(),
                t.value().apellidos(),
                t.codigoTaller(),
                t.perfil());
    }

    public static Oficial crearOficial(aOficial o) {
        return new Oficial(
                o.value().dni(),
                o.value().nombre(),
                o.value().apellidos(),
                o.codigoTaller(),
                o.categoria());
    }

    public static <A extends Annotation> List<A> recuperaAnotaciones(Class<A> clase) {
        Class<Empresa> empresaClass = Empresa.class;
        return Arrays.asList(empresaClass.getAnnotationsByType(clase));
    }
}
