package org.example;

public class Main {

    public static void main(String[] args) {

        Empresa empresa = Empresa.cargadorDeContexto("Mi Empresa");
        System.out.println(empresa);

    }
}
