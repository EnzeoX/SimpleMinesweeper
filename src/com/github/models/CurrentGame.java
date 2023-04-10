package com.github.models;

import com.github.view.pane.CellPane;
import com.github.view.pane.TimerPane;

import java.util.ArrayList;

public class CurrentGame {

    private boolean isGameStarted = false;

    private boolean isGameEnded = false;

    private int bombsLeft;

    private int bombsQuantity;

    private TimerPane stopWatch;

    private int notOpenedCells;

    private ArrayList<ArrayList<CellPane>> cells;

    public CurrentGame(TimerPane timerPane) {
        this.stopWatch = timerPane;
    }

    public void startGame() {
        setGameStarted(true);
        startTimer();
    }

    public void startTimer() {
        this.stopWatch.startTimer();
    }

    public void pauseTime() {
        this.stopWatch.pauseTimer();
    }

    public void resumeTime() {
        this.stopWatch.resumeTimer();
    }

    public void stopTime() {
        this.stopWatch.stopTimer();
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        isGameStarted = gameStarted;
    }

    public int getBombsLeft() {
        return bombsLeft;
    }

    public void setBombsLeft(int bombsLeft) {
        this.bombsLeft = bombsLeft;
    }

    public void decreaseBombs() {
        this.bombsLeft--;
//        this.gameBoardTopPane.setBombsCountValue(this.bombsLeft);
    }

    public void increaseBombs() {
        this.bombsLeft++;
//        this.gameBoardTopPane.setBombsCountValue(this.bombsLeft);
    }

    public void decreaseNotOpened() {
        this.notOpenedCells--;
    }

    public int getNotOpenedCells() {
        return this.notOpenedCells;
    }

    public int getBombsQuantity() {
        return bombsQuantity;
    }

    public void setBombsQuantity(int bombsQuantity) {
        this.bombsQuantity = bombsQuantity;
        this.bombsLeft = bombsQuantity;
    }

    public void setFreeCellsQuantity() {
        if (getCells() != null) {
            this.notOpenedCells = (getCells().size() * getCells().get(0).size()) - getBombsQuantity();
        }
    }

    public ArrayList<ArrayList<CellPane>> getCells() {
        return cells;
    }

    public void setCells(ArrayList<ArrayList<CellPane>> cells) {
        this.cells = cells;
    }

    public void setStopWatch(TimerPane timerPane) {
        this.stopWatch = timerPane;
    }

    public boolean isGameEnded() {
        return this.isGameEnded;
    }

    private void setGameStatus(boolean value) {
        this.isGameEnded = value;
    }

    public void endGame() {
        this.stopTime();
        setGameStatus(true);
    }

    public void reset() {
        this.stopWatch.reset();
        this.isGameEnded = false;
        this.isGameStarted = false;
        setBombsQuantity(getBombsQuantity());
        for (int i = 0; i < getCells().size(); i++) {
            for (int k = 0; k < getCells().get(i).size(); k++) {
                getCells().get(i).get(k).resetCell();
            }
        }
        setFreeCellsQuantity();
    }
}
