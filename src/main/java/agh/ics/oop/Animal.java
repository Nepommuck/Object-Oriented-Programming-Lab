package agh.ics.oop;

import java.util.Objects;

import static java.lang.System.out;

public class Animal {
    private MapDirection direction;
    private Vector2d position;

    private Vector2d minPosition;
    private Vector2d maxPosition;

    public Animal() {
        direction = MapDirection.NORTH;
        position = new Vector2d(2, 2);

        minPosition = new Vector2d(0, 0);
        maxPosition = new Vector2d(4, 4);
    }

    @Override
    public String toString() {
        return "Zwierzę na pozycji "+position.toString()+" w kierunku "+direction;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        Vector2d newPosition = position.copy();

        switch (direction) {
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
            case FORWARD -> newPosition = position.add(this.direction.toUnitVector());
            case BACKWARD -> newPosition = position.subtract(this.direction.toUnitVector());
        }
        if (newPosition.precedes(maxPosition) && newPosition.follows(minPosition))
            position = newPosition;
    }


}
