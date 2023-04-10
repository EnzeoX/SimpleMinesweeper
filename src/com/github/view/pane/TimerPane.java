package com.github.view.pane;

import com.github.utils.StopWatch;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class TimerPane extends Pane {

    private int time;

    private final Label textTimeLabel;

    private final Label timerLabel;

    private StopWatch stopWatch;

//    private Timeline timeline;

    public TimerPane() {
        this.textTimeLabel = new Label();
        this.timerLabel = new Label();
        this.stopWatch = new StopWatch(timerLabel);
        setParameters();
    }

    public TimerPane(Label timerText, Label timerValue) {
        this.textTimeLabel = timerText;
        this.timerLabel = timerValue;
        setParameters();
    }

    private void setParameters() {
        this.setId("timer-label");

        this.textTimeLabel.setPrefSize(61, 25);
        this.textTimeLabel.setLayoutX(14);
        this.textTimeLabel.setLayoutY(20);
        this.textTimeLabel.setNodeOrientation(NodeOrientation.INHERIT);
        this.textTimeLabel.setText("TIME:");
        this.textTimeLabel.setStyle("-fx-font-family: 'Britannic Bold';" +
                "-fx-font-size: 25;");
        this.textTimeLabel.setAccessibleRole(AccessibleRole.TEXT);
        this.textTimeLabel.setVisible(true);
        this.getChildren().add(this.textTimeLabel);


        this.timerLabel.setPrefSize(67, 17);
        this.timerLabel.setLayoutX(88);
        this.timerLabel.setLayoutY(20);
        this.timerLabel.setNodeOrientation(NodeOrientation.INHERIT);
//        this.timerLabel.setText("0");
        this.timerLabel.setContentDisplay(ContentDisplay.LEFT);
        this.timerLabel.setAlignment(Pos.CENTER_LEFT);
        this.timerLabel.setStyle(
                "-fx-font-family: 'Britannic Bold';" +
                        "-fx-font-size: 25;" +
                        "-fx-text-fill: black;" +
                        "-fx-background-color: none;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-color: transparent;");
        this.timerLabel.setAccessibleRole(AccessibleRole.TEXT);
        this.timerLabel.setVisible(true);
        this.getChildren().add(this.timerLabel);

        this.setPrefSize(175, 64);
        this.setLayoutX(52);
        this.setLayoutY(0);
        this.setNodeOrientation(NodeOrientation.INHERIT);
        this.setAccessibleRole(AccessibleRole.PARENT);
        this.setVisible(true);
    }

    public void stopTimer() {
        if (this.stopWatch != null) {
            this.stopWatch.stop();
        }
    }

    public void startTimer() {
        if (this.stopWatch != null) {
            this.stopWatch.start();
        }
    }

    public void pauseTimer() {
        if (this.stopWatch != null) {
            this.stopWatch.pause();
        }
    }

    public void resumeTimer() {
        if (this.stopWatch != null) {
            this.stopWatch.resume();
        }
    }

    public void reset() {
        if (this.stopWatch != null) {
            this.stopWatch.reset();
        }
    }
}
