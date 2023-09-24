package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ListaOrdenadaTest {

    ListaOrdenada<Integer> list;

    @BeforeEach
    void setUp() {
        list = new ListaOrdenada<>();
    }

    @Test
    @DisplayName("getIndexTest")
    void getIndexTest() {
        assertEquals(0, list.getIndex(2), "Deberia devolver el indice 0");
        list.add(2);
        assertEquals(1, list.getIndex(5), "Deberia devolver el indice 1");
        list.add(5);
        assertEquals(0, list.getIndex(1), "Deberia devolver el indice 0");
        list.add(1);
        assertEquals(0, list.getIndex(-2), "Deberia devolver el indice 0");
        list.add(-2);
        assertEquals(0, list.getIndex(-532), "Deberia devolver el indice 0");
        list.add(-532);
        assertEquals(1, list.getIndex(-2), "Deberia devolver el indice 1");
        list.add(-2);
        assertEquals(0, list.getIndex(-532), "Deberia devolver el indice 0");
        list.add(-532);
        assertEquals(6, list.getIndex(5), "Deberia devolver el indice 6");
        list.add(5);
        assertEquals(6, list.getIndex(3), "Deberia devolver el indice 6");
        list.add(3);
    }
}
