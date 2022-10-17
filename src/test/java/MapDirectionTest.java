import agh.ics.oop.MapDirection;
import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;


public class MapDirectionTest  {

    @Test
    public void nextTest() {
        assertEquals(MapDirection.NORTH.next(), MapDirection.EAST);
        assertEquals(MapDirection.EAST.next(), MapDirection.SOUTH);
        assertEquals(MapDirection.SOUTH.next(), MapDirection.WEST);
        assertEquals(MapDirection.WEST.next(), MapDirection.NORTH);
    }
    @Test
    public void previousTest() {
        assertEquals(MapDirection.NORTH, MapDirection.EAST.previous());
        assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous());
        assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous());
        assertEquals(MapDirection.WEST, MapDirection.NORTH.previous());
    }

    @Test
    public void toUnitVectorTest() {
        assertEquals(MapDirection.NORTH.toUnitVector(), new Vector2d(0, 1));
        assertEquals(MapDirection.SOUTH.toUnitVector(), new Vector2d(0, -1));
        assertEquals(MapDirection.WEST.toUnitVector(), new Vector2d(-1, 0));
        assertEquals(MapDirection.EAST.toUnitVector(), new Vector2d(1, 0));
    }
}
