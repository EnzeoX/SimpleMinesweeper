package com.github.utils;

import com.github.models.CurrentGame;
import com.github.models.GameParameters;
import com.github.view.pane.CellPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameFieldGenerator {

    public static void generateField(GameParameters parameters, CellPane firstPressed, CurrentGame currentGame) {
        if (parameters == null) return;
        if (currentGame == null) return;
        int bombsTotal = currentGame.getBombsQuantity();

        List<CellPane> listOfFreeCells = new ArrayList<>();
        for (int i = 0; i < currentGame.getCells().size(); i++) {
            listOfFreeCells.addAll(currentGame.getCells().get(i));
        }

        while (bombsTotal != 0) {
            if (setBombOnField(listOfFreeCells, firstPressed, currentGame.getCells())) {
                bombsTotal--;
            }
        }
        for (int i = 0; i < currentGame.getCells().size(); i++) {
            for (int k = 0; k < currentGame.getCells().get(i).size(); k++) {
                CellPane cell = currentGame.getCells().get(i).get(k);
                if (k == currentGame.getCells().get(i).size() - 1) {
                    System.out.print("[" + (cell.isBomb() ? "X" : " ") + "]\n");
                } else {
                    System.out.print("[" + (cell.isBomb() ? "X" : " ") + "]");
                }
            }
        }
        currentGame.setGameStarted(true);
    }

    private static boolean setBombOnField(List<CellPane> listOfFreeCells, CellPane firstPressed, ArrayList<ArrayList<CellPane>> cells) {
        int randomValue = new Random().nextInt(listOfFreeCells.size());
        CellPane randomCell = listOfFreeCells.get(randomValue);
        if (randomCell.equals(firstPressed)) return false; // get new random if collected cell is equals first pressed
        if (randomCell.isBomb()) {
            return false;
        } else {
            cells.get(randomCell.getCellPositionY()).get(randomCell.getCellPositionX()).setBomb(true);
            listOfFreeCells.remove(randomCell);
            return true;
        }
    }
}
