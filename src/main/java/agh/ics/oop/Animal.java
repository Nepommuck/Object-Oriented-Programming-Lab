package agh.ics.oop;

public class Animal {
    private MapDirection direction;

//    The theoretical one (before making a move)
    private Vector2d position;
//    Actual position visible on a map
    private Vector2d actualPosition;

    private final static  Vector2d minPosition = new Vector2d(0, 0);
    private final static Vector2d maxPosition = new Vector2d(4, 4);

    private final IWorldMap map;

    public Animal() {
        map = new RectangularMap(5, 5);
        direction = MapDirection.NORTH;
        position = new Vector2d(2, 2);
    }

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

    protected Vector2d getPosition() {
        return position;
    }

    protected Vector2d getActualPosition() {
        return actualPosition;
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

//    Used by map object to update position on the map
    boolean updatePosition() {
        if (position == actualPosition)
            return false;
        actualPosition = position;
        return true;
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

        if (newPosition != position && map.canMoveTo(newPosition)) {
            position = newPosition;
            map.place(this);
        }
    }
}
