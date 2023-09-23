package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatrizTest {

    Matriz<Integer> matriz;

    @BeforeEach
    void setUp() {
        matriz = new Matriz<>(2, 2);
    }

    @Test
    @DisplayName("setTest")
    void setTest() {
        assertTrue(matriz.set(1, 1, 5),
                "Deberia insertarse: Fila y columna limite inferior");
        assertTrue(matriz.set(2, 2, 5),
                "Deberia insertar: Fila y columna limite superior");
        assertTrue(matriz.set(1, 2, 5),
                "Deberia insertar: Fila y columna");
        assertFalse(matriz.set(0, 1, 5),
                "No deberia insertar: Fila igual a 0");
        assertFalse(matriz.set(1, 0, 5),
                "No deberia insertar: Columna igual a 0");
        assertFalse(matriz.set(0, 0, 5),
                "No deberia insertar: Fila y columna igual a 0");
        assertFalse(matriz.set(1, 1, 2),
                "No deberia insertarse: Fila y Columna ocupada");
        assertFalse(matriz.set(3, 1, 5),
                "No deberia insertarse: Numero de filas es inferior al que se intenta acceder");
        assertFalse(matriz.set(1, 3, 5),
                "No deberia insertarse: Numero de columnas es inferior al que se intenta acceder");
        assertFalse(matriz.set(3, 3, 5),
                "No deberia insertarse: Numero de filas y columnas es inferior al que se intenta acceder");
        assertFalse(matriz.set(-1, 2, 5),
                "No deberia insertarse: Numero de filas negativo");
        assertFalse(matriz.set(1, -2, 5),
                "No deberia insertarse: Numero de columnas negativo");
        assertFalse(matriz.set(-1, -2, 5),
                "No deberia insertarse: Numero de filas y columnas negativo");
    }

    @Test
    @DisplayName("getTest")
    void get() {
        matriz.set(1, 1, 5);
        assertEquals(5, matriz.get(1, 1), "Deberia recuperar el elemento");
        assertNull(matriz.get(2, 2), "Deberia recuperar null: No se encuentra el elemento");
        assertNull(matriz.get(5, 5), "Deberia recuperar null: Fuera de los limites positivo");
        assertNull(matriz.get(-5, -5), "Deberia recuperar null: Fuera de los limites negativo");
    }

    @Test
    @DisplayName("columnasTest")
    void columnasTest() {
        assertEquals(2, matriz.columnas(), "Deberia recuperar correctamente");
        assertNotEquals(5, matriz.columnas(), "No deberia recuperar correctamente");
    }

    @Test
    @DisplayName("filasTest")
    void filasTest() {
        assertEquals(2, matriz.filas(), "Deberia recuperar correctamente");
        assertNotEquals(5, matriz.filas(), "No deberia recuperar correctamente");
    }

    @Test
    @DisplayName("toStringTest")
    void toStringTest() {
        assertEquals("Vacio", matriz.toString(), "Deberia devolver la cadena Vacio");
        matriz.set(1, 1, 5);
        assertEquals("Fila: 1 - Columna: 1 - Valor: 5\n", matriz.toString(), "Deberia devolver la cadena 5 ");
        matriz.set(1, 2, 2);
        assertEquals("Fila: 1 - Columna: 1 - Valor: 5\nFila: 1 - Columna: 2 - Valor: 2\n",
                matriz.toString(),
                "Deberia devovler la cadena: Fila: 1 - Columna: 1 - Valor: 5\nFila: 1 - Columna: 2 - Valor: 2\n");
        matriz.set(2, 2, 6);
        assertEquals(
                "Fila: 1 - Columna: 1 - Valor: 5\nFila: 1 - Columna: 2 - Valor: 2\nFila: 2 - Columna: 2 - Valor: 6\n",
                matriz.toString(),
                "Deberia devovler la cadena: Fila: 1 - Columna: 1 - Valor: 5\nFila: 1 - Columna: 2 - Valor: 2\nFila: 2 - Columna: 2 - Valor: 6\n");
    }
}
