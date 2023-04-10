package com.github.view.pane.other;

import com.github.view.pane.ShapeParameters;
import javafx.geometry.NodeOrientation;
import javafx.scene.AccessibleRole;
import javafx.scene.layout.Pane;

public class MarkerPane extends Pane implements ShapeParameters {

    public MarkerPane() {
        this.setPrefSize(34, 34);
        this.setVisible(true);
        createVisual();
    }

    @Override
    public void createVisual() {
        Pane pole = getShape(3, 20, 0,15,7);
        this.getChildren().add(pole);

        Pane ass = getShape(8, 3, 0, 13,25);
        this.getChildren().add(ass);

        Pane flag = getShape(5,6,0, 16,7.5);
        this.getChildren().add(flag);
    }

    @Override
    public Pane getShape(int width, int height, int rotation, double layoutX, double layoutY) {
        Pane contactPane = new Pane();
        contactPane.setNodeOrientation(NodeOrientation.INHERIT);
        contactPane.setAccessibleRole(AccessibleRole.PARENT);
        contactPane.setVisible(true);
        contactPane.setPrefSize(width, height);
        contactPane.setRotate(rotation);
        contactPane.setLayoutX(layoutX);
        contactPane.setLayoutY(layoutY);
        contactPane.setStyle("-fx-background-color: #676767;");
        return contactPane;
    }

}
