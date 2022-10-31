package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class World {
    public static void old_run(String[] names) {
        out.println("zwierzak idzie do przodu");
        boolean first = true;

        for(String name: names){
            if (!first)
                out.print(", ");
            out.print(name);

            first = false;
        }
        out.print("\n");
    }

    public static void run(MoveDirection[] comm) {
        for(MoveDirection command: comm) {
            String message = switch (command) {
                case FORWARD -> "Do przodu";
                case BACKWARD -> "Do tyłu";
                case LEFT -> "W lewo";
                case RIGHT -> "W prawo";
            };

            out.println(message);
        }
    }

    public static MoveDirection[] convert(String[] args) {
        MoveDirection[] ans = new MoveDirection[args.length];

        int i = 0;
        for (String arg : args) {
            switch (arg) {
                case "f" -> ans[i] = MoveDirection.FORWARD;
                case "b" -> ans[i] = MoveDirection.BACKWARD;
                case "l" -> ans[i] = MoveDirection.LEFT;
                case "r" -> ans[i] = MoveDirection.RIGHT;
                default -> i--;
            }
            i++;
        }

        MoveDirection[] final_ans = new MoveDirection[i];
        System.arraycopy(ans, 0, final_ans, 0, i);

        return final_ans;
    }

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
//        Animal animal1 = new Animal();
//        controlAnimal(animal1);

        List<List<Integer>> arr = new ArrayList<List<Integer>>();
        arr.add(new ArrayList<>());
        arr.add(new ArrayList<>());
        arr.get(0).add(5);
        arr.get(0).add(4);
        out.println(arr.get(0).get(0));
        out.println(arr);
        for (List<Integer> row:arr) {
            out.println(row);
        }

        RectangularMap m1 = new RectangularMap(4, 2);
        m1.show();
    }
}
