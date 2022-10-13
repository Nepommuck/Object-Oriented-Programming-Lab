package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import static agh.ics.oop.MoveDirection.*;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        List<MoveDirection> commList = new ArrayList<>();
        MoveDirection akt;
        for (String arg : args) {
            akt = switch(arg.toLowerCase()) {
                case "forward", "f" -> FORWARD;
                case "backward", "b", "backwards" -> BACKWARD;
                case "left", "l" -> LEFT;
                case "right", "r" -> RIGHT;
                default -> null;
            };
            if (akt != null)
                commList.add(akt);
        }
        return commList.toArray(new MoveDirection[0]);
    }
}
