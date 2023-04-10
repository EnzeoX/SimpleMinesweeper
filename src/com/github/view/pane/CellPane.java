package com.github.view.pane;

import com.github.controller.view.GameViewController;
import com.github.view.pane.other.BombPane;
import com.github.view.pane.other.MarkerPane;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

public class CellPane extends Pane {

    private final GameViewController gameViewController;

    private final Label nearBombsText;

    private MarkerPane markerPane = null;

    private BombPane bombPane = null;

    private int cellPositionX = 0;

    private int cellPositionY = 0;

    private boolean isBomb = false;

    private boolean isCellOpened = false;

    private boolean isCellMarked = false;

    private boolean hasBombsNear = false;

    public boolean isBomb() {
        return this.isBomb;
    }

    public void setBomb(boolean bomb) {
        this.isBomb = bomb;
    }

    public int getCellPositionX() {
        return cellPositionX;
    }

    public void setCellPositionX(int cellPositionX) {
        this.cellPositionX = cellPositionX;
    }

    public int getCellPositionY() {
        return cellPositionY;
    }

    public void setCellPositionY(int cellPositionY) {
        this.cellPositionY = cellPositionY;
    }

    public boolean isCellOpened() {
        return this.isCellOpened;
    }

    public void setCellOpened(boolean cellOpened) {
        isCellOpened = cellOpened;
    }

    public boolean isCellMarked() {
        return isCellMarked;
    }

    public void setCellMarked(boolean cellMarked) {
        isCellMarked = cellMarked;
    }

    public void setNearBombsText(String number) {
        this.nearBombsText.setText(number);
    }

    public void setNearBombsText(int number) {
        if (number <= 0) {
            setNearBombsText(" ");
        } else {
            this.setHasBombsNear(true);
            setNearBombsText(String.valueOf(number));
        }
    }

    public int getNearBombsText() {
        return Integer.parseInt(this.nearBombsText.getText());
    }

