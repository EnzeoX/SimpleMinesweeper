package com.github.view.input.main;

import com.github.view.input.InputBox;
import javafx.scene.control.TextField;

import static com.github.view.global.styles.TextBoxStyles.TEXT_BOX_DEFAULT;

public class PaneHeightInputBox extends TextField implements InputBox<String> {

    public PaneHeightInputBox() {
        init();
    }

    private void init() {
        this.setId("main_panel_height_text_box");
        this.setPrefSize(42, 25);
        this.setLayoutX(256);
        this.setLayoutY(213);
        this.setStyle(TEXT_BOX_DEFAULT);
        this.setVisible(true);
        this.setEditable(false);
    }

    @Override
    public void setValue(String value) {
        this.setText(value);
    }

    @Override
    public String getValue() {
        return this.getText();
    }
}
