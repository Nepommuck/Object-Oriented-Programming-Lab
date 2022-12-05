package agh.ics.oop;

public class Grass implements IMapElement {
    private final Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public String mapDescription() {
        return "Grass";
    }

    @Override
    public String getResource() {
        return "src/main/resources/grass.png";
    }
}
