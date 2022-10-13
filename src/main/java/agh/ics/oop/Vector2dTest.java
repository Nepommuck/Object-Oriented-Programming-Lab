package agh.ics.oop;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.*;


public class Vector2dTest {
    Vector2d v1 = new Vector2d(1, 0);
    Vector2d v2 = new Vector2d(0, 3);
    Vector2d v3 = new Vector2d(1, 3);
    Vector2d v4 = new Vector2d(1, 3);

    @Test
    public void equalsTest() {
        assertEquals(v1, v1);
        assertEquals(v3, v4);
        assertNotEquals(v1, v2);
        assertNotEquals(v1, v3);
        assertNotEquals(v2, v3);
    }

    @Test
    public void toStringTest() {
        assertEquals(v1.toString(), "(1, 0)");
        assertEquals(v2.toString(), "(0, 3)");
        assertEquals(v3.toString(), "(1, 3)");
    }
}