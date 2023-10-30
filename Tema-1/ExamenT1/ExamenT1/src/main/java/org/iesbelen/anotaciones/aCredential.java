package org.iesbelen.anotaciones;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(aCredentials.class)
public @interface aCredential {
    String usuario();

    String hashPasswd();
}
