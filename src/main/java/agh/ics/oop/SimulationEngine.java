package agh.ics.oop;

import agh.ics.oop.gui.App;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable {
    private IWorldMap map;
    private List<Animal> animals;
    private MoveDirection[] commands;

    public SimulationEngine(MoveDirection[] moves, IWorldMap worldMap, Vector2d[] animalsPositions, App app) {
        map = worldMap;
        animals = new ArrayList<>();
        commands = moves;

        for(Vector2d pos:animalsPositions) {
            Animal newAnimal = new Animal(map, pos);
            if(map.place(newAnimal)) {
                animals.add(newAnimal);
                newAnimal.addObserver((IPositionChangeObserver) map);
                if(app != null)
                    newAnimal.addObserver(app);
            }
        }
    }

    public SimulationEngine(MoveDirection[] moves, IWorldMap worldMap, Vector2d[] animalsPositions) {
        this(moves, worldMap, animalsPositions, null);
    }

    @Override
    public void run() {
        int numberOfAnimals = animals.size();
        int i = 0;
        for(MoveDirection command:commands) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println("Nieoczekiwane przerwanie symulacji");
                throw new RuntimeException(e);
            }
            animals.get(i).move(command);
            i = (i+1) % numberOfAnimals;
        }
    }
}
