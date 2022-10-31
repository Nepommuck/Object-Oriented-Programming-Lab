package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class RectangularMap implements IWorldMap{
    List<List<Animal>> map;

    RectangularMap(int width, int height) {
        map = new ArrayList<List<Animal>>();
        for (int y=0; y<height; y++) {
            map.add(new ArrayList<>());
            for (int x=0; x<width; x++)
                map.get(y).add(null);
        }
    }

    public void show() {
        for (List<Animal> row:map) {
            out.println(row);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return map.get(position.y).get(position.x) == null;
    }

    @Override
    public boolean place(Animal animal) {
        map.get()
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }
}
