import agh.ics.oop.*;

import static agh.ics.oop.MoveDirection.*;
import static org.testng.AssertJUnit.*;

import org.junit.jupiter.api.Test;

public class AnimalTest {
    @Test
    public void constructorTest() {
        Animal animal = new Animal();
        assertTrue(animal.isFacing(MapDirection.NORTH));
        assertTrue(animal.isAt(new Vector2d(2, 2)));
    }

    @Test
    public void rotationTest() {
        Animal animal = new Animal();
        animal.move(RIGHT);
        assertTrue(animal.isFacing(MapDirection.EAST));

        animal.move(RIGHT);
        animal.move(RIGHT);
        assertTrue(animal.isFacing(MapDirection.WEST));

        for (int i = 0; i < 5; i++)
            animal.move(LEFT);
        assertTrue(animal.isFacing(MapDirection.SOUTH));
    }

    @Test
    public void forwardTest() {
        Animal animal = new Animal();
        animal.move(FORWARD);
        assertTrue(animal.isAt(new Vector2d(2, 3)));

        for (int i = 0; i < 4; i++) {
            animal.move(FORWARD);
            assertTrue(animal.isAt(new Vector2d(2, 4)));
        }

        animal = new Animal();
        animal.move(LEFT);
        animal.move(FORWARD);
        assertTrue(animal.isAt(new Vector2d(1, 2)));

        for (int i = 0; i < 4; i++) {
            animal.move(FORWARD);
            assertTrue(animal.isAt(new Vector2d(0, 2)));
        }

        animal = new Animal();
        animal.move(LEFT);
        animal.move(LEFT);
        animal.move(FORWARD);
        assertTrue(animal.isAt(new Vector2d(2, 1)));

        for (int i = 0; i < 4; i++) {
            animal.move(FORWARD);
            assertTrue(animal.isAt(new Vector2d(2, 0)));
        }
    }

    @Test
    public void backwardTest() {
        Animal animal = new Animal();
        animal.move(BACKWARD);
        assertTrue(animal.isAt(new Vector2d(2, 1)));

        for (int i = 0; i < 4; i++) {
            animal.move(BACKWARD);
            assertTrue(animal.isAt(new Vector2d(2, 0)));
        }

        animal = new Animal();
        animal.move(LEFT);
        animal.move(BACKWARD);
        assertTrue(animal.isAt(new Vector2d(3, 2)));

        for (int i = 0; i < 4; i++) {
            animal.move(BACKWARD);
            assertTrue(animal.isAt(new Vector2d(4, 2)));
        }

        animal = new Animal();
        animal.move(RIGHT);
        animal.move(BACKWARD);
        assertTrue(animal.isAt(new Vector2d(1, 2)));

        for (int i = 0; i < 4; i++) {
            animal.move(BACKWARD);
            assertTrue(animal.isAt(new Vector2d(0, 2)));
        }
    }

    @Test
    public void bigSingleTest() {
        Animal animal1 = new Animal();
        MoveDirection[] moves1 = {
                FORWARD, FORWARD, RIGHT, BACKWARD, BACKWARD, RIGHT, FORWARD, LEFT, FORWARD};
        for (MoveDirection move : moves1) {
            animal1.move(move);
        }
        assertTrue(animal1.isAt(new Vector2d(1, 3)));
        assertTrue(animal1.isFacing(MapDirection.EAST));

        Animal animal2 = new Animal();
        MoveDirection[] moves2 = {
                RIGHT, FORWARD, FORWARD, FORWARD, FORWARD, RIGHT, BACKWARD, BACKWARD};
        for (MoveDirection move : moves2) {
            animal2.move(move);
        }
        assertTrue(animal2.isAt(new Vector2d(4, 4)));
        assertTrue(animal2.isFacing(MapDirection.SOUTH));

        Animal animal3 = new Animal();
        MoveDirection[] moves3 = {
                LEFT, LEFT, FORWARD, FORWARD, BACKWARD, RIGHT, BACKWARD, BACKWARD, BACKWARD,
                RIGHT, FORWARD, FORWARD, LEFT, FORWARD, FORWARD, RIGHT, BACKWARD};
        for (MoveDirection move : moves3) {
            animal3.move(move);
        }
        assertTrue(animal3.isAt(new Vector2d(2, 2)));
        assertTrue(animal3.isFacing(MapDirection.NORTH));
    }

    @Test public void basicDoubleTest() {
        RectangularMap map = new RectangularMap(8, 4);
        Vector2d[] positions = {
                new Vector2d(0, 0),
                new Vector2d(1, 1)
        };
        MoveDirection[] moves = {
                FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, LEFT, FORWARD, BACKWARD};

        IEngine engine = new SimulationEngine(moves, map, positions);
        engine.run();

        Animal animal1 = (Animal) map.objectAt(new Vector2d(2, 1));
        Animal animal2 = (Animal) map.objectAt(new Vector2d(2, 3));

        assertNotNull(animal1);
        assertTrue(animal1.isFacing(MapDirection.EAST));
        assertNotNull(animal2);
        assertTrue(animal2.isFacing(MapDirection.WEST));
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
        engine.run();

        Animal animal1 = (Animal) map.objectAt(new Vector2d(0, 1));
        Animal animal2 = (Animal) map.objectAt(new Vector2d(0, 0));

        assertNotNull(animal1);
        assertTrue(animal1.isFacing(MapDirection.SOUTH));
        assertNotNull(animal2);
        assertTrue(animal2.isFacing(MapDirection.WEST));
    }

    @Test public void bigDoubleTest() {
        MoveDirection[] directions = {
                FORWARD, BACKWARD, RIGHT, LEFT, FORWARD, FORWARD, RIGHT, RIGHT,
                FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD};

        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Animal animal1 = (Animal) map.objectAt(new Vector2d(2, 0));
        Animal animal2 = (Animal) map.objectAt(new Vector2d(3, 4));

        assertNotNull(animal1);
        assertTrue(animal1.isFacing(MapDirection.SOUTH));
        assertNotNull(animal2);
        assertTrue(animal2.isFacing(MapDirection.NORTH));

        for(int y=1; y<=3; y++)
            for(int x=0; x<10; x++)
                assertNull(
                        map.objectAt(new Vector2d(x, y))
                );
    }
}
