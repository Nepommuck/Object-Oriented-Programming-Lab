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

        maxPosition = new Vector2d(0, 0);

        while (numberOfGrass > 0) {
            int x = ThreadLocalRandom.current().nextInt(0, mx_ind + 1);
            int y = ThreadLocalRandom.current().nextInt(0, mx_ind + 1);

            if(!hasGrass(new Vector2d(x, y))) {
                numberOfGrass--;
                Vector2d grassPosition = new Vector2d(x, y);
                grassLocation.add(new Grass(grassPosition));

                maxPosition = maxPosition.upperRight(grassPosition);
            }
        }

        animals = new ArrayList<>();
    }

    public boolean hasGrass(Vector2d position) {
        for(Grass g:grassLocation)
            if(g.getPosition().equals(position))
                return true;
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {

        if(position.follows(minPosition))
            return !isOccupied(position);
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if(!canMoveTo(animal.getPosition()))
            return false;

        animals.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal animal:animals) {
            if(animal.isAt(position))
                return true;
        }
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
