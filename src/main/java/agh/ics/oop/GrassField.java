package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.max;


public class GrassField extends AbstractWorldMap{

    private final int numberOfGrass;
//    private List<Grass> grassLocation;
    Map<Vector2d, Grass> grassLocation = new HashMap<>();

    @Override
    protected Vector2d getMaxPosition() {
        maxPosition = new Vector2d(0, 0);

        for(Vector2d position : grassLocation.keySet()) {
            maxPosition = maxPosition.upperRight(position);
        }
        for(Vector2d position : animals.keySet()) {
            maxPosition = maxPosition.upperRight(position);
        }
        return maxPosition;
    }

    public GrassField(int numberOfGrass) {
        this.numberOfGrass = numberOfGrass;
        maxPosition = new Vector2d(0, 0);

//        Losowanie trawy
        for (int i=0; i<numberOfGrass; i++)
            placeGrass();
    }

    private void placeGrass() {
        int mx_ind = (int) Math.sqrt(10 * numberOfGrass);
        while (true) {
            int x = ThreadLocalRandom.current().nextInt(0, mx_ind + 1);
            int y = ThreadLocalRandom.current().nextInt(0, mx_ind + 1);

            if(!isOccupied(new Vector2d(x, y))) {
                Vector2d grassPosition = new Vector2d(x, y);
                grassLocation.put(grassPosition, new Grass(grassPosition));

                maxPosition = maxPosition.upperRight(grassPosition);
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
        if(position.follows(minPosition))
            return !isOccupied(position) || objectAt(position) instanceof Grass;
        return false;
    }

    // Movement of grass
    @Override
    public boolean place(Animal animal) {

        if(super.place(animal)) {
            if (grassLocation.containsKey(animal.getPosition())) {

                grassLocation.remove(animal.getPosition());
                placeGrass();
            }
            return true;
        }
        throw new IllegalArgumentException(
                "Object cannot be placed, because field " + animal.getPosition()
                        + " does not exist or is occupied"
        );
//        return false;
    }

    // Movement of grass
    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {

        if(super.positionChanged(oldPosition, newPosition)) {
            if (grassLocation.containsKey(newPosition)) {

                grassLocation.remove(newPosition);
                placeGrass();
            }
            return true;
        }
        return false;
    }
}
