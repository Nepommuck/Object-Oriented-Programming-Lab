package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.max;


public class GrassField extends AbstractWorldMap{

    private final int numberOfGrass;
//    private List<Grass> grassLocation;
    Map<Vector2d, Grass> grassLocation = new HashMap<>();

    @Override
    protected Vector2d getMaxPosition() {
        for(Vector2d position : animals.keySet()) {
            maxPosition = maxPosition.upperRight(position);
        }
        return maxPosition;
    }

    public GrassField(int numberOfGrass) {
        this.numberOfGrass = numberOfGrass;
        int mx_ind = (int) Math.sqrt(10 * numberOfGrass);

        maxPosition = new Vector2d(0, 0);

//        Losowanie trawy
        while (numberOfGrass > 0) {
            int x = ThreadLocalRandom.current().nextInt(0, mx_ind + 1);
            int y = ThreadLocalRandom.current().nextInt(0, mx_ind + 1);

            if(!isOccupied(new Vector2d(x, y))) {
                numberOfGrass--;
                Vector2d grassPosition = new Vector2d(x, y);
//                grassLocation.add(new Grass(grassPosition));
                grassLocation.put(grassPosition, new Grass(grassPosition));

                maxPosition = maxPosition.upperRight(grassPosition);
            }
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object object = super.objectAt(position);
        if(object != null)
            return object;
        return grassLocation.get(position);
    }
}
