package com.github.view.pane.main;

import com.github.controller.MainViewController;
import com.github.handler.StartHandler;
import com.github.handler.impl.MainMenuHandler;
import com.github.view.buttons.main.*;
import com.github.view.input.main.MinesQuantityInputBox;
import com.github.view.input.main.PaneHeightInputBox;
import com.github.view.input.main.PaneWidthInputBox;
import com.github.view.labels.main.HeightLabel;
import com.github.view.labels.main.MainTopLabel;
import com.github.view.labels.main.MinesQuantityLabel;
import com.github.view.labels.main.WidthLabel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainPane extends AnchorPane {

    private Pane mainPane;

    private Label topLabel;

    private Label widthLabel;
    private TextField paneWidthInputBox;
    private Button paneWidthPlusButton;
    private Button paneWidthMinusButton;

    private Label heightLabel;
    private TextField paneHeightInputBox;
    private Button paneHeightPlusButton;
    private Button paneHeightMinusButton;

    private Label minesQuantityLabel;
    private TextField minesQuantityInputBox;
    private Button minesQuantityPlusButton;
    private Button minesQuantityMinusButton;

    private Button startButton;

    private Button scoreBoardButton;

    private MainMenuHandler handler;

    public MainPane(MainMenuHandler mainMenuHandler) {
        this.handler = mainMenuHandler;
        initScene();
    }

    private void initScene() {
        this.setPrefSize(480, 640);
        this.setStyle("-fx-background-color: #efefef;");
        this.setVisible(true);

        this.mainPane = new Pane();
        this.mainPane.setPrefSize(480,640);
        this.mainPane.setVisible(true);
        this.getChildren().add(this.mainPane);

        this.topLabel = new MainTopLabel();
        this.mainPane.getChildren().add(this.topLabel);

        this.widthLabel = new WidthLabel();
        this.mainPane.getChildren().add(this.widthLabel);

        this.paneWidthInputBox = new PaneWidthInputBox();
        this.mainPane.getChildren().add(this.paneWidthInputBox);

        this.paneWidthPlusButton = new PaneWidthPlusButton(this.handler);
        this.mainPane.getChildren().add(this.paneWidthPlusButton);

        this.paneWidthMinusButton = new PaneWidthMinusButton(this.handler);
        this.mainPane.getChildren().add(this.paneWidthMinusButton);

        this.heightLabel = new HeightLabel();
        this.mainPane.getChildren().add(this.heightLabel);

        this.paneHeightInputBox = new PaneHeightInputBox();
        this.mainPane.getChildren().add(this.paneHeightInputBox);

        this.paneHeightPlusButton = new PaneHeightPlusButton(this.handler);
        this.mainPane.getChildren().add(this.paneHeightPlusButton);

        this.paneHeightMinusButton = new PaneHeightMinusButton(this.handler);
        this.mainPane.getChildren().add(this.paneHeightMinusButton);

        this.minesQuantityLabel = new MinesQuantityLabel();
        this.mainPane.getChildren().add(this.minesQuantityLabel);

        this.minesQuantityInputBox = new MinesQuantityInputBox();
        this.mainPane.getChildren().add(this.minesQuantityInputBox);

        this.minesQuantityPlusButton = new MinesQuantityPlusButton(this.handler);
        this.mainPane.getChildren().add(this.minesQuantityPlusButton);

        this.minesQuantityMinusButton = new MinesQuantityMinusButton(this.handler);
        this.mainPane.getChildren().add(this.minesQuantityMinusButton);

        this.startButton = new StartButton(this.handler);
        this.mainPane.getChildren().add(this.startButton);

        this.scoreBoardButton = new ScoreBoardButton(this.handler);
        this.mainPane.getChildren().add(this.scoreBoardButton);

        this.handler.setValues();
    }
}
