package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class GrassField implements IWorldMap{

    final Vector2d minPosition = new Vector2d(0, 0);
    private final int numberOfGrass;
    private List<Animal> animals;
    private List<Grass> grassLocation;

    public GrassField(int numberOfGrass) {
        this.numberOfGrass = numberOfGrass;
        int mx_ind = (int) Math.sqrt(10 * numberOfGrass);

        while (numberOfGrass > 0) {
            int x = ThreadLocalRandom.current().nextInt(0, mx_ind + 1);
            int y = ThreadLocalRandom.current().nextInt(0, mx_ind + 1);

            if(!hasGrass(new Vector2d(x, y))) {
                numberOfGrass--;

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

        if(position.follows(minPosition) && position.precedes(maxPosition)
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
        for(Animal animal:animals)
            if(animal.isAt(position))
                return animal;
        return null;
    }
}
