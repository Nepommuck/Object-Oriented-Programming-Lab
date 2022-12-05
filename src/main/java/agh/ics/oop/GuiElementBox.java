package agh.ics.oop;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private VBox vBox;
    private ImageView imageView;

    public GuiElementBox(IMapElement element) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(element.getResource()));
        imageView = new ImageView(image);
        imageView.fitHeightProperty().set(20);
        imageView.fitWidthProperty().set(20);

        Label label = new Label(element.mapDescription());
        vBox = new VBox(imageView, label);
        vBox.setAlignment(Pos.BASELINE_CENTER);
    }

    public VBox getvBox() {
        return vBox;
    }
}
