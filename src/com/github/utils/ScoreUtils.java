package com.github.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.models.Score;
import com.github.models.ScoresData;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreUtils {

    private final String pathSeparator;
    private final String path;

    public ScoreUtils() {
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            this.pathSeparator = "\\";
        } else {
            this.pathSeparator = "/";
        }
        //        String path = "." + this.pathSeparator + "scores" + this.pathSeparator + "scores.json";
        this.path = "scores.json";
    }

    public List<Score> getScoresByAllParameters(int boardWidth, int boardHeight, int bombsQuantity, int time) {
        ScoresData scores = loadScores();
        if (scores == null || scores.getScoresList() == null) {
            return new ArrayList<>();
        }
        return scores.getScoresList()
                .stream()
                .filter(score -> score != null
                        && score.getFiledWidth() == boardWidth
                        && score.getFieldHeight() == boardHeight
                        && score.getBombsQuantity() == bombsQuantity
                        && score.getCompletionTime() == time)
                .collect(Collectors.toList());
    }

    public List<Score> getScoresByDate(LocalDate date) {
        if (date == null) {
            System.out.println("No date provided!");
            return null;
        }
        ScoresData scores = loadScores();
        if (scores == null || scores.getScoresList() == null) {
            return new ArrayList<>();
        }
        return scores.getScoresList()
                .stream()
                .filter(score -> score != null
                        && score.getScoreDate().equals(date.toString()))
                .collect(Collectors.toList());
    }

    public List<Score> getScoresByBombsQuantity(int bombsQuantity) {
        ScoresData scores = loadScores();
        if (scores == null || scores.getScoresList() == null) {
            return new ArrayList<>();
        }
        return scores.getScoresList()
                .stream()
                .filter(score -> score != null
                        && score.getBombsQuantity() == bombsQuantity)
                .collect(Collectors.toList());
    }

    public List<Score> getScoresByBoardSize(int width, int height) {
        ScoresData scores = loadScores();
        if (scores == null || scores.getScoresList() == null) {
            return new ArrayList<>();
        }
        return scores.getScoresList()
                .stream()
                .filter(score -> score != null
                        && score.getFiledWidth() == width && score.getFieldHeight() == height)
                .collect(Collectors.toList());
    }

    public ScoresData loadScores() {
        File jsonFile = new File(this.path);
        ScoresData data = null;
        try {
            data = new ObjectMapper().readValue(jsonFile, ScoresData.class);
        } catch (IOException e) {
            System.out.println("Error parsing json file: " + e.getMessage());
        }
        return data;
    }

    public void saveScores(Score score) {
        if (score == null) {
            System.out.println("No score provided!");
            return;
        }
        ScoresData scoresData = loadScores();
        if (scoresData == null) {
            scoresData = new ScoresData();
            scoresData.getScoresList().add(score);
        } else {
            scoresData.getScoresList().forEach(oneScore -> {
                if (oneScore == null) {
                    return;
                }
                if (oneScore.getFieldHeight() != score.getFieldHeight()
                        && oneScore.getFiledWidth() != score.getFiledWidth()
                        && oneScore.getBombsQuantity() != score.getBombsQuantity()) {
                    return;
                }
                if (oneScore.getCompletionTime() > score.getCompletionTime()) {
                    return;
                }
                oneScore.setCompletionTime(score.getCompletionTime());
                oneScore.setScoreDate(score.getScoreDate());
            });
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(this.path), scoresData);
        } catch (IOException e) {
            System.out.println("Error saving a file: " + e.getMessage());
        }
    }
}
