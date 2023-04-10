package com.github.view.labels.main;

import javafx.scene.control.Label;

public class HeightLabel extends Label {

    public HeightLabel() {
        init();
    }

    private void init() {
        this.setText("Field height");
        this.setVisible(true);
        this.setStyle("-fx-font-family: 'Britannic Bold'; -fx-font-size: 20");
        this.setPrefSize(114, 25);
        this.setLayoutX(124);
        this.setLayoutY(214);
    }
}
