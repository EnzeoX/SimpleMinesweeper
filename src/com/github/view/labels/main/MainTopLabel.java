package com.github.view.labels.main;

import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.effect.Glow;

public class MainTopLabel extends Label {

    public MainTopLabel() {
        init();
    }

    private void init() {
        this.setText("MINESWEEPER");
        this.setNodeOrientation(NodeOrientation.INHERIT);
        this.setVisible(true);
        this.setStyle("-fx-font-family: 'Onyx';" +
                " -fx-font-size: 40;" +
                " -fx-background-color:  #8dbdf1;" +
                "-fx-text-fill: white;");
        this.setPrefSize(480, 68);
        this.setAlignment(Pos.CENTER);
        this.setTextOverrun(OverrunStyle.CLIP);
        this.setContentDisplay(ContentDisplay.CENTER);
        this.setEffect(new Glow(0.31));
        this.setLayoutX(0);
        this.setLayoutY(0);
    }
}
