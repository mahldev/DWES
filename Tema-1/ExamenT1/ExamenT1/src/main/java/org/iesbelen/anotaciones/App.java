package org.iesbelen.anotaciones;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Login login = Login.cargadorDeContexto();

        try {
            System.out.println(login.login(
                    Login.createCredentialBySystemIn())
                    ? "ACCESO CONCEDIDO"
                    : "ACCESO DENEGADO");
        } catch (NoSuchAlgorithmException err) {
            System.out.println("Ha ocurrido un error" + err.getMessage());
        }
    }
}
