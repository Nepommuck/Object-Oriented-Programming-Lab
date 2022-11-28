package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class GrassField extends AbstractWorldMap{

    private final int numberOfGrass;
    private Map<Vector2d, Grass> grassLocation = new HashMap<>();
    private MapBoundary mapBoundary = new MapBoundary();


    public GrassField(int numberOfGrass) {
        this.numberOfGrass = numberOfGrass;

//        Losowanie trawy
        for (int i=0; i<numberOfGrass; i++)
            placeGrass();
    }

    @Override
    public Vector2d getUpperRight() {
        return mapBoundary.getUpperRight();
    }

    private void placeGrass() {
        int mx_ind = (int) Math.sqrt(10 * numberOfGrass);
        while (true) {
            int x = ThreadLocalRandom.current().nextInt(0, mx_ind + 1);
            int y = ThreadLocalRandom.current().nextInt(0, mx_ind + 1);

            if(!isOccupied(new Vector2d(x, y))) {
                Vector2d grassPosition = new Vector2d(x, y);
                Grass newGrass = new Grass(grassPosition);
                grassLocation.put(grassPosition, newGrass);
                mapBoundary.add(grassPosition, newGrass);

                return;
            }
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object object = super.objectAt(position);
        if(object != null)
            return object;
        return grassLocation.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(position.follows(getLowerLeft()))
            return !isOccupied(position) || objectAt(position) instanceof Grass;
        return false;
    }

    // Movement of grass
    @Override
    public boolean place(Animal animal) {

        if(super.place(animal)) {
            if (grassLocation.containsKey(animal.getPosition())) {

                grassLocation.remove(animal.getPosition());
                mapBoundary.remove(animal.getPosition(), null);
                placeGrass();
            }
            mapBoundary.add(animal.getPosition(), animal);
            return true;
        }
        throw new IllegalArgumentException(
                "Object cannot be placed, because field " + animal.getPosition()
                        + " does not exist or is occupied"
        );
//        return false;
    }

    @Override
    public boolean positionChanged(Vector2d oldPosition, Animal animal) {
        Vector2d newPosition = animal.getPosition();

        if(super.positionChanged(oldPosition, animal)) {
            if (grassLocation.containsKey(newPosition)) {

                grassLocation.remove(newPosition);
                mapBoundary.remove(newPosition, null);
                placeGrass();
            }
            mapBoundary.positionChanged(oldPosition, animal);
            return true;
        }
        return false;
    }
}
