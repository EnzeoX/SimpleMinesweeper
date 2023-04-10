package com.github.handler;

import com.github.cache.GameCache;
import com.github.models.GameParameters;
import com.github.models.ViewCommand;
import com.github.view.buttons.ButtonInterface;
import com.github.view.input.InputBox;
import com.github.view.input.main.MinesQuantityInputBox;
import com.github.view.input.main.PaneHeightInputBox;
import com.github.view.input.main.PaneWidthInputBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class StartHandler  {

    private AnchorPane parent;
    private String handlerName;

    public StartHandler(AnchorPane parent) {
        this.parent = parent;
    }

    public ViewCommand handleAction(ButtonInterface button) {
        ViewCommand command = new ViewCommand();
        switch (button.getButtonName()) {
            case "minesQuantityMinusButton":
                decreaseValueOf("main_panel_bombs_quantity_text_box");
                command.setVariant(ViewCommand.ViewCommandVariants.CONTINUE);
                break;
            case "minesQuantityPlusButton":
                increaseValueOf("main_panel_bombs_quantity_text_box");
                command.setVariant(ViewCommand.ViewCommandVariants.CONTINUE);
                break;
            case "optionsButton":
                break;
            case "paneHeightMinusButton":
                decreaseValueOf("main_panel_height_text_box");
                command.setVariant(ViewCommand.ViewCommandVariants.CONTINUE);
                break;
            case "paneHeightPlusButton":
                increaseValueOf("main_panel_height_text_box");
                command.setVariant(ViewCommand.ViewCommandVariants.CONTINUE);
                break;
            case "paneWidthMinusButton":
                decreaseValueOf("main_panel_width_text_box");
                command.setVariant(ViewCommand.ViewCommandVariants.CONTINUE);
                break;
            case "paneWidthPlusButton":
                increaseValueOf("main_panel_width_text_box");
                command.setVariant(ViewCommand.ViewCommandVariants.CONTINUE);
                break;
            case "startButton":
                loadGame();
                command.setVariant(ViewCommand.ViewCommandVariants.CHANGE_VIEW_GAME);
                break;
            default:
                System.out.println("No button handler found for button with name " + button.getButtonName());
                command.setVariant(ViewCommand.ViewCommandVariants.NONE);
                break;
        }
        return command;
    }

    private Object getObjectById(String id) {
        return this.parent.lookup("#" + id);
    }

    private void increaseValueOf(String id) {
        TextField textField = (TextField) getObjectById(id);
        int value = Integer.parseInt(textField.getText());
        if (value < 1000) {
            value++;
            textField.setText(String.valueOf(value));
        }
    }

    private void decreaseValueOf(String id) {
        TextField textField = (TextField) getObjectById(id);
        int value = Integer.parseInt(textField.getText());
        if (value <= 0) {
            return;
        }
        value--;
        textField.setText(String.valueOf(value));
    }

    public void loadGame() {
        int heightValue, widthValue, bombsQuantity;

        InputBox<String> widthText = (PaneWidthInputBox) this.parent.lookup("#main_panel_width_text_box");
        InputBox<String> heightText = (PaneHeightInputBox) this.parent.lookup("#main_panel_height_text_box");
        InputBox<String> bombsQuantityText = (MinesQuantityInputBox) this.parent.lookup("#main_panel_bombs_quantity_text_box");

        try {
            heightValue = Integer.parseInt(widthText.getValue());
            widthValue = Integer.parseInt(heightText.getValue());
            bombsQuantity = Integer.parseInt(bombsQuantityText.getValue());
        } catch (NumberFormatException e) {
            System.out.println("Parse error!");
            System.out.println(e.getMessage());
            return;
        }

        if ((heightValue * widthValue) < bombsQuantity) {
            System.out.println("Bombs quantity much more than field size");
            //TODO show error message on panel
            return;
        }
        GameParameters parameters = new GameParameters();
        parameters.setPaneHeight(heightValue);
        parameters.setPaneWidth(widthValue);
        parameters.setBombsQuantity(bombsQuantity);
        GameCache.GAME_PARAMETERS = parameters;
    }
}
