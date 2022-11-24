package agh.ics.oop;


import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.System.out;


public class MapBoundary implements IPositionChangeObserver{
    TreeSet<VectorKeyAndElement> setX = new TreeSet<>(new CompareVectorKeyAndElement(true));
    TreeSet<VectorKeyAndElement> setY = new TreeSet<>(new CompareVectorKeyAndElement(false));

    @Override
    public boolean positionChanged(Vector2d oldPosition, Animal animal) {

        return this.remove(oldPosition, animal) &&
                this.add(animal.getPosition(), animal);
    }

    public boolean add(Vector2d v, IMapElement el) {
        VectorKeyAndElement vke = new VectorKeyAndElement(v, el);

        return setX.add(vke) && setY.add(vke);
    }
    public boolean remove(Vector2d v, IMapElement el) {
        VectorKeyAndElement vke = new VectorKeyAndElement(v, el);

        return setX.remove(vke) && setY.remove(vke);
    }

    public Vector2d getLowerLeft() {
        return setX.first().key.lowerLeft(
                setY.first().key);
    }

    public Vector2d getUpperRight() {
        return setX.last().key.upperRight(
                setY.last().key);
    }

    @Override
    public String toString() {
        return "X: " + setX.toString() + "\nY: " + setY.toString();
    }


    public class VectorKeyAndElement {
        final Vector2d key;
        final IMapElement element;

        public VectorKeyAndElement(Vector2d key, IMapElement element) {
            this.key = key;
            this.element = element;
        }

        @Override
        public String toString() {
            return key + ":  " + element;
        }
    }

    public class CompareVectorKeyAndElement implements Comparator {

        boolean xFirst;

        CompareVectorKeyAndElement(boolean xFirst) {
            this.xFirst = xFirst;
        }

        @Override
        public int compare(Object o1, Object o2) {
            Vector2d v1 = ((VectorKeyAndElement)o1).key;
            Vector2d v2 = ((VectorKeyAndElement)o2).key;

            if (this.xFirst) {
                if (v1.x - v2.x != 0)
                    return v1.x - v2.x;
                else
                    return v1.y - v2.y;
            }
            if (v1.y - v2.y != 0)
                return v1.y - v2.y;
            else
                return v1.x - v2.x;
        }
    }
}
