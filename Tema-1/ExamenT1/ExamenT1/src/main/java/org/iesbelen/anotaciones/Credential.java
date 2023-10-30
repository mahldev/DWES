package org.iesbelen.anotaciones;

import java.util.Objects;

public class Credential {

    private final String userName;
    private final String password;

    public Credential(String name, String pass) {
        this.userName = name;
        this.password = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credential that = (Credential) o;
        return Objects.equals(userName, that.userName) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }
}
