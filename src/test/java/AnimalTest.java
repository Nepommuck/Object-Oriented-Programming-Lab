import agh.ics.oop.Animal;
import agh.ics.oop.MapDirection;
import static agh.ics.oop.MoveDirection.*;

import agh.ics.oop.MoveDirection;
import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertTrue;

public class AnimalTest {
    @Test public void constructorTest() {
        Animal animal = new Animal();
        assertTrue(animal.isFacing(MapDirection.NORTH));
        assertTrue(animal.isAt(new Vector2d(2, 2)));
    }

    @Test public void rotationTest() {
        Animal animal = new Animal();
        animal.move(RIGHT);
        assertTrue(animal.isFacing(MapDirection.EAST));

        animal.move(RIGHT);
        animal.move(RIGHT);
        assertTrue(animal.isFacing(MapDirection.WEST));

        for (int i=0; i<5; i++)
            animal.move(LEFT);
        assertTrue(animal.isFacing(MapDirection.SOUTH));
    }

    @Test public void forwardTest() {
        Animal animal = new Animal();
        animal.move(FORWARD);
        assertTrue(animal.isAt(new Vector2d(2, 3)));

        for (int i=0; i<4; i++) {
            animal.move(FORWARD);
            assertTrue(animal.isAt(new Vector2d(2, 4)));
        }

        animal = new Animal();
        animal.move(LEFT);
        animal.move(FORWARD);
        assertTrue(animal.isAt(new Vector2d(1, 2)));

        for (int i=0; i<4; i++) {
            animal.move(FORWARD);
            assertTrue(animal.isAt(new Vector2d(0, 2)));
        }

        animal = new Animal();
        animal.move(LEFT);
        animal.move(LEFT);
        animal.move(FORWARD);
        assertTrue(animal.isAt(new Vector2d(2, 1)));

        for (int i=0; i<4; i++) {
            animal.move(FORWARD);
            assertTrue(animal.isAt(new Vector2d(2, 0)));
        }
    }

    @Test public void backwardTest() {
        Animal animal = new Animal();
        animal.move(BACKWARD);
        assertTrue(animal.isAt(new Vector2d(2, 1)));

        for (int i=0; i<4; i++) {
            animal.move(BACKWARD);
            assertTrue(animal.isAt(new Vector2d(2, 0)));
        }

        animal = new Animal();
        animal.move(LEFT);
        animal.move(BACKWARD);
        assertTrue(animal.isAt(new Vector2d(3, 2)));

        for (int i=0; i<4; i++) {
            animal.move(BACKWARD);
            assertTrue(animal.isAt(new Vector2d(4, 2)));
        }

        animal = new Animal();
        animal.move(RIGHT);
        animal.move(BACKWARD);
        assertTrue(animal.isAt(new Vector2d(1, 2)));

        for (int i=0; i<4; i++) {
            animal.move(BACKWARD);
            assertTrue(animal.isAt(new Vector2d(0, 2)));
        }
    }

    @Test public void bigTest() {
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
}