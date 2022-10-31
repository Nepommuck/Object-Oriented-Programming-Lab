package agh.ics.oop;

public class Animal {
    private MapDirection direction;
    private Vector2d position;

    private final static  Vector2d minPosition = new Vector2d(0, 0);
    private final static Vector2d maxPosition = new Vector2d(4, 4);

    private IWorldMap map;

    public Animal() {
        direction = MapDirection.NORTH;
        position = new Vector2d(2, 2);
    }

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2, 2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        direction = MapDirection.NORTH;
        position = initialPosition;
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

    public String oldToString() {
        return "Zwierzę na pozycji "+position.toString()+" w kierunku "+direction;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public boolean isFacing(MapDirection goal) {
        return this.direction.equals(goal);
    }

    public void oldMove(MoveDirection direction) {
        Vector2d newPosition = position;

        switch (direction) {
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
            case FORWARD -> newPosition = position.add(this.direction.toUnitVector());
            case BACKWARD -> newPosition = position.subtract(this.direction.toUnitVector());
        }
        if (newPosition.precedes(maxPosition) && newPosition.follows(minPosition))
            position = newPosition;
    }

    public void move(MoveDirection direction) {
        Vector2d newPosition = position;

        switch (direction) {
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
            case FORWARD -> newPosition = position.add(this.direction.toUnitVector());
            case BACKWARD -> newPosition = position.subtract(this.direction.toUnitVector());
        }

        if (newPosition != position && map.canMoveTo(newPosition))
            position = newPosition;
    }
}
