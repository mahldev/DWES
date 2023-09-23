package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PilaArrayTest {

    PilaArray<Integer> pila;

    @BeforeEach
    void setUp() {
        pila = new PilaArray<>();
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
        assertEquals(5, pila.extraer(), "Deberia extraer un 5");
        assertEquals(2, pila.extraer(), "Deberia extraer un 2");
        assertNull(pila.extraer(), "Deberia extraer un null");
    }

    @Test
    @DisplayName("primeroTest")
    void primeroTest() {
        assertEquals(5, pila.primero(), "Deberia recuperar un 5");
        assertEquals(5, pila.primero(), "Deberia volver a recuperar 5");
        pila.extraer();
        pila.extraer();
        assertNull(pila.primero(), "Deberia recuperar null");
    }

    @Test
    @DisplayName("aniadirTest")
    @RepeatedTest(5)
    void aniadirTest() {
        assertTrue(pila.aniadir(2), "Deberia agregar correctamente");
    }
}
