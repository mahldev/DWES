package org.iesbelen.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.iesbelen.model.Usuario;

public class GestorDePermisos {

    private static Map<String, Permiso> permisos = new HashMap<>();

    static {
        permisos.put("/tienda/fabricantes/editar/\\d+/?", new Permiso(Set.of("Administrador")));
        permisos.put("/tienda/fabricantes/crear/", new Permiso(Set.of("Administrador")));
        permisos.put("/tienda/fabricantes/borrar/", new Permiso(Set.of("Administrador")));

        permisos.put("/tienda/productos/editar/\\d+/?", new Permiso(Set.of("Administrador", "Vendedor")));
        permisos.put("/tienda/productos/crear/", new Permiso(Set.of("Administrador", "Vendedor")));
        permisos.put("/tienda/productos/borrar/", new Permiso(Set.of("Administrador", "Vendedor")));

        permisos.put("/tienda/usuarios/", new Permiso(Set.of("Administrador")));
        permisos.put("/tienda/usuarios/editar/\\d+/?", new Permiso(Set.of("Administrador")));
        permisos.put("/tienda/usuarios/crear/", new Permiso(Set.of("Administrador")));
        permisos.put("/tienda/usuarios/borrar/", new Permiso(Set.of("Administrador")));
    }

    public static void gestiona(Runnable tienePermiso, Runnable noTienePermiso, Usuario usuario, String url) {
        Optional<Permiso> permiso = permisos.entrySet().stream()
                .filter(entry -> url.matches(entry.getKey()))
                .findFirst()
                .map(Map.Entry::getValue);

        permiso.ifPresentOrElse(p -> {
            if (p.getRolesRequeridos().contains(usuario.getRol())) {
                tienePermiso.run();
            } else {
                noTienePermiso.run();
            }
        },
                tienePermiso::run);
    }
}
