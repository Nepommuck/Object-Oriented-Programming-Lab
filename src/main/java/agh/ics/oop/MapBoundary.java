package agh.ics.oop;


import java.util.Set;
import java.util.TreeSet;


public class MapBoundary implements IPositionChangeObserver{
    Set<Vector2d> setX = new TreeSet<>(new CompareVector(true));

    public class CompareVector {
        CompareVector(boolean xFirst) {
            this.xFirst = xFirst;
        }
        public int compare(Vector2d v1, Vector2d v2) {
            int first = v1.x;
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

    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        return false;
    }
}
