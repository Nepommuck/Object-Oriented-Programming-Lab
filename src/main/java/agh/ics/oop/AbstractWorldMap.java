package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    Map<Vector2d, Animal> animals = new HashMap<>();

    protected Vector2d getLowerLeft() {
        return new Vector2d(0, 0);
    }

    protected abstract Vector2d getUpperRight();

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public boolean canMoveTo(Vector2d position) {

        if(position.follows(getLowerLeft()) && position.precedes(getUpperRight()))
            return !isOccupied(position);
        return false;
    }

    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    public boolean place(Animal animal) {
        if(!canMoveTo(animal.getPosition()))
            throw new IllegalArgumentException(
                    "Object cannot be placed, because field " + animal.getPosition()
                            + " does not exist or is occupied"
            );

        animals.put(animal.getPosition(), animal);
        return true;
    }

//    public String toString() {
//        StringBuilder rez = new StringBuilder();
//        // May be outdated!
//        maxPosition = getMaxPosition();
//
//        for(int y = maxPosition.y; y>=0; y--) {
//
//            for (int x=0; x <= maxPosition.x; x++) {
//                Object obj = objectAt(new Vector2d(x, y));
//                if(obj == null)
//                    rez.append(".");
//                else
//                    rez.append(obj);
//
//                rez.append(" ");
//            }
//            rez.append("\n");
//        }
//        return String.valueOf(rez);
//    }

    @Override
    public String toString() {
        MapVisualizer mv = new MapVisualizer(this);
        return mv.draw(getLowerLeft(), getUpperRight());
    }


    // Wyjątek

    public boolean positionChanged(Vector2d oldPosition, Animal animal) {
        Vector2d newPosition = animal.getPosition();

        if (!animals.containsKey(oldPosition))
            return false;
        if (newPosition.equals(oldPosition))
            return true;
        if (animals.containsKey(newPosition))
            return false;

        animals.remove(oldPosition);
        animals.put(newPosition, animal);
        return true;
    }
}
