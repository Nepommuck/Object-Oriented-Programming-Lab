package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.max;


public class GrassField extends AbstractWorldMap{

    private final int numberOfGrass;
    private List<Grass> grassLocation;

    @Override
    protected Vector2d getMaxPosition() {
        for(Animal animal : animals) {
            maxPosition = maxPosition.upperRight(animal.getPosition());
        }
        return maxPosition;
    }

    public GrassField(int numberOfGrass) {
        this.numberOfGrass = numberOfGrass;
        int mx_ind = (int) Math.sqrt(10 * numberOfGrass);
        grassLocation = new ArrayList<>();
        animals = new ArrayList<>();

        maxPosition = new Vector2d(0, 0);

        while (numberOfGrass > 0) {
            int x = ThreadLocalRandom.current().nextInt(0, mx_ind + 1);
            int y = ThreadLocalRandom.current().nextInt(0, mx_ind + 1);

            if(objectAt(new Vector2d(x, y)) == null) {
                numberOfGrass--;
                Vector2d grassPosition = new Vector2d(x, y);
                grassLocation.add(new Grass(grassPosition));

                maxPosition = maxPosition.upperRight(grassPosition);
            }
        }
    }

    public boolean canMoveTo(Vector2d position) {

        if(position.follows(minPosition))
            return !(objectAt(position) instanceof Animal);
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object object = super.objectAt(position);
        if(object != null)
            return object;
        for(Grass grass : grassLocation)
            if(grass.getPosition().equals(position))
                return grass;
        return null;
    }
}
