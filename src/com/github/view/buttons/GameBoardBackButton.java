package com.github.view.buttons;


import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

public class GameBoardBackButton extends Button {

    public GameBoardBackButton() {
        initButton();
        initActions();
    }

    private void initButton() {
        this.setPrefSize(52,64);
        this.setVisible(true);
        this.setAccessibleRole(AccessibleRole.BUTTON);
        this.setStyle("-fx-background-color: none;" +
                "-fx-border-width: 0 1 0 0;" +
                "-fx-border-color: #c4f3ff;");
    }

    private void initActions() {
        this.setOnMouseEntered(event -> {
            this.setStyle("-fx-background-color: #41c2db;" +
                    "-fx-border-width: 0 1 0 0;" +
                    "-fx-border-color: #c4f3ff;");
        });

        this.setOnMouseExited(event -> {
            this.setStyle("-fx-background-color: none;" +
                    "-fx-border-width: 0 1 0 0;" +
                    "-fx-border-color: #c4f3ff;");
        });

        this.setOnMouseClicked(event -> {
            this.setStyle("-fx-background-color: #d6d6d6;" +
                    "-fx-border-width: 0 1 0 0;" +
                    "-fx-border-color: #c4f3ff;");
        });

        this.setOnMouseReleased(event -> {
            this.setStyle("-fx-background-color: none;" +
                    "-fx-border-width: 0 1 0 0;" +
                    "-fx-border-color: #c4f3ff;");
        });
    }
}
