package com.github.utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class StopWatch {

    private Label timerLabel;

    private Timeline timeline;

    private int time;

    public StopWatch() {

    }

    public StopWatch(Label timerLabel) {
        this.timerLabel = timerLabel;
    }

    public void stop() {
        this.timeline.stop();
    }

    public void start() {
        KeyFrame kf = new KeyFrame(Duration.millis(1000), e -> {
            this.time++;
            if (this.timerLabel != null) {
                this.timerLabel.setText(String.valueOf(this.time));
            }
        });

        this.timeline = new Timeline(kf);
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
    }

    public void pause() {
        this.timeline.pause();
    }

    public void resume() {
        this.timeline.play();
    }

    public void reset() {
        this.time = 0;
        this.timerLabel.setText(" ");
    }
}
