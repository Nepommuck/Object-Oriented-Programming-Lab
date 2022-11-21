package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap {

    final protected Vector2d minPosition = new Vector2d(0, 0);  // to nie jest część wspólna map
    protected Vector2d maxPosition; // to też nie

    protected final List<Animal> animals;

    protected Vector2d getMaxPosition() {
        return maxPosition;
    }


    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public boolean canMoveTo(Vector2d position) {

        if (position.follows(minPosition))
            return !isOccupied(position);
        return false;
    }

    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position))
                return animal;
        }
        return null;
    }

    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition()))
            return false;

        animals.add(animal);
        return true;
    }

    public String toString() {
        StringBuilder rez = new StringBuilder();
        // May be outdated!  // to chyba niedobrze?
        maxPosition = getMaxPosition();

        for (int y = maxPosition.y; y >= 0; y--) {  // https://raw.githubusercontent.com/apohllo/obiektowe-lab/master/lab4/java/MapVisualizer.java

            for (int x = 0; x <= maxPosition.x; x++) {
                Object obj = objectAt(new Vector2d(x, y));
                if (obj == null)
                    rez.append(" ");
                else
                    rez.append(obj);

                rez.append(" ");
            }
            rez.append("\n");
        }
        return String.valueOf(rez);
    }
}
