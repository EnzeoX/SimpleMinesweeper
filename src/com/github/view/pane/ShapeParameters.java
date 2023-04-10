package com.github.view.pane;

import javafx.scene.layout.Pane;

public interface ShapeParameters {

    void createVisual();

    Pane getShape(int width, int height, int rotation, double layoutX, double layoutY);
}
