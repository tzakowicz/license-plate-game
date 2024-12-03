package net.gilmor.plate.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import net.gilmor.plate.utility.LicensePlateTest;

public class CrossingTest extends LicensePlateTest {

    @Test
    void testCtor() {
        Area first = makeArea("CODE1", "CODE2");
        Area second = makeArea("CODE2", "CODE1");
        Crossing xing = new Crossing(first, second);
        assertEquals(first.getCode(), xing.getStart().getCode());
        assertEquals(second.getCode(), xing.getEnd().getCode());
    }

    @Test
    void testCtorSameAreaTwice() {
        Area first = makeArea("CODE1", "CODE3");
        assertThrows(IllegalArgumentException.class, () -> new Crossing(first, first));
    }

    @Test
    void testCtorNotConnected() {
        Area first = makeArea("CODE1", "CODE3");
        Area second = makeArea("CODE2", "CODE3");
        assertThrows(IllegalArgumentException.class, () -> new Crossing(first, second));
    }

    @Test
    void testCtorNullFrom() {
        Area first = makeArea("CODE1", "CODE3");
        assertThrows(IllegalArgumentException.class, () -> new Crossing(null, first));
    }

    @Test
    void testCtorNullTo() {
        Area first = makeArea("CODE1", "CODE3");
        assertThrows(IllegalArgumentException.class, () -> new Crossing(first, null));
    }

}
