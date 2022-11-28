package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

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
        args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};

        try {
            Application.launch(App.class, args);
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
    }
}
