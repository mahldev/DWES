package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OperableTest {

    Operable<Integer> operable = new Operable<Integer>() {

        private Integer valor = 5;

        @Override
        public Integer suma(Integer o) {
            return this.valor + o;
        }

        @Override
        public Integer resta(Integer o) {
            return this.valor - o;
        }

        @Override
        public Integer multiplicacion(Integer o) {
            return this.valor * o;
        }

        @Override
        public Integer division(Integer o) {
            return this.valor / o;
        }
    };

    @Test
    @DisplayName("sumaTest")
    void sumaTest() {
        assertEquals(10, operable.suma(5));
        assertNotEquals(5, operable.suma(5));
    }

    @Test
    @DisplayName("restaTest")
    void restaTest() {
        assertEquals(0, operable.resta(5));
        assertNotEquals(5, operable.resta(5));
    }

    @Test
    @DisplayName("multiplicacionTest")
    void multiplicacionTest() {
        assertEquals(25, operable.multiplicacion(5));
        assertNotEquals(10, operable.multiplicacion(5));
    }

    @Test
    @DisplayName("divisionTest")
    void divisionTest() {
        assertEquals(1, operable.division(5));
        assertNotEquals(10, operable.division(5));
    }
}
