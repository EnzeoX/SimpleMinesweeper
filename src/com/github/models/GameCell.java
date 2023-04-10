package com.github.models;

public class GameCell {

    private boolean isBombCell = false;
    private boolean isOpened = false;
    private int positionX = 0;
    private int positionY = 0;

    public boolean isBombCell() {
        return isBombCell;
    }

    public void setBombCell(boolean bombCell) {
        isBombCell = bombCell;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
