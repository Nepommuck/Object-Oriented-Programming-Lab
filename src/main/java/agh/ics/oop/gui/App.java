package agh.ics.oop.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {

    @Override
    public void init() throws Exception {
        super.init();

        Parameters parameters = getParameters();
        if (parameters != null) {
            List<String> argList = getParameters().getRaw();
            String[] args = argList.toArray(new String[0]);
            Application.launch(args);
        }
        else
            Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lab 7");

        Label label = new Label("Zwierzak");
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        gridPane.add(label, 2, 0, 1, 1);
        Scene scene = new Scene(gridPane, 400, 400);


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
