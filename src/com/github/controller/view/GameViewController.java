package com.github.controller.view;

import com.github.handler.GameHandler;
import com.github.models.ReturnData;
import com.github.view.anchors.game.EndGameBoard;
import com.github.view.pane.CellPane;
import com.github.view.pane.GameBoardTopPane;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class GameViewController {

    private final GameHandler gameHandler;
    private final AnchorPane parentPane;

    public GameViewController(AnchorPane parent, GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.parentPane = parent;
    }

    public void processCell(CellPane cellPane, MouseEvent mouseEvent) {
        if (cellPane == null) {
            return;
        }
        if (!this.gameHandler.isGameEnded()) {
            ReturnData returnData = this.gameHandler.processCell(cellPane, mouseEvent);
            switch (returnData.getEventType()) {
                case START_GAME:
                    System.out.println("Game started!");
                    setViewBombsCount(returnData.getValueData());
                case CONTINUE:
                    break;
                case WIN_GAME:
                    this.gameHandler.endGame();
                    viewGameWinPane();
                    break;
                case MARK:
                case UNMARK:
                    setViewBombsCount(returnData.getValueData());
                    break;
                case GAME_OVER:
                    this.gameHandler.endGame();
                    viewEndGamePane();
                    break;
                case NO_CELL:
                    System.out.println("No cell provided!");
                default:
                    break;
            }
        }
    }

    private void setViewBombsCount(int value) {
        GameBoardTopPane topPane = (GameBoardTopPane) this.parentPane.lookup("#game-board-top-pane");
        topPane.setBombsCountValue(value);
    }

    private void viewEndGamePane() {
        EndGameBoard endGameBoard = (EndGameBoard) this.parentPane.lookup("#end-game-pane");
        endGameBoard.setBoardText("GAME OVER");
        setAnimationToPaneAndPlay(endGameBoard);
    }

    private void viewGameWinPane() {
        EndGameBoard endGameBoard = (EndGameBoard) this.parentPane.lookup("#end-game-pane");
        endGameBoard.setBoardText("YOU WIN");
        setAnimationToPaneAndPlay(endGameBoard);
    }

    private void setAnimationToPaneAndPlay(EndGameBoard endGameBoard) {
        double layoutValue = endGameBoard.getLayoutY() * (-1);
        System.out.println("EndGameBoard found: " + endGameBoard);
        Timeline timeline = new Timeline();
        KeyValue keyValueEndPane = new KeyValue(endGameBoard.translateYProperty(), layoutValue + 150, Interpolator.EASE_IN);
        KeyFrame keyFrameEndPane = new KeyFrame(Duration.seconds(0.3), keyValueEndPane);
        timeline.getKeyFrames().add(keyFrameEndPane);
        timeline.play();
        endGameBoard.setPaneActive();
    }
}
