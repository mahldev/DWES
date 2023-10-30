package org.iesbelen.genericos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BagTest {

    Bag<Integer> bag;

    @BeforeEach
    void setUp() {
        bag = new Bag<>();
    }

    @RepeatedTest(5)
    void simpleAddTest() {
        assertEquals(0, bag.size());
        assertTrue(bag.add(1));
        assertEquals(1, bag.size());
    }

    @RepeatedTest(5)
    void multipleAddTest() {
        assertEquals(0, bag.size());
        assertTrue(bag.add(1, 5));
        assertEquals(5, bag.size());
    }

    @Test
    void simpleRemoveTest() {
        bag.add(1);
        assertFalse(bag.remove(2));
        assertTrue(bag.remove(1));
        assertEquals(0, bag.size());
        assertFalse(bag.remove(1));
    }

    @Test
    void multimpleRemoveTest() {
        bag.add(1, 5);
        assertFalse(bag.remove(2, 5));
        assertTrue(bag.remove(1, 4));
        assertEquals(1, bag.size());

        bag.remove(1);

        bag.add(1, 10);
        assertTrue(bag.remove(1, 11));
        assertEquals(0, bag.size());
    }

    @Test
    void sizeTest() {
        assertEquals(0, bag.size());

        bag.add(1, 5);
        assertEquals(5, bag.size());
    }

    @Test
    void uniqueSetTest() {
        assertEquals(Set.of(), bag.uniqueSet());

        bag.add(1, 5);
        bag.add(2, 5);
        bag.add(3, 5);

        assertEquals(Set.of(1, 2, 3), bag.uniqueSet());
    }

    @Test
    void toStringTest() {
        assertEquals("No hay elementos", bag.toString());

        bag.add(1, 2);

        assertEquals("1\n1", bag.toString());
    }
}
