package com.github.view.anchors.game;

import com.github.handler.GameHandler;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import static com.github.view.global.styles.ButtonsStyles.MAIN_BUTTON_STYLE_DEFAULT;
import static com.github.view.global.styles.ButtonsStyles.MAIN_BUTTON_STYLE_ON_HOVER_DEFAULT;

public class EndGameBoard extends AnchorPane {

    private Button toMainMenuButton;
    private Button newGameButton;
    private Label textLabel;
    private final GameHandler gameHandler;
    private boolean isPaneActive = false;

    public EndGameBoard(String text, GameHandler gameHandler) {
        initObjects();
        initBoard();
        this.textLabel.setText(text);
        this.gameHandler = gameHandler;
        initActions();
    }

    public void setBoardText(String text) {
        this.textLabel.setText(text);
    }

    private void initBoard() {
        this.setId("end-game-pane");
        this.setPrefSize(380, 300);
        this.setStyle("-fx-background-color: white;" +
                "-fx-background-radius: 2;" +
                "-fx-border-color: #c4f3ff;" +
                "-fx-border-width: 4;" +
                "-fx-border-radius: 2;");
        this.setLayoutY(-300);
        this.setLayoutX(50);
        this.getChildren().add(this.textLabel);
        this.getChildren().add(this.toMainMenuButton);
        this.getChildren().add(this.newGameButton);
        this.setVisible(true);
    }

    private void initObjects() {
        this.textLabel = new Label();
        this.textLabel.setPrefSize(380, 50);
        this.textLabel.setLayoutX(0);
        this.textLabel.setLayoutY(0);
        this.textLabel.setVisible(true);
        this.textLabel.setAlignment(Pos.CENTER);
        this.textLabel.setStyle("-fx-font-family: 'Britannic Bold'; -fx-font-size: 30");

        this.toMainMenuButton = new Button();
        this.toMainMenuButton.setPrefSize(100, 40);
//        this.toMainMenuButton.setStyle("-fx-background-color: white; -fx-font-family: 'Britannic Bold'; -fx-font-size: 14");
        this.toMainMenuButton.setStyle(MAIN_BUTTON_STYLE_DEFAULT);
        this.toMainMenuButton.setLayoutX(20);
        this.toMainMenuButton.setLayoutY(200);
        this.toMainMenuButton.setText("Main menu");


        this.newGameButton = new Button();
        this.newGameButton.setPrefSize(100, 40);
        this.newGameButton.setStyle(MAIN_BUTTON_STYLE_DEFAULT);
        this.newGameButton.setLayoutX(260);
        this.newGameButton.setLayoutY(200);
        this.newGameButton.setVisible(true);
        this.newGameButton.setText("New game");
    }

    private void initActions() {
        this.newGameButton.setOnMouseClicked(event -> {
            this.gameHandler.startNewGame();
            if (this.isPaneActive) {
                Timeline timeline = new Timeline();
                KeyValue keyValueEndPane = new KeyValue(this.translateYProperty(), -450, Interpolator.EASE_IN);
                KeyFrame keyFrameEndPane = new KeyFrame(Duration.seconds(0.2), keyValueEndPane);
                timeline.getKeyFrames().add(keyFrameEndPane);
                timeline.play();
                this.setPaneUnActive();
            }
        });

        this.toMainMenuButton.setOnMouseClicked(event -> {

        });

        this.toMainMenuButton.setOnMouseEntered(event -> {
            this.toMainMenuButton.setStyle(MAIN_BUTTON_STYLE_ON_HOVER_DEFAULT);
        });

        this.toMainMenuButton.setOnMouseExited(event -> {
            this.toMainMenuButton.setStyle(MAIN_BUTTON_STYLE_DEFAULT);
        });

        this.newGameButton.setOnMouseEntered(event -> {
            this.newGameButton.setStyle(MAIN_BUTTON_STYLE_ON_HOVER_DEFAULT);
        });

        this.newGameButton.setOnMouseExited(event -> {
            this.newGameButton.setStyle(MAIN_BUTTON_STYLE_DEFAULT);
        });
    }

    public void setPaneActive() {
        this.isPaneActive = true;
    }

    public void setPaneUnActive() {
        this.isPaneActive = false;
    }
}
