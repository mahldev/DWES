package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CeldaTest {

    Celda<Integer> c;

    @BeforeEach
    void setUp() {
        c = new Celda<>(1, 1, 5);
    }

    @Test
    void equalsTest() {
        assertEquals(c, new Celda<>(1, 1, 5), "Deberian ser iguales: Mismos valores en fila y columna");
        assertNotEquals(c, new Celda<Integer>(1, 2, 5), "Deberian ser distintas: Diferentes valores columna");
    }
}
