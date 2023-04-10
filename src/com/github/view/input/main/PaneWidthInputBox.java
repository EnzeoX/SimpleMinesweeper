package com.github.view.input.main;

import com.github.view.input.InputBox;
import javafx.scene.control.TextField;

import static com.github.view.global.styles.TextBoxStyles.TEXT_BOX_DEFAULT;

public class PaneWidthInputBox extends TextField implements InputBox<String> {

    public PaneWidthInputBox() {
        init();
    }

    private void init() {
        this.setId("main_panel_width_text_box");
        this.setPrefSize(42, 25);
        this.setLayoutX(256);
        this.setLayoutY(176);
        this.setStyle(TEXT_BOX_DEFAULT);
        this.setVisible(true);
        this.setEditable(false);
    }

    public void setTextValue(String value) {
        this.setText(value);
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
