package com.github.view.anchors;

import com.github.controller.MainViewController;
import javafx.scene.layout.AnchorPane;

public class MainAnchorPane extends AnchorPane {

    private final MainViewController mainViewController;

    public MainAnchorPane(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
        setAnchorPaneParameters();
    }

    private void setAnchorPaneParameters() {
        this.setPrefSize(480, 640);
    }
}
