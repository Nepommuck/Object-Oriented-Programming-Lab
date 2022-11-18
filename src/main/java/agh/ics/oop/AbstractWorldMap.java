package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    final protected Vector2d minPosition = new Vector2d(0, 0);
    protected Vector2d maxPosition;

    Map<Vector2d, Animal> animals = new HashMap<>();

    protected Vector2d getMaxPosition() {
        return maxPosition;
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public boolean canMoveTo(Vector2d position) {

        if(position.follows(minPosition))
            return !isOccupied(position);
        return false;
    }

    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    public boolean place(Animal animal) {
        if(!canMoveTo(animal.getPosition()))
            return false;

        animals.put(animal.getPosition(), animal);
        return true;
    }

    public String toString() {
        StringBuilder rez = new StringBuilder();
        // May be outdated!
        maxPosition = getMaxPosition();

        for(int y = maxPosition.y; y>=0; y--) {

            for (int x=0; x <= maxPosition.x; x++) {
                Object obj = objectAt(new Vector2d(x, y));
                if(obj == null)
                    rez.append(".");
                else
                    rez.append(obj);

                rez.append(" ");
            }
            rez.append("\n");
        }
        return String.valueOf(rez);
    }

    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (!animals.containsKey(oldPosition))
            return false;
        if (newPosition.equals(oldPosition))
            return true;
        if (animals.containsKey(newPosition))
            return false;
        Animal animal = animals.remove(oldPosition);
        animals.put(newPosition, animal);
        return true;
    }
}
