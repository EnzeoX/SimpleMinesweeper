package com.github.view.pane.other;

import com.github.view.pane.ShapeParameters;
import javafx.geometry.NodeOrientation;
import javafx.scene.AccessibleRole;
import javafx.scene.layout.Pane;

public class BombPane extends Pane implements ShapeParameters {

    public BombPane() {
        this.setId("bomb-pane");
        this.setPrefSize(34, 34);
        this.setVisible(true);
        createVisual();
    }

    @Override
    public void createVisual() {
        Pane leftContact = getShape(6, 4, 0, 2, 15);
        this.getChildren().add(leftContact);

        Pane topContact = getShape(6, 4, 90, 14, 3);
        this.getChildren().add(topContact);

        Pane rightContact = getShape(6, 4, 0, 26, 15);
        this.getChildren().add(rightContact);

        Pane bottomContact = getShape(6, 4, 90, 14,27);
        this.getChildren().add(bottomContact);

        Pane bombBody = getShape(24,24,0,5,5);
        Pane centerContact = getShape(4, 4, 0, 10, 10);
        bombBody.getChildren().add(centerContact);

        this.getChildren().add(bombBody);
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
        contactPane.setStyle("-fx-background-color: black;" +
                "-fx-background-radius: 90;" +
                "-fx-border-color: grey;" +
                "-fx-border-radius: 90;" +
                "-fx-border-width: 0.5");
        return contactPane;
    }
}
