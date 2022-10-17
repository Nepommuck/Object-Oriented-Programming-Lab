package agh.ics.oop;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {
//        out.println("system wystartował");
//
//        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
//        String[] commands = {"f", "f", "r", "l", "a", "b"};
//
////        old_run(cars);
//        run( convert(commands) );
//        out.println("system zakończył działanie");


//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));
//
//        out.println(MapDirection.WEST == MapDirection.NORTH.next());
//        MapDirection[] dirs = {MapDirection.NORTH};
//
//        out.println(position1.equals(null));

        Animal a = new Animal();
        out.println(a);
        out.println(a.isAt(
                new Vector2d(3, 2)
        ));

        a.move(MoveDirection.RIGHT);
        a.move(MoveDirection.FORWARD);
        a.move(MoveDirection.FORWARD);
        a.move(MoveDirection.FORWARD);
        out.println(a);
        
//        String[] s = {"ak abba a po pl";
//        for (char c:s
//             ) {
//            out.println(let);
//        }
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
