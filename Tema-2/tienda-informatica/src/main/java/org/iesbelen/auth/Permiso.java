package org.iesbelen.auth;

import java.util.Set;

public class Permiso {

    Set<String> rolesRequeridos;

    public Permiso(Set<String> rolesRequeridos) {
        this.rolesRequeridos = rolesRequeridos;
    }

    public Set<String> getRolesRequeridos() {
        return rolesRequeridos;
    }
}
