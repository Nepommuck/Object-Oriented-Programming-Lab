package agh.ics.oop;

import java.util.Scanner;


public class Animal {
    private MapDirection direction;
    private Vector2d position;

    private static Vector2d minPosition;
    private static Vector2d maxPosition;

    public Animal() {
        direction = MapDirection.NORTH;
        position = new Vector2d(2, 2);

        minPosition = new Vector2d(0, 0);
        maxPosition = new Vector2d(4, 4);
    }

    @Override
    public String toString() {
        return "Zwierzę na pozycji "+position.toString()+" w kierunku "+direction;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public boolean isFacing(MapDirection goal) {
        return this.direction.equals(goal);
    }

    public void move(MoveDirection direction) {
        Vector2d newPosition = position.copy();

        switch (direction) {
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
            case FORWARD -> newPosition = position.add(this.direction.toUnitVector());
            case BACKWARD -> newPosition = position.subtract(this.direction.toUnitVector());
        }
        if (newPosition.precedes(maxPosition) && newPosition.follows(minPosition))
            position = newPosition;
    }

    public static void controlAnimal() {

        int k=1, mx = 50;
        Animal animal1 = new Animal();
        String[] userInput;
        Scanner myObj = new Scanner(System.in);
        boolean ok;

        System.out.println("Rozpoczęto testowanie dzialania klasy Animal");
        System.out.println("--------------------------------------------\n");

        while (true) {
            System.out.println(animal1);
            System.out.println("");
            System.out.print("Ile komend: ");
            ok = true;

            try {
                k = Integer.parseInt(
                        myObj.nextLine()
                );
            }
            catch (NumberFormatException e) {
                System.out.println("Podana wartosc nie jest liczba calkowita.");
                ok = false;
            }
            if (k <= 0)
                break;

            if (ok) {
                System.out.println(k);
                if (k > mx) {
                    System.out.println("BLAD! Maksymalnie "+mx+" komend.");
                }
                else {
                    userInput = new String[k];
                    for (int i=0; i<k; i++) {
                        System.out.print("["+(i+1)+"/"+k+"]:  ");
                        userInput[i] = myObj.nextLine();
                    }
                    for (MoveDirection com : OptionsParser.parse(userInput))
                        animal1.move(com);
                }
            }
        }
        System.out.println("--------------------------------------------\n");
        System.out.println("Zakonczono testowanie dzialania klasy Animal");
    }
}
