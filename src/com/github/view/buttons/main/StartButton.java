package com.github.view.buttons.main;

import com.github.controller.MainViewController;
import com.github.handler.impl.MainMenuHandler;
import com.github.view.buttons.ButtonInterface;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import static com.github.view.global.styles.ButtonsStyles.MAIN_BUTTON_STYLE_DEFAULT;
import static com.github.view.global.styles.ButtonsStyles.MAIN_BUTTON_STYLE_ON_HOVER_DEFAULT;

public class StartButton extends Button implements ButtonInterface {

    private String buttonName;
    private final MainMenuHandler mainMenuHandler;

    public StartButton(MainMenuHandler mainMenuHandler) {
        this.mainMenuHandler = mainMenuHandler;
        this.buttonName = this.getClass().getSimpleName();
        init();
        initActions();
    }

    private void init() {
        this.setPrefSize(200, 45);
        this.setLayoutX(151);
        this.setLayoutY(310);
        this.setText("Start");
        Font font = new Font("Britannic Bold",23);
        this.setFont(font);
        this.setAlignment(Pos.CENTER);
        this.setStyle(MAIN_BUTTON_STYLE_DEFAULT);
        this.setVisible(true);
    }

    private void initActions() {
        this.setOnMouseEntered(event -> {
            this.setStyle(MAIN_BUTTON_STYLE_ON_HOVER_DEFAULT);
        });

        this.setOnMouseExited(event -> {
            this.setStyle(MAIN_BUTTON_STYLE_DEFAULT);
        });

        this.setOnMouseClicked(event -> {
            this.mainMenuHandler.handleAction(getButtonName(), event);
        });
    }

    @Override
    public String getButtonName() {
        return this.buttonName;
    }

    @Override
    public void setButtonName(String value) {
        if (value != null && !value.isEmpty() && !value.isBlank()) {
            this.buttonName = value;
        }
    }
}
