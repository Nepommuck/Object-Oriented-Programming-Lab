package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class App extends Application implements IPositionChangeObserver {

    private GridPane gridPane;
    private final int width = 45;
    private final int height = 45;
    private GrassField map;
    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Lab 7");

        GrassField map = (GrassField) handleSimulation();

        Vector2d upperRight = map.getUpperRight();

        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        drawNumberLine(gridPane, upperRight, width, height);
        drawElements(gridPane, map, upperRight);

        scene = new Scene(gridPane, width * (upperRight.x + 2), height * (upperRight.y + 2));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private AbstractWorldMap handleSimulation() {
        String[] arrArgs = getParameters().getRaw().toArray(new String[0]);

        MoveDirection[] directions = OptionsParser.parse(arrArgs);
        map = new GrassField(10);

        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,9) };
//        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        SimulationEngine engine = new SimulationEngine(directions, map, positions, this);
        Thread engineThread = new Thread(engine);

        engineThread.start();
//        engine.run();

        System.out.println(map);
        return map;
    }

    private void addLabel(GridPane gridPane, Vector2d gridPosition, String msg) {
        int colIndex = gridPosition.x;
        int rowIndex = gridPosition.y;

        Label label = new Label(msg);
        GridPane.setHalignment(label, HPos.CENTER);
        gridPane.add(label, colIndex, rowIndex);
    }

    private void addGuiElementBox(GridPane gridPane, Vector2d gridPosition, GuiElementBox guiElem) {
        int colIndex = gridPosition.x;
        int rowIndex = gridPosition.y;

        VBox vBox = guiElem.getvBox();
        GridPane.setHalignment(vBox, HPos.CENTER);
        gridPane.add(vBox, colIndex, rowIndex);
    }

    private void drawElements(GridPane gridPane, AbstractWorldMap map, Vector2d upperRight) {
        for (int x=0; x <= upperRight.x; x++)
            for (int y=0; y <= upperRight.y; y++) {

                IMapElement elem = (IMapElement) map.objectAt(new Vector2d(x, y));
                if (elem != null) {
                    try {
                        addGuiElementBox(gridPane, fromMapToGridPosition(new Vector2d(x, y), upperRight), new GuiElementBox(elem));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
    }

    private void drawNumberLine(GridPane gridPane, Vector2d upperRight, int width, int height) {

        addLabel(gridPane, new Vector2d(0, 0), "y \\ x");
        gridPane.getColumnConstraints().add(new ColumnConstraints(width));
        gridPane.getRowConstraints().add(new RowConstraints(height));

        // Draw column numbers
        for (int x=0; x <= upperRight.x; x++) {
            String num = Integer.toString(x);

            addLabel(gridPane, new Vector2d(x+1, 0), num);
            gridPane.getColumnConstraints().add(new ColumnConstraints(width));
        }

        // Draw row numbers
        for (int y=0; y <= upperRight.y; y++) {
            String num = Integer.toString(y);

            addLabel(gridPane, fromMapToGridPosition(new Vector2d(-1, y), upperRight), num);
            gridPane.getRowConstraints().add(new RowConstraints(height));
        }
    }

    private Vector2d fromMapToGridPosition(Vector2d mapPosition, Vector2d upperRight) {
        return new Vector2d(mapPosition.x+1, upperRight.y - mapPosition.y + 1);
    }

    @Override
    public boolean positionChanged(Vector2d oldPosition, Animal animal) {
        clear();

        Vector2d upperRight = map.getUpperRight();
        drawNumberLine(gridPane, upperRight, width, height);
        drawElements(gridPane, map, upperRight);
        gridPane.setGridLinesVisible(true);

        stage.setHeight(height * (upperRight.y + 3));
        stage.setWidth(width * (upperRight.x + 2.5));

        return true;
    }

    private void clear() {
        this.gridPane.setGridLinesVisible(false);
        this.gridPane.getColumnConstraints().clear();
        this.gridPane.getRowConstraints().clear();
        this.gridPane.getChildren().clear();
        this.gridPane.setGridLinesVisible(true);
    }
}
