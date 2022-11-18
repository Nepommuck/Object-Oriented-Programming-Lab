package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height) {
        maxPosition = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {

        if(position.follows(minPosition) && position.precedes(maxPosition))
            return !isOccupied(position);
        return false;
    }
}

