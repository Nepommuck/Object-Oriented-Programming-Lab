package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class RectangularMap implements IWorldMap{

    final Vector2d maxPosition;
    List<List<Animal>> map;

    public RectangularMap(int width, int height) {
        maxPosition = new Vector2d(width - 1, height - 1);
        map = new ArrayList<>();

        for (int y=0; y<height; y++) {
            map.add(new ArrayList<>());
            for (int x=0; x<width; x++)
                map.get(y).add(null);
        }
    }

    public void show() {
        for(int i=map.size()-1; i>=0; i--) {
            for(Animal animal:map.get(i)) {
                if(animal == null)
                    out.print(' ');
                else
                    out.print(animal);
                out.print(',');
            }
            out.println("");
        }
        out.println("");
    }

    @Override
    public boolean canMoveTo(Vector2d position) {

        if(position.follows(new Vector2d(0, 0)) && position.precedes(maxPosition))
            return !isOccupied(position);
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition()))
            return false;

        if (animal.getActualPosition() != null) {
            int x = animal.getActualPosition().x;
            int y = animal.getActualPosition().y;
            map.get(y).set(x, null);
        }
        
        animal.updatePosition();

        int x = animal.getPosition().x;
        int y = animal.getPosition().y;
        map.get(y).set(x, animal);
        return true;
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        return map.get(position.y).get(position.x) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return map.get(position.y).get(position.x);
    }
}
