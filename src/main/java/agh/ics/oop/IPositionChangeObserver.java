package agh.ics.oop;

import java.io.FileNotFoundException;

public interface IPositionChangeObserver {
    boolean positionChanged(Vector2d oldPosition, Animal animal);
}