    public CellPane(GameViewController gameViewController) {
        this.gameViewController = gameViewController;
        this.nearBombsText = new Label();
        nearBombsText.setPrefSize(34, 34);
        nearBombsText.setStyle("-fx-font-family: 'Britannic Bold'; -fx-font-size: 15");
        nearBombsText.setVisible(true);
        nearBombsText.setAlignment(Pos.CENTER);
        this.getChildren().add(this.nearBombsText);

        this.setStyle(
                "-fx-background-color: radial-gradient(center 50% 50% , radius 100% , #cfcfcf, #737373);" +
                        "-fx-border-width: 1 1 1 1;" +
                        "-fx-border-color: #b7b9ba;" +
                        "-fx-border-radius: 4;" +
                        "-fx-background-radius: 4");
        this.setPrefSize(34, 34);
        this.setWidth(34);
        this.setHeight(34);
        this.setVisible(true);

        this.setOnMouseClicked(event -> {

        });

        this.setOnMouseReleased(event -> {
            if (event.isDragDetect()) {
                this.gameViewController.processCell(this, event);
            } else {
                if (!this.isCellOpened() && !this.isCellMarked()) {
                    this.setStyle(
                            "-fx-background-color: radial-gradient(center 50% 50% , radius 100% , #cfcfcf, #737373);" +
                                    "-fx-border-width: 1 1 1 1;" +
                                    "-fx-border-color: #b7b9ba;" +
                                    "-fx-border-radius: 4;" +
                                    "-fx-background-radius: 4");
                }
            }
        });

        this.setOnMousePressed(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (!isCellOpened && !isCellMarked) {
                    this.setStyle(
                            "-fx-background-color: white;" +
                                    "-fx-border-width: 1 1 1 1;" +
                                    "-fx-border-color: #b7b9ba;" +
                                    "-fx-border-radius: 4;" +
                                    "-fx-background-radius: 4");
                }
            }
        });

        this.setOnMouseEntered(event -> {
            if (!this.isCellOpened && !this.isCellMarked && !event.isPrimaryButtonDown()) {
                this.setStyle(
                        "-fx-background-color: #646464;" +
                                "-fx-border-width: 1 1 1 1;" +
                                "-fx-border-color: #b7b9ba;" +
                                "-fx-border-radius: 4;" +
                                "-fx-background-radius: 4");
            }
        });

        this.setOnMouseExited(event -> {
            if (!this.isCellOpened && !this.isCellMarked && !event.isPrimaryButtonDown()) {
                this.setStyle(
                        "-fx-background-color: radial-gradient(center 50% 50% , radius 100% , #cfcfcf, #737373);" +
                                "-fx-border-width: 1 1 1 1;" +
                                "-fx-border-color: #b7b9ba;" +
                                "-fx-border-radius: 4;" +
                                "-fx-background-radius: 4");
            }
        });

    }

    public void setCellAsMarked() {
        this.setCellMarked(true);
        this.setStyle(
                "-fx-background-color: #9cfaf5;" +
                        "-fx-border-width: 1 1 1 1;" +
                        "-fx-border-color: #b7b9ba;" +
                        "-fx-border-radius: 4;" +
                        "-fx-background-radius: 4");
        if (this.markerPane == null) {
            this.markerPane = new MarkerPane();
        }
        this.getChildren().add(this.markerPane);
    }

    public void setCellAsUnmarked() {
        this.nearBombsText.setText("");
        this.setCellMarked(false);
        this.setStyle(
                "-fx-background-color: radial-gradient(center 50% 50% , radius 100% , #cfcfcf, #737373);" +
                        "-fx-border-width: 1 1 1 1;" +
                        "-fx-border-color: #b7b9ba;" +
                        "-fx-border-radius: 4;" +
                        "-fx-background-radius: 4");
        this.getChildren().remove(this.markerPane);
    }

    public boolean isHasBombsNear() {
        return this.hasBombsNear;
    }

    public void setHasBombsNear(boolean value) {
        this.hasBombsNear = value;
    }

    public void resetCell() {
        if (this.isCellMarked()) {
            this.setCellAsUnmarked();
        }
        if (this.isCellOpened()) {
            closeCell();
        }
        this.hasBombsNear = false;
        this.nearBombsText.setText("");
        removeBomb();
    }

    private void closeCell() {
        if (this.isCellOpened()) {
            this.setCellOpened(false);
            this.setStyle(
                    "-fx-background-color: radial-gradient(center 50% 50% , radius 100% , #cfcfcf, #737373);" +
                            "-fx-border-width: 1 1 1 1;" +
                            "-fx-border-color: #b7b9ba;" +
                            "-fx-border-radius: 4;" +
                            "-fx-background-radius: 4");
        }
    }

    private void removeBomb() {
        if (this.isBomb()) {
            this.getChildren().remove(this.bombPane);
            this.isBomb = false;
        }
    }

    public void setOpened() {
        if (!this.isCellOpened()) {
            if (this.isCellMarked()) {
                this.setCellAsUnmarked();
            }
            this.setCellOpened(true);
            if (!this.isBomb()) {
                this.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-border-width: 1 1 1 1;" +
                                "-fx-border-color: #b7b9ba;" +
                                "-fx-border-radius: 4;" +
                                "-fx-background-radius: 4");
            } else {
                this.bombPane = new BombPane();
                this.getChildren().add(this.bombPane);
                this.setStyle(
                        "-fx-background-color: red;" +
                                "-fx-border-width: 1 1 1 1;" +
                                "-fx-border-color: #b7b9ba;" +
                                "-fx-border-radius: 4;" +
                                "-fx-background-radius: 4");
            }
        }
    }

    @Override
    public String toString() {
        return "CellPane{" +
                "nearBombsText=" + nearBombsText +
                ", cellPositionX=" + cellPositionX +
                ", cellPositionY=" + cellPositionY +
                ", isBomb=" + isBomb +
                ", isCellOpened=" + isCellOpened +
                ", isCellMarked=" + isCellMarked +
                ", hasBombsNear=" + hasBombsNear +
                '}';
    }
}
