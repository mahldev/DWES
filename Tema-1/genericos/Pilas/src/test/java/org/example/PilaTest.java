package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

public class PilaTest {

    Pila<Integer> pila;

    @BeforeEach
    void setUp() {
        pila = new Pila<>();
        pila.aniadir(2);
        pila.aniadir(5);
    }

    @Test
    @DisplayName("estaVaciaTest")
    void estaVaciaTest() {
        assertFalse(pila.estaVacia(), "No deberia estar vacia");
        pila.extraer();
        pila.extraer();
        assertTrue(pila.estaVacia(), "Deberia estar vacia");
    }

    @Test
    @DisplayName("extraerTest")
    void extraerTest() {

        NoSuchElementException thrown;

        assertEquals(5, pila.extraer(), "Deberia extraer un 5");
        assertEquals(2, pila.extraer(), "Deberia extraer un 2");

        thrown = assertThrows(NoSuchElementException.class,
                () -> {
                    pila.extraer();
                }, "Se espera un NoSuchElementException");
        assertEquals("", thrown.getMessage());
    }

    @Test
    @DisplayName("primeroTest")
    void primeroTest() {
        assertEquals(5, pila.primero(), "Deberia recuperar un 5");
        assertEquals(5, pila.primero(), "Deberia volver a recuperar 5");
        pila.extraer();
        pila.extraer();
    }

    @Test
    @DisplayName("aniadirTest")
    @RepeatedTest(5)
    void aniadirTest() {
        assertTrue(pila.aniadir(2), "Deberia agregar correctamente");
    }
}
