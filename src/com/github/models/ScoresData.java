package com.github.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoresData {

    public ScoresData() {
        this.scoresList = new ArrayList<>();
    }

    private List<Score> scoresList;

    public List<Score> getScoresList() {
        return this.scoresList;
    }

    public void setScoresList(List<Score> scoresList) {
        if (scoresList == null) {
            System.out.println("No scores provided to set");
            return;
        }
        this.scoresList = scoresList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Scores data:").append("\nSize: ");
        if (this.scoresList != null) {
            sb.append(this.scoresList.size());
            sb.append("\n").append("Scores list data:\n");
            for (Score score : this.scoresList) {
                sb.append(score.toString());
                sb.append("\n");
            }
        } else {
            sb.append("0");
        }
        return sb.toString();
    }
}
