package agh.ics.oop;

import javafx.application.Platform;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement {
    private MapDirection direction;
    private Vector2d position;
    private final IWorldMap map;

    private List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2, 2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this(map, initialPosition, MapDirection.NORTH);
    }

    public Animal(IWorldMap map, Vector2d initialPosition, MapDirection initialDirection) {
        this.map = map;
        direction = initialDirection;
        position = initialPosition;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return switch (direction) {
            case SOUTH -> "V";
            case WEST -> "<";
            case NORTH -> "^";
            case EAST -> ">";
        };
    }

    @Override
    public String mapDescription() {
        return "A " + position.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public boolean isFacing(MapDirection goal) {
        return this.direction.equals(goal);
    }

    public void move(MoveDirection direction) {
        Vector2d newPosition = position;

        switch (direction) {
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
            case FORWARD -> newPosition = position.add(this.direction.toUnitVector());
            case BACKWARD -> newPosition = position.subtract(this.direction.toUnitVector());
        }

        if (newPosition != position && map.canMoveTo(newPosition)) {
            Vector2d oldPosition = position;
            position = newPosition;
            positionChanged(oldPosition);
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition) {

        for (IPositionChangeObserver observer : observers) {
            Platform.runLater(() -> {
                observer.positionChanged(oldPosition, this);
            });
        }
    }

    @Override
    public String getResource() {
        return switch (this.direction) {
            case SOUTH -> "src/main/resources/down.png";
            case WEST -> "src/main/resources/left.png";
            case NORTH -> "src/main/resources/up.png";
            case EAST -> "src/main/resources/right.png";
        };
    }
}
