package com.github.handler;

import com.github.cache.GameCache;
import com.github.models.CurrentGame;
import com.github.models.ReturnData;
import com.github.utils.GameFieldGenerator;
import com.github.view.pane.CellPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class GameHandler {

    private final CurrentGame currentGame;

    public void setStartBombsQuantity(int bombsQuantity) {
        this.currentGame.setBombsQuantity(bombsQuantity);
    }

    public void initFreeCellsQuantity() {
        this.currentGame.setFreeCellsQuantity();
    }

    public int getBombsQuantity() {
        return this.currentGame.getBombsQuantity();
    }

    public void setCells(ArrayList<ArrayList<CellPane>> listOfCells) {
        this.currentGame.setCells(listOfCells);
    }

    public GameHandler(CurrentGame currentGame) {
        this.currentGame = currentGame;
    }

    //make methods to process cell which is not bomb and doesn't have bombs near
    // and one method to process cell which is has bombs near

    private void processNextOrOpenCell(int x, int y, CellPane currentCell) {
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (hasBombsNearBy(this.currentGame.getCells().get(y).get(x)) && !this.currentGame.getCells().get(y).get(x).isCellOpened()
//                && !this.currentGame.getCells().get(y).get(x).isCellMarked()
        ) {
            if (!this.currentGame.getCells().get(y).get(x).isBomb()) {
                openCell(this.currentGame.getCells().get(y).get(x));
            } else {
                processNext(this.currentGame.getCells().get(y).get(x), currentCell);
            }
        } else {
            if (!this.currentGame.getCells().get(y).get(x).isBomb() && !this.currentGame.getCells().get(y).get(x).isCellOpened()
//                    && !this.currentGame.getCells().get(y).get(x).isCellMarked()
            ) {
                processNext(this.currentGame.getCells().get(y).get(x), currentCell);
            }
        }
    }

    private void processCellWithBombsNear(CellPane currentCell, CellPane previousCell) {
        openCell(currentCell);

        int valueYMax = 0;
        int valueYMin = 0;

        int valueXMax = 0;
        int valueXMin = 0;

        if (currentCell.getCellPositionX() + 1 != this.currentGame.getCells().get(0).size()) {
            valueXMax = 1;
        }
        if (currentCell.getCellPositionX() - 1 > 0) {
            valueXMin = 1;
        }

        if (currentCell.getCellPositionY() + 1 != this.currentGame.getCells().size()) {
            valueYMax = 1;
        }
        if (currentCell.getCellPositionY() - 1 > 0) {
            valueYMin = 1;
        }

        if (currentCell.getCellPositionX() == previousCell.getCellPositionX() && currentCell.getCellPositionY() - 1 == previousCell.getCellPositionY()) { // 1
            processNextOrOpenCell(currentCell.getCellPositionX() + valueXMax, currentCell.getCellPositionY(), currentCell);
            processNextOrOpenCell(currentCell.getCellPositionX() - valueXMin, currentCell.getCellPositionY(), currentCell);
        } else if (currentCell.getCellPositionX() - 1 == previousCell.getCellPositionX() && currentCell.getCellPositionY() == previousCell.getCellPositionY()) { // 2
            processNextOrOpenCell(currentCell.getCellPositionX(), currentCell.getCellPositionY() - valueYMax, currentCell);
            processNextOrOpenCell(currentCell.getCellPositionX(), currentCell.getCellPositionY() + valueYMax, currentCell);
        } else if (currentCell.getCellPositionX() + 1 == previousCell.getCellPositionX() && currentCell.getCellPositionY() == previousCell.getCellPositionY()) { // 3
            processNextOrOpenCell(currentCell.getCellPositionX(), currentCell.getCellPositionY() - valueYMin, currentCell);
            processNextOrOpenCell(currentCell.getCellPositionX(), currentCell.getCellPositionY() + valueYMax, currentCell);
        } else if (currentCell.getCellPositionX() == previousCell.getCellPositionX() && currentCell.getCellPositionY() + 1 == previousCell.getCellPositionY()) { // 4
            processNextOrOpenCell(currentCell.getCellPositionX() + valueXMax, currentCell.getCellPositionY(), currentCell);
            processNextOrOpenCell(currentCell.getCellPositionX() - valueXMin, currentCell.getCellPositionY(), currentCell);
        }
    }

    public void processNext(CellPane current, CellPane previous) {
        // get coordinates of cells in cross-pattern
        //        []                 (x, y - 1)
        //      [][][]  (x - 1, y) (x, y (next)) (x + 1, y)
        //        []                 (x, y + 1)

        openCell(current);

        int maxX = this.currentGame.getCells().get(0).size() - 1;
        int maxY = this.currentGame.getCells().size() - 1;

        int zeroCoordinateX = current.getCellPositionX();
        int zeroCoordinateY = current.getCellPositionY();

        int startCoordinateX = zeroCoordinateX - 1;
        if (startCoordinateX < 0) startCoordinateX = 0;
        int startCoordinateY = zeroCoordinateY - 1;
        if (startCoordinateY < 0) startCoordinateY = 0;

        int endCoordinateX = zeroCoordinateX + 1;
        if (endCoordinateX > this.currentGame.getCells().get(0).size() - 1)
            endCoordinateX = this.currentGame.getCells().get(0).size() - 1;
        int endCoordinateY = zeroCoordinateY + 1;
        if (endCoordinateY > this.currentGame.getCells().size() - 1)
            endCoordinateY = this.currentGame.getCells().size() - 1;
        ArrayList<CellPane> cellsWithBombsNear = new ArrayList<>();
        ArrayList<CellPane> clearCells = new ArrayList<>();
        //Make loop if not lazy
        for (int y = startCoordinateY; y <= endCoordinateY; y++) {
            for (int x = startCoordinateX; x <= endCoordinateX; x++) {
                if (zeroCoordinateX == 0 && zeroCoordinateY == 0) {
                    if (x == 0 && y == 1) {
                        getCellPaneByCoordinatesNotBomb(x, y, current, cellsWithBombsNear, clearCells);
                        break;
                    } else {
                        getCellPaneByCoordinatesNotBomb(x, y, current, cellsWithBombsNear, clearCells);
                    }
                } else if (zeroCoordinateX == 0 && (zeroCoordinateY > 0 && zeroCoordinateY < maxY)) {
                    if (x == 0 && y == startCoordinateY) {
                        getCellPaneByCoordinatesNotBomb(x, y, current, cellsWithBombsNear, clearCells);
                        break;
                    } else if ((y == endCoordinateY) && x == 0) {
                        getCellPaneByCoordinatesNotBomb(x, y, current, cellsWithBombsNear, clearCells);
                        break;
                    } else {
                        getCellPaneByCoordinatesNotBomb(x, y, current, cellsWithBombsNear, clearCells);
                    }
                } else if (zeroCoordinateY == 0 && zeroCoordinateX == this.currentGame.getCells().get(y).size()) {
                    if (x == maxX - 1 && y == 1) {
                        getCellPaneByCoordinatesNotBomb(x + 1, y, current, cellsWithBombsNear, clearCells);
                        break;
                    } else {
                        getCellPaneByCoordinatesNotBomb(x, y, current, cellsWithBombsNear, clearCells);
                    }
                } else if (zeroCoordinateX == 0 && zeroCoordinateY == maxY) {
                    if (x == 0 && y == maxY - 1) {
                        getCellPaneByCoordinatesNotBomb(x, y, current, cellsWithBombsNear, clearCells);
                        break;
                    } else {
                        getCellPaneByCoordinatesNotBomb(x, y, current, cellsWithBombsNear, clearCells);
                    }
                } else if (zeroCoordinateX == maxX && zeroCoordinateY == maxY) {
                    if (x == maxX - 1 && y == maxY - 1) {
                        getCellPaneByCoordinatesNotBomb(x + 1, y, current, cellsWithBombsNear, clearCells);
                        break;
                    } else {
                        getCellPaneByCoordinatesNotBomb(x, y, current, cellsWithBombsNear, clearCells);
                    }
                } else if (zeroCoordinateX == maxX && zeroCoordinateY == 1) { // center coordinates ()
                    if ((x == maxX - 1) && (y == 0 || y == 2)) {
                        getCellPaneByCoordinatesNotBomb(x + 1, y, current, cellsWithBombsNear, clearCells);
                        break;
                    } else {
                        getCellPaneByCoordinatesNotBomb(x, y, current, cellsWithBombsNear, clearCells);
                    }
                } else if (((x > 0 || x < maxX) && zeroCoordinateY == 0)) {
                    if (y == 1) {
                        getCellPaneByCoordinatesNotBomb(x + 1, y, current, cellsWithBombsNear, clearCells);
                        break;
                    } else {
                        getCellPaneByCoordinatesNotBomb(x, y, current, cellsWithBombsNear, clearCells);
                    }
                } else if (((x > 0 || x < maxX) && zeroCoordinateY == maxY)) {
                    if (y == maxY - 1) {
                        getCellPaneByCoordinatesNotBomb(x + 1, y, current, cellsWithBombsNear, clearCells);
                        break;
                    } else {
                        getCellPaneByCoordinatesNotBomb(x, y, current, cellsWithBombsNear, clearCells);
                    }
                } else {
                    if (x == startCoordinateX && (y == startCoordinateY || y == endCoordinateY)) {
                        getCellPaneByCoordinatesNotBomb(x + 1, y, current, cellsWithBombsNear, clearCells);
                        break;
                    } else {
                        getCellPaneByCoordinatesNotBomb(x, y, current, cellsWithBombsNear, clearCells);
                    }
                }
            }
        }
        if (!current.isHasBombsNear()) {
            if (clearCells.size() != 0) {
                for (CellPane cell : clearCells) {
                    processNext(cell, current);
                }
            }

            if (cellsWithBombsNear.size() != 0) {
                for (CellPane cellPane : cellsWithBombsNear) {
                    processCellWithBombsNear(cellPane, current);
                }
            }
        }
    }


    private void getCellPaneByCoordinatesNotBomb(int coordinateX, int coordinateY, CellPane currentCell, ArrayList<CellPane> cellsWithBombsNear, ArrayList<CellPane> clearCells) {
        if (!this.currentGame.getCells().get(coordinateY).get(coordinateX).isBomb()
                && !this.currentGame.getCells().get(coordinateY).get(coordinateX).isCellOpened()
                && !this.currentGame.getCells().get(coordinateY).get(coordinateX).equals(currentCell)) {
            if (getNearBombsCount(this.currentGame.getCells().get(coordinateY).get(coordinateX), false) > 0) {
                if (cellsWithBombsNear != null) {
                    cellsWithBombsNear.add(this.currentGame.getCells().get(coordinateY).get(coordinateX));
                }
            } else {
                if (clearCells != null) {
                    clearCells.add(this.currentGame.getCells().get(coordinateY).get(coordinateX));
                }
            }
        }
    }


    public ReturnData processCell(CellPane cellPane, MouseEvent event) {
        ReturnData returnData = new ReturnData();
        if (!this.currentGame.isGameEnded()) {
            if (cellPane == null) {
                System.out.println("No cellPane provided!");
                return null;
            }
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (!this.currentGame.isGameStarted()) {
                    GameFieldGenerator.generateField(GameCache.GAME_PARAMETERS, cellPane, this.currentGame);
                    processNext(cellPane, cellPane);
                    this.currentGame.startGame();
                    returnData.setEventType(ReturnData.EventType.START_GAME);
                    returnData.setValueData(getBombsQuantity());
                } else {
                    if (!cellPane.isCellMarked() && !cellPane.isCellOpened()) {
                        if (cellPane.isBomb()) {
                            //TODO END GAME
                            System.out.println("GAME OVER, IT'S BOMB CELL");
                            openCell(cellPane);
                            returnData.setEventType(ReturnData.EventType.GAME_OVER);
                        } else {
                            processNext(cellPane, cellPane);
                            if (this.currentGame.getNotOpenedCells() == 0) {
                                returnData.setEventType(ReturnData.EventType.WIN_GAME);
                            } else {
                                returnData.setEventType(ReturnData.EventType.CONTINUE);
                            }
                        }
                    }
                }
            } else if (event.getButton().equals(MouseButton.SECONDARY)) {
                if (!cellPane.isCellOpened()) {
                    if (!cellPane.isCellMarked()) {
                        returnData.setEventType(ReturnData.EventType.MARK);
                        returnData.setValueData(markCell(cellPane));
                    } else {
                        returnData.setValueData(unMarkCell(cellPane));
                        returnData.setEventType(ReturnData.EventType.UNMARK);
                    }
                }
            } else {
                returnData.setEventType(ReturnData.EventType.CONTINUE);
            }
        }
        return returnData;
    }

    public void endGame() {
        this.currentGame.endGame();
    }

    private void openCell(CellPane cellPane) {
        if (cellPane.isCellMarked()) {
            this.currentGame.increaseBombs();
        }
        if (!cellPane.isBomb() && !cellPane.isCellOpened()) {
            this.currentGame.decreaseNotOpened();
        }
        cellPane.setOpened();
        cellPane.setNearBombsText(getNearBombsCount(cellPane, false));
    }

    public int getNearBombsCount(CellPane cellPane, boolean openNearCells) {
        int bombsCountNear = 0;

        int startPositionY = cellPane.getCellPositionY() - 1;
        if (startPositionY < 0) startPositionY = 0;

        int startPositionX = cellPane.getCellPositionX() - 1;
        if (startPositionX < 0) startPositionX = 0;

        int endPositionY = cellPane.getCellPositionY() + 1;
        if (endPositionY > this.currentGame.getCells().size() - 1) {
            endPositionY = this.currentGame.getCells().size() - 1;
        }

        int endPositionX = cellPane.getCellPositionX() + 1;
        if (endPositionX > this.currentGame.getCells().get(0).size() - 1) {
            endPositionX = this.currentGame.getCells().get(0).size() - 1;
        }

        for (int i = startPositionY; i < endPositionY + 1; i++) {
            for (int k = startPositionX; k < endPositionX + 1; k++) {
                if (this.currentGame.getCells().get(i).get(k).isBomb() && !cellPane.equals(this.currentGame.getCells().get(i).get(k))) {
                    bombsCountNear++;
                    if (openNearCells) {
                        if (!this.currentGame.getCells().get(i).get(k).isCellOpened() && !this.currentGame.getCells().get(i).get(k).isCellMarked()) {
                            openCell(cellPane);
                        }
                    }
                }
            }
        }
        return bombsCountNear;
    }

    public int markCell(CellPane cellPane) {
        if (!cellPane.isCellMarked()) {
            cellPane.setCellAsMarked();
            this.currentGame.decreaseBombs();
        }
        return this.currentGame.getBombsLeft();
    }

    public int unMarkCell(CellPane cellPane) {
        if (cellPane.isCellMarked()) {
            cellPane.setCellAsUnmarked();
            this.currentGame.increaseBombs();
        }
        return this.currentGame.getBombsLeft();
    }

    public boolean hasBombsNearBy(CellPane cellPane) {
        return getNearBombsCount(cellPane, false) != 0;
    }

    public boolean isGameEnded() {
        return this.currentGame.isGameEnded();
    }

    public void startNewGame() {
        this.currentGame.reset();
    }
}
