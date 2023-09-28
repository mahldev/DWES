package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Repeatable(aEmpleados.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface aEmpleado {

    String clase();

    String dni();

    String nombre();

    String apellidos();

    int codigoDespacho() default -1;

    String perfil() default "";

    int codigoTaller() default -1;

    String categoria() default "";
}
