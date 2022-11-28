package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class RectangularMap extends AbstractWorldMap {

    private final Vector2d maxPosition;
    public RectangularMap(int width, int height) {
        maxPosition = new Vector2d(width - 1, height - 1);
    }

    @Override
    Vector2d getUpperRight() {
        return maxPosition;
    }
}

