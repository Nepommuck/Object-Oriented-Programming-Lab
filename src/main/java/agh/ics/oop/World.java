package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.out;

public class World {

    public static void controlAnimal(Animal animal) {

        int k=1, mx = 50;
        String[] userInput;
        Scanner myObj = new Scanner(System.in);
        boolean ok;

        System.out.println("Rozpoczęto testowanie dzialania klasy Animal");
        System.out.println("--------------------------------------------\n");

        while (true) {
            System.out.println(animal);
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
                        animal.move(com);
                }
            }
        }
        System.out.println("--------------------------------------------\n");
        System.out.println("Zakonczono testowanie dzialania klasy Animal");
    }

    public static void main(String[] args) {

        try {
            args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
//            args = new String[]{"f", "r", "f", "f"};
//            args = new String[]{};

            App app = new App();
            app.init();

            MoveDirection[] directions = OptionsParser.parse(args);
            RectangularMap map = new RectangularMap(10, 5);
            GrassField grassMap = new GrassField(10);
            out.println(grassMap);

            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            IEngine engine = new SimulationEngine(directions, grassMap, positions);

            out.println(grassMap);
            engine.run();

            out.println(grassMap);
        }
        catch (IllegalArgumentException e) {
            out.println("Encountered  fatal error:");
            out.println(e);
            out.println("The process will be terminated.");
            exit(-1);
        }
        catch (java.lang.Exception e) {
            out.println("Something wrong with gui:");
            out.println(e);
            exit(-1);
        }

//        GrassField grassMap = new GrassField(15);
//
//        out.println(
//                grassMap
//        );
//
//        Animal animal1 = new Animal(grassMap, new Vector2d(2, 2));
//        grassMap.place(animal1);
//        out.println(grassMap.animals);
//
//        animal1.addObserver(grassMap);
//
//        out.println(grassMap);
//        animal1.move(MoveDirection.FORWARD);
//        out.println(grassMap.animals);
//        out.println(grassMap);
    }
}
