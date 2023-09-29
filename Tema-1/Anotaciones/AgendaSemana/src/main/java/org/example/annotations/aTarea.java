package org.example.annotations;

import java.lang.annotation.*;

@Repeatable(aTareas.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface aTarea {

    String titulo();

    String descripcion();

    String diaDeLaSemana();

    String hora();
}
