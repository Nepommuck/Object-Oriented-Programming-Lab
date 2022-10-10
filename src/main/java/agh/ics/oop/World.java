package agh.ics.oop;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {
        out.println("system wystartował");

        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        String[] commands = {"f", "f", "r", "l", "a", "b"};

//        old_run(cars);
        run( convert(commands) );
        out.println("system zakończył działanie");
    }

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

    public static void run(Direction[] comm) {
        for(Direction command: comm) {
            String message = switch (command) {
                case FORWARD -> "Do przodu";
                case BACKWARD -> "Do tyłu";
                case LEFT -> "W lewo";
                case RIGHT -> "W prawo";
            };

            out.println(message);
        }
    }

    public static Direction[] convert(String[] args) {
        Direction[] ans = new Direction[args.length];

        int i = 0;
        for (String arg : args) {
            switch (arg) {
                case "f" -> ans[i] = Direction.FORWARD;
                case "b" -> ans[i] = Direction.BACKWARD;
                case "l" -> ans[i] = Direction.LEFT;
                case "r" -> ans[i] = Direction.RIGHT;
                default -> i--;
            }
            i++;
        }

        Direction[] final_ans = new Direction[i];
        System.arraycopy(ans, 0, final_ans, 0, i);

        return final_ans;
    }
}
