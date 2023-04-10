package com.github.view.pane;

import javafx.geometry.NodeOrientation;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class BombsCountPane extends Pane {

    private final Label bombsText;
    private final Label bombsCountLabel;

    public BombsCountPane() {
        this.setId("bombs-count-pane");
        this.bombsCountLabel = new Label();
        this.bombsText = new Label();
        initPane();
    }

    private void initPane() {
        this.bombsText.setPrefSize(100, 25);
        this.bombsText.setLayoutX(14);
        this.bombsText.setLayoutY(20);
        this.bombsText.setText("BOMBS: ");
        this.bombsText.setStyle("-fx-font-family: 'Britannic Bold';" +
                "-fx-font-size: 25;");
        this.bombsText.setAccessibleRole(AccessibleRole.TEXT);
        this.bombsText.setVisible(true);
        this.getChildren().add(this.bombsText);

        this.bombsCountLabel.setPrefSize(70, 25);
        this.bombsCountLabel.setLayoutX(114);
        this.bombsCountLabel.setLayoutY(20);
        this.bombsCountLabel.setText("");
        this.bombsCountLabel.setStyle("-fx-font-family: 'Britannic Bold';" +
                "-fx-font-size: 25;");
        this.bombsCountLabel.setAccessibleRole(AccessibleRole.TEXT);
        this.bombsCountLabel.setVisible(true);
        this.getChildren().add(this.bombsCountLabel);

        this.setPrefSize(200, 64);
        this.setLayoutX(266);
        this.setLayoutY(0);
        this.setNodeOrientation(NodeOrientation.INHERIT);
        this.setAccessibleRole(AccessibleRole.PARENT);
        this.setVisible(true);
    }

    public void setBombsCount(int value) {
        this.bombsCountLabel.setText(String.valueOf(value));
    }

    public int getBombsCount() {
        try {
            return Integer.parseInt(this.bombsCountLabel.getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
