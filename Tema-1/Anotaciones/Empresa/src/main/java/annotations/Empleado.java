package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Empleado {

    String clase();

    String dni();

    String nombre();

    String apellidos();

    int codigoDespacho() default 0;

    String perfil() default "";

    int codigoTaller() default 0;

    String categoria() default "";
}
