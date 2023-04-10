package com.github.view.anchors.game;

import com.github.cache.GameCache;
import com.github.controller.view.GameViewController;
import com.github.handler.GameHandler;
import com.github.models.CurrentGame;
import com.github.view.pane.CellPane;
import com.github.view.pane.GameBoardTopPane;
import com.github.view.pane.scroll.GameScrollPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MainGamePane extends AnchorPane {

    private GameViewController gameViewController;

    private GameBoardMainBoard gameBoardMainBoard;

    private GameBoardTopPane gameBoardTopPane;

    private GameScrollPane gameScrollPane;

    private EndGameBoard endGameBoard;

    public MainGamePane() {
        initPane();
    }

    private void initPane() {

        this.setPrefSize(640, 480);
        this.setVisible(true);

        this.gameBoardTopPane = new GameBoardTopPane();
        this.getChildren().add(this.gameBoardTopPane);

        this.gameBoardMainBoard = new GameBoardMainBoard();
        this.getChildren().add(this.gameBoardMainBoard);

        GameHandler gameHandler = new GameHandler(
                new CurrentGame(this.gameBoardTopPane.getTimer()));

        this.endGameBoard = new EndGameBoard("", gameHandler);
        this.getChildren().add(this.endGameBoard);


        this.gameViewController = new GameViewController(this, gameHandler);
        this.gameScrollPane = new GameScrollPane();
        this.gameBoardMainBoard.getChildren().add(this.gameScrollPane);
        gameHandler.setStartBombsQuantity(GameCache.GAME_PARAMETERS.getBombsQuantity());

        int gridWidth = GameCache.GAME_PARAMETERS.getPaneWidth();
        int gridHeight = GameCache.GAME_PARAMETERS.getPaneHeight();

        ArrayList<ArrayList<CellPane>> verticalCoordinates = new ArrayList<>();

        VBox verticalLayout = new VBox();
        verticalLayout.setSpacing(2);
        verticalLayout.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.gameScrollPane.setContent(verticalLayout);
        this.gameScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.gameScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        for (int i = 0; i < gridHeight; i++) {
            HBox horizontalLayout = new HBox();
            horizontalLayout.setSpacing(2);
            ArrayList<CellPane> horizontalCoordinates = new ArrayList<>();
            verticalCoordinates.add(horizontalCoordinates);
            horizontalLayout.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
            for (int k = 0; k < gridWidth; k++) {
                CellPane cellPane = new CellPane(this.gameViewController);

                cellPane.setOnDragDetected(scrollEvent -> cellPane.setVisible(cellPane.getBoundsInParent().intersects(gameScrollPane.getViewportBounds())));

                cellPane.setCellPositionX(k);
                cellPane.setCellPositionY(i);
                horizontalCoordinates.add(cellPane);
                horizontalLayout.getChildren().add(cellPane);
            }
            verticalLayout.getChildren().add(horizontalLayout);
        }

        double height = gridHeight * 36 + 2;
        double width = gridWidth * 36 + 2;

        if (height >= this.gameBoardMainBoard.getHeight()) {
            this.gameScrollPane.setLayoutY(10);
            this.gameScrollPane.setPrefHeight(550);
        } else {
            double heightCalculated = (this.gameBoardMainBoard.getHeight() - height) / 2;
            this.gameScrollPane.setLayoutY(heightCalculated);
            this.gameScrollPane.setPrefHeight(height);
        }

        if (width >= this.gameBoardMainBoard.getWidth()) {
            this.gameScrollPane.setLayoutX(11);
            this.gameScrollPane.setPrefWidth(460);
        } else {
            double widthCalculated = (this.gameBoardMainBoard.getWidth() - width) / 2;
            this.gameScrollPane.setLayoutX(widthCalculated);
            this.gameScrollPane.setPrefWidth(width);
        }

        gameHandler.setCells(verticalCoordinates);
        gameHandler.initFreeCellsQuantity();
    }
}
