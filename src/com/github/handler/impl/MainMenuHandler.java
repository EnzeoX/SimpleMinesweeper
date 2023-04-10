package com.github.handler.impl;

import com.github.cache.GameCache;
import com.github.controller.MainViewController;
import com.github.handler.IViewHandler;
import com.github.view.input.InputBox;
import com.github.view.input.main.MinesQuantityInputBox;
import com.github.view.input.main.PaneHeightInputBox;
import com.github.view.input.main.PaneWidthInputBox;
import com.github.view.pane.main.MainPane;
import javafx.event.Event;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MainMenuHandler implements IViewHandler {

    private final MainViewController mainViewController;

    private AnchorPane controlledPane = null;

    private String handlerName;

    public MainMenuHandler(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    @Override
    public void handleAction(String objectId, Event event) {
        if (this.controlledPane == null) {
            return;
        }
        switch (objectId) {
            case "MinesQuantityMinusButton":
                decreaseValueOf("main_panel_bombs_quantity_text_box");
                break;
            case "MinesQuantityPlusButton":
                increaseValueOf("main_panel_bombs_quantity_text_box");
                break;
            case "OptionsButton":
                this.mainViewController.changeView();
                break;
            case "PaneHeightMinusButton":
                decreaseValueOf("main_panel_height_text_box");
                break;
            case "PaneHeightPlusButton":
                increaseValueOf("main_panel_height_text_box");
                break;
            case "PaneWidthMinusButton":
                decreaseValueOf("main_panel_width_text_box");
                break;
            case "PaneWidthPlusButton":
                increaseValueOf("main_panel_width_text_box");
                break;
            case "StartButton":
                this.mainViewController.changeView();
                break;
            default:
                System.out.println("No button handler found for button with name " + objectId);
                break;
        }
    }

    @Override
    public void changeView(String changeTo) {
        this.mainViewController.changeView();
    }

    @Override
    public AnchorPane getCorrespondingView() {
        MainPane mainPane = new MainPane(this);
        this.controlledPane = mainPane;
        return mainPane;
    }

    @Override
    public void setHandlerName(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("No name provided for " + this.getClass().getSimpleName());
            return;
        }
        this.handlerName = name;
    }

    @Override
    public String getHandlerName() {
        return this.handlerName;
    }

    @Override
    public boolean canHandle(String handlerName) {
        return handlerName.equals(this.handlerName);
    }

    public void setValues() {
        InputBox<String> widthText = (PaneWidthInputBox) this.controlledPane.lookup("#main_panel_width_text_box");
        InputBox<String> heightText = (PaneHeightInputBox) this.controlledPane.lookup("#main_panel_height_text_box");
        InputBox<String> bombsQuantityText = (MinesQuantityInputBox) this.controlledPane.lookup("#main_panel_bombs_quantity_text_box");

        if (GameCache.GAME_PARAMETERS == null) {
            widthText.setValue("30");
            heightText.setValue("30");
            bombsQuantityText.setValue("100");
        } else {
            widthText.setValue(String.valueOf(GameCache.GAME_PARAMETERS.getPaneWidth()));
            heightText.setValue(String.valueOf(GameCache.GAME_PARAMETERS.getPaneHeight()));
            bombsQuantityText.setValue(String.valueOf(GameCache.GAME_PARAMETERS.getBombsQuantity()));
        }
    }

    private Object getObjectById(String id) {
        if (this.controlledPane == null) {
            return null;
        }
        return this.controlledPane.lookup("#" + id);
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

}
