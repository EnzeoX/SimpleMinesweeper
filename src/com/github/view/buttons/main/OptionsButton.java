package com.github.view.buttons.main;

import com.github.view.buttons.ButtonInterface;
import javafx.scene.control.Button;

public class OptionsButton extends Button implements ButtonInterface {

    private String buttonName = "optionsButton";

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
