package com.github.view.labels.main;

import javafx.scene.control.Label;
import javafx.scene.effect.Glow;

public class MinesQuantityLabel extends Label {

    public MinesQuantityLabel() {
        init();
    }

    private void init() {
        this.setText("Mines quantity");
        this.setVisible(true);
        this.setStyle("-fx-font-family: 'Britannic Bold'; -fx-font-size: 20");
        this.setPrefSize(150, 25);
        this.setLayoutX(108);
        this.setLayoutY(251);
    }
}
