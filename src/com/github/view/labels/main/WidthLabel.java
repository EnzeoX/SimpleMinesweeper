package com.github.view.labels.main;

import javafx.scene.control.Label;

public class WidthLabel extends Label {

    public WidthLabel() {
        init();
    }

    private void init() {

        this.setText("Field width");
        this.setVisible(true);
        this.setStyle("-fx-font-family: 'Britannic Bold'; -fx-font-size: 20");
        this.setPrefSize(114, 25);
        this.setLayoutX(125);
        this.setLayoutY(177);
    }
}
