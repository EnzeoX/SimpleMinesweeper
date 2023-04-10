package com.github.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Score {

    public Score() {}

    @JsonProperty("fieldWidth")
    private int filedWidth;

    @JsonProperty("fieldHeight")
    private int fieldHeight;

    @JsonProperty("completionTime")
    private int completionTime;

    @JsonProperty("bombsQuantity")
    private int bombsQuantity;

    @JsonProperty("scoreDate")
    private String scoreDate;

    public int getFiledWidth() {
        return filedWidth;
    }

    public void setFiledWidth(int filedWidth) {
        this.filedWidth = filedWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public void setFieldHeight(int fieldHeight) {
        this.fieldHeight = fieldHeight;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getBombsQuantity() {
        return bombsQuantity;
    }

    public void setBombsQuantity(int bombsQuantity) {
        this.bombsQuantity = bombsQuantity;
    }

    public String getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(String scoreDate) {
        this.scoreDate = scoreDate;
    }

    @Override
    public String toString() {
        return "Score [" +
                "filedWidth=" + filedWidth +
                ", fieldHeight=" + fieldHeight +
                ", completionTime=" + completionTime +
                ", bombsQuantity=" + bombsQuantity +
                ", scoreDate='" + scoreDate + '\'' +
                ']';
    }
}
