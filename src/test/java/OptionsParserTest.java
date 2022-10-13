import agh.ics.oop.OptionsParser;
import agh.ics.oop.MoveDirection;
import static agh.ics.oop.MoveDirection.*;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertArrayEquals;

public class OptionsParserTest {
    @Test public void correctTest() {
        String[] tab1 = {"forward", "backward", "left", "right"};
        MoveDirection[] odp1 = {FORWARD, BACKWARD, LEFT, RIGHT};
        assertArrayEquals(OptionsParser.parse(tab1), odp1);

        String[] tab2 = {"f", "b", "l", "r"};
        MoveDirection[] odp2 = {FORWARD, BACKWARD, LEFT, RIGHT};
        assertArrayEquals(OptionsParser.parse(tab2), odp2);

        String[] tab3 = {"f", "B", "b", "rIGHT", "Backwards", "lEfT", "L"};
        MoveDirection[] odp3 = {FORWARD, BACKWARD, BACKWARD, RIGHT, BACKWARD, LEFT, LEFT};
        assertArrayEquals(OptionsParser.parse(tab3), odp3);
    }

    @Test public void misspelledTest() {
        String[] tab1 = {"front", "b", "l", "a", "right", "left"};
        MoveDirection[] odp1 = {BACKWARD, LEFT, RIGHT, LEFT};
        assertArrayEquals(OptionsParser.parse(tab1), odp1);

        String[] tab2 = {"riggt", "backwart", "leftt", "forwart"};
        MoveDirection[] odp2 = {};
        assertArrayEquals(OptionsParser.parse(tab2), odp2);

        String[] tab3 = {"a", "b", "c", "d", "e", "f", "left", "right", "upper", "lower", "none", "f"};
        MoveDirection[] odp3 = {BACKWARD, FORWARD, LEFT, RIGHT, FORWARD};
        assertArrayEquals(OptionsParser.parse(tab3), odp3);
    }
}
