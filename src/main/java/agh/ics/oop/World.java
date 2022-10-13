package agh.ics.oop;

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
}
