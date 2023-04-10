package com.github.view.pane;

import com.github.view.buttons.GameBoardBackButton;
import javafx.geometry.NodeOrientation;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class GameBoardTopPane extends Pane {

    private TimerPane timerPane;

    private BombsCountPane bombsCountPane;

    private GameBoardBackButton gameBoardBackButton;

    public GameBoardTopPane() {
        this.setId("game-board-top-pane");
        this.timerPane = new TimerPane();
        this.bombsCountPane = new BombsCountPane();
        initPane();
    }

    public GameBoardTopPane(Label timerTextLabel, Label timerValue) {
        this.timerPane = new TimerPane(timerTextLabel, timerValue);
        this.bombsCountPane = new BombsCountPane();
        initPane();
    }

    private void initPane() {
        this.gameBoardBackButton = new GameBoardBackButton();
        this.setPrefSize(480, 64);
        this.setLayoutY(0);
        this.setLayoutX(0);
        this.setNodeOrientation(NodeOrientation.INHERIT);
        this.setStyle("-fx-background-color:  #8dbdf1;");
        this.setAccessibleRole(AccessibleRole.PARENT);
        this.getChildren().add(this.gameBoardBackButton);

        if (this.timerPane == null) {
            this.timerPane = new TimerPane();
        }
        this.getChildren().add(this.timerPane);
        this.getChildren().add(this.bombsCountPane);
        this.setVisible(true);
    }

    public TimerPane getTimer() {
        return this.timerPane;
    }

    public void setBombsCountValue(int value) {
        this.bombsCountPane.setBombsCount(value);
    }
}
