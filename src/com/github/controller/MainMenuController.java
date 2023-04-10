package com.github.controller;

import com.github.cache.GameCache;
import com.github.models.GameParameters;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private Label height_label;

    @FXML
    private Pane main_pane;

    @FXML
    private TextField mines_quantity_input_box;

    @FXML
    private Label mines_quantity_label;

    @FXML
    private Button mines_quantity_minus_button;

    @FXML
    private Button mines_quantity_plus_button;

    @FXML
    private TextField pane_height_input_box;

    @FXML
    private Button pane_height_minus;

    @FXML
    private Button pane_height_plus;

    @FXML
    private TextField pane_width_input_box;

    @FXML
    private Button pane_width_minus;

    @FXML
    private Button pane_width_plus;

    @FXML
    private AnchorPane parent_pane;

    @FXML
    private Button start_button;

    @FXML
    private Label width_label;

    @FXML
    void initialize() {
        if (GameCache.GAME_PARAMETERS == null) {
            this.pane_width_input_box.setText("50");
            this.pane_height_input_box.setText("50");
            this.mines_quantity_input_box.setText("300");
        } else {
            this.pane_width_input_box.setText(String.valueOf(GameCache.GAME_PARAMETERS.getPaneWidth()));
            this.pane_height_input_box.setText(String.valueOf(GameCache.GAME_PARAMETERS.getPaneHeight()));
            this.mines_quantity_input_box.setText(String.valueOf(GameCache.GAME_PARAMETERS.getBombsQuantity()));
        }

        this.pane_height_minus.setOnAction(actionEvent -> {
            int paneHeight = Integer.parseInt(pane_height_input_box.getText());
            if (paneHeight <= 0) {
                return;
            }
                paneHeight--;
                pane_height_input_box.setText(String.valueOf(paneHeight));
        });

        this.pane_height_plus.setOnAction(actionEvent -> {
            int paneHeight = Integer.parseInt(pane_height_input_box.getText());
            if (paneHeight < 201) {
                paneHeight++;
                pane_height_input_box.setText(String.valueOf(paneHeight));
            }
        });

        this.pane_width_minus.setOnAction(actionEvent -> {
            int paneHeight = Integer.parseInt(pane_width_input_box.getText());
            if (paneHeight <= 0) {
                return;
            }
            paneHeight--;
            pane_width_input_box.setText(String.valueOf(paneHeight));
        });

        this.pane_width_plus.setOnAction(actionEvent -> {
            int paneHeight = Integer.parseInt(pane_width_input_box.getText());
            if (paneHeight < 201) {
                paneHeight++;
                pane_width_input_box.setText(String.valueOf(paneHeight));
            }
        });

        this.mines_quantity_minus_button.setOnAction(actionEvent -> {
            int paneHeight = Integer.parseInt(mines_quantity_input_box.getText());
            if (paneHeight <= 0) {
                return;
            }
            paneHeight--;
            mines_quantity_input_box.setText(String.valueOf(paneHeight));
        });

        this.mines_quantity_plus_button.setOnAction(actionEvent -> {
            int paneHeight = Integer.parseInt(mines_quantity_input_box.getText());
            if (paneHeight < 1000) {
                paneHeight++;
                mines_quantity_input_box.setText(String.valueOf(paneHeight));
            }
        });
    }

    @FXML
    public void loadGame(ActionEvent actionEvent) {

        int heightValue, widthValue, bombsQuantity;

        try {
            heightValue = Integer.parseInt(this.pane_height_input_box.getText());
            widthValue = Integer.parseInt(this.pane_width_input_box.getText());
            bombsQuantity = Integer.parseInt(this.mines_quantity_input_box.getText());
        } catch (NumberFormatException e) {
            System.out.println("Parse error!");
            System.out.println(e.getMessage());
            return;
        }

        if ((heightValue * widthValue) < bombsQuantity) {
            System.out.println("Bombs quantity much more than field size");
            //TODO show error message on panel
            return;
        }
        GameParameters parameters = new GameParameters();
        parameters.setPaneHeight(heightValue);
        parameters.setPaneWidth(widthValue);
        parameters.setBombsQuantity(bombsQuantity);
        GameCache.GAME_PARAMETERS = parameters;

        processToGameBoard();
    }

    private void processToGameBoard() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/github/game_board.fxml"));
        } catch (IOException e) {
            System.out.println("Root was not loaded for path \"game_board.fxml\"");
            System.out.println("Message: " + e.getMessage());
            return;
        }
        Scene scene = this.start_button.getScene();
        root.translateXProperty().set(scene.getWidth());
        this.parent_pane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.2), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(move -> {
            this.parent_pane.getChildren().remove(this.main_pane);
        });
        timeline.play();
    }

}
