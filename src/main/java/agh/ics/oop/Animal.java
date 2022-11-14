package agh.ics.oop;

public class Animal implements IMapElement {

    private MapDirection direction;
    private Vector2d position;
    private final IWorldMap map;

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

    public String oldToString() {
        return "Zwierzę na pozycji "+position.toString()+" w kierunku "+direction;
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

        if (newPosition != position && map.canMoveTo(newPosition))
            position = newPosition;
    }
}
