import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import static agh.ics.oop.MoveDirection.*;
import static agh.ics.oop.MoveDirection.FORWARD;
import static org.testng.AssertJUnit.*;

public class SimulationEngineTest {
    @Test public void basicDoubleTest() {
        RectangularMap map = new RectangularMap(8, 4);
        Vector2d[] positions = {
                new Vector2d(0, 0),
                new Vector2d(1, 1)
        };
        MoveDirection[] moves = {
                FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, LEFT, FORWARD, BACKWARD};

        IEngine engine = new SimulationEngine(moves, map, positions);

        Animal animal1 = (Animal) map.objectAt(new Vector2d(0, 0));
        Animal animal2 = (Animal) map.objectAt(new Vector2d(1, 1));

        assertNotNull(animal1);
        assertNotNull(animal2);

        engine.run();

        assertTrue(animal1.isAt(new Vector2d(2, 1)));
        assertTrue(animal2.isAt(new Vector2d(2, 3)));

        assertTrue(animal1.isFacing(MapDirection.EAST));
        assertTrue(animal2.isFacing(MapDirection.WEST));

        assertEquals(
                animal1,
                map.objectAt(new Vector2d(2, 1))
        );
        assertEquals(
                animal2,
                map.objectAt(new Vector2d(2, 3))
        );
    }
    @Test public void collisionDoubleTest() {
        RectangularMap map = new RectangularMap(8, 4);
        Vector2d[] positions = {
                new Vector2d(0, 0),
                new Vector2d(3, 0)
        };
        MoveDirection[] moves = {
                RIGHT, LEFT, RIGHT, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, BACKWARD, FORWARD};

        IEngine engine = new SimulationEngine(moves, map, positions);

        Animal animal1 = (Animal) map.objectAt(new Vector2d(0, 0));
        Animal animal2 = (Animal) map.objectAt(new Vector2d(3, 0));

        assertNotNull(animal1);
        assertNotNull(animal2);

        engine.run();

        assertTrue(animal1.isAt(new Vector2d(0, 1)));
        assertTrue(animal2.isAt(new Vector2d(0, 0)));

        assertTrue(animal1.isFacing(MapDirection.SOUTH));
        assertTrue(animal2.isFacing(MapDirection.WEST));

        assertEquals(
                animal1,
                map.objectAt(new Vector2d(0, 1))
        );
        assertEquals(
                animal2,
                map.objectAt(new Vector2d(0, 0))
        );
    }

    @Test public void bigDoubleTest() {
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {
                new Vector2d(2,2),
                new Vector2d(3,4)
        };
        MoveDirection[] moves = {
                FORWARD, BACKWARD, RIGHT, LEFT, FORWARD, FORWARD, RIGHT, RIGHT,
                FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD};

        IEngine engine = new SimulationEngine(moves, map, positions);

        Animal animal1 = (Animal) map.objectAt(positions[0]);
        Animal animal2 = (Animal) map.objectAt(positions[1]);

        assertNotNull(animal1);
        assertNotNull(animal2);

        engine.run();

        assertTrue(animal1.isAt(new Vector2d(2, 0)));
        assertTrue(animal2.isAt(new Vector2d(3, 4)));

        assertTrue(animal1.isFacing(MapDirection.SOUTH));
        assertTrue(animal2.isFacing(MapDirection.NORTH));

        assertEquals(
                animal1,
                map.objectAt(new Vector2d(2, 0))
        );
        assertEquals(
                animal2,
                map.objectAt(new Vector2d(3, 4))
        );

        for(int y=1; y<=3; y++)
            for(int x=0; x<10; x++)
                assertNull(
                        map.objectAt(new Vector2d(x, y))
                );
    }
}
