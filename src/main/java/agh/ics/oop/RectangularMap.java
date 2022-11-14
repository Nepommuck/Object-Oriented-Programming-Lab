package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height) {
        maxPosition = new Vector2d(width - 1, height - 1);

        animals = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder rez = new StringBuilder();

        for(int y = maxPosition.y; y>=0; y--) {

            for (int x=0; x <= maxPosition.x; x++) {
                Animal animal = (Animal) objectAt(new Vector2d(x, y));
                if (animal == null)
                    rez.append(".");
                else
                    rez.append(animal.toString());
            }
            rez.append("\n");
        }
        return String.valueOf(rez);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {

        if(position.follows(minPosition) && position.precedes(maxPosition))
            return !isOccupied(position);
        return false;
    }


    @Override
    public boolean place(Animal animal) {
        if(isOccupied(
                animal.getPosition()
        ))
            return false;

        animals.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

}

