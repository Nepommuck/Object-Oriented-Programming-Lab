import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.*;


public class Vector2dTest {
    Vector2d v1 = new Vector2d(1, 0);
    Vector2d v2 = new Vector2d(0, 3);
    Vector2d v3 = new Vector2d(1, 3);
    Vector2d v4 = new Vector2d(1, 3);
    Vector2d v5 = new Vector2d(4, 5);

    @Test public void equalsTest() {
        assertEquals(v1, v1);
        assertEquals(v3, v4);

        assertNotEquals(v1, v2);
        assertNotEquals(v1, v3);
        assertNotEquals(v2, v3);
        assertNotEquals(v2, null);
    }
    @Test public void toStringTest() {
        assertEquals(v1.toString(), "(1, 0)");
        assertEquals(v2.toString(), "(0, 3)");
        assertEquals(v3.toString(), "(1, 3)");
    }
    @Test public void precedesTest() {
        assertTrue(v1.precedes(v3));
        assertTrue(v3.precedes(v5));
        assertTrue(v1.precedes(v1));

        assertFalse(v3.precedes(v2));
        assertFalse(v5.precedes(v1));
    }
    @Test public void followsTest() {
        assertTrue(v3.follows(v2));
        assertTrue(v5.follows(v1));
        assertTrue(v1.follows(v1));

        assertFalse(v1.follows(v3));
        assertFalse(v3.follows(v5));
    }
    @Test public void upperRightTest() {
        assertEquals(v1.upperRight(v2), v3);
        assertEquals(v2.upperRight(v3), v3);
        assertEquals(v4.upperRight(v5), v5);
        assertEquals(v5.upperRight(v4), v5);
    }
    @Test public void lowerLeftTest() {
        assertEquals(v1.lowerLeft(v4), v1);
        assertEquals(v2.lowerLeft(v3), v2);
        assertEquals(v1.lowerLeft(v5), v1);
    }

    @Test public void hashCodeTest() {
        assertEquals(
                new Vector2d(1, 3).hashCode(),
                new Vector2d(1, 3).hashCode()
        );
        assertEquals(
                new Vector2d(5, 12).hashCode(),
                new Vector2d(5, 12).hashCode()
        );
        assertEquals(
                new Vector2d(0, -8).hashCode(),
                new Vector2d(0, -8).hashCode()
        );
    }
    Vector2d a = new Vector2d(1, 0);
    Vector2d b = new Vector2d(-2, 8);
    Vector2d c = new Vector2d(14, -7);

    Vector2d negA = new Vector2d(-1, 0);
    Vector2d negB = new Vector2d(2, -8);
    Vector2d negC = new Vector2d(-14, 7);

    Vector2d aPlusB = new Vector2d(-1, 8);
    Vector2d aMinusB = new Vector2d(3, -8);
    Vector2d bMinusA = new Vector2d(-3, 8);

    Vector2d aPlusC = new Vector2d(15, -7);
    Vector2d aMinusC = new Vector2d(-13, 7);
    Vector2d cMinusA = new Vector2d(13, -7);

    Vector2d bPlusC = new Vector2d(12, 1);
    Vector2d bMinusC = new Vector2d(-16, 15);
    Vector2d cMinusB = new Vector2d(16, -15);
    @Test public void addTest() {
        assertEquals(a.add(b), aPlusB);
        assertEquals(a.add(negB), aMinusB);
        assertEquals(b.add(negA), bMinusA);

        assertEquals(a.add(c), aPlusC);
        assertEquals(a.add(negC), aMinusC);
        assertEquals(c.add(negA), cMinusA);

        assertEquals(b.add(c), bPlusC);
        assertEquals(b.add(negC), bMinusC);
        assertEquals(c.add(negB), cMinusB);
    }
    @Test public void subtractTest() {
        assertEquals(a.subtract(b), aMinusB);
        assertEquals(a.subtract(negB), aPlusB);
        assertEquals(b.subtract(a), bMinusA);

        assertEquals(a.subtract(c), aMinusC);
        assertEquals(a.subtract(negC), aPlusC);
        assertEquals(c.subtract(a), cMinusA);

        assertEquals(b.subtract(c), bMinusC);
        assertEquals(b.subtract(negC), bPlusC);
        assertEquals(c.subtract(a), cMinusA);
    }
    @Test public void oppositeTest() {
        assertEquals(a.opposite(), negA);
        assertEquals(b.opposite(), negB);
        assertEquals(c.opposite(), negC);

        assertEquals(aMinusB.opposite(), bMinusA);
        assertEquals(aMinusC.opposite(), cMinusA);
        assertEquals(bMinusC.opposite(), cMinusB);
    }
}