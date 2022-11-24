package agh.ics.oop;

public interface IPositionChangeObserver {
    boolean positionChanged(Vector2d oldPosition, Animal animal);
}
