package com.github.view.pane.scroll;

import javafx.geometry.NodeOrientation;
import javafx.scene.AccessibleRole;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlendMode;

public class GameScrollPane extends ScrollPane {

    public GameScrollPane() {
        setScrollPaneDefaultPosition();
        this.setStyle("-fx-border-color: transparent;" +
                " -fx-focus-color: transparent;" +
                " -fx-faint-focus-color: transparent;" +
                "-fx-background-insets: 0");
        this.setVisible(true);
        this.nodeOrientationProperty().set(NodeOrientation.INHERIT);
        this.setAccessibleRole(AccessibleRole.SCROLL_PANE);
        this.setBlendMode(BlendMode.SRC_OVER);
        setActions();
    }

    public void setScrollPaneDefaultPosition() {
        this.setPrefSize(460, 560);
        this.setLayoutX(11);
        this.setLayoutY(10);
    }

    private void setActions() {
        this.setPannable(true);
        this.setCache(true);
    }
}
