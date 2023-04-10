package com.github.controller;

import com.github.cache.GameCache;
import com.github.handler.IViewHandler;
import com.github.handler.StartHandler;
import com.github.handler.impl.MainMenuHandler;
import com.github.models.ViewCommand;
import com.github.view.anchors.game.MainGamePane;
import com.github.view.buttons.ButtonInterface;
import com.github.view.input.InputBox;
import com.github.view.input.main.MinesQuantityInputBox;
import com.github.view.input.main.PaneHeightInputBox;
import com.github.view.input.main.PaneWidthInputBox;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class MainViewController {

    private final AnchorPane root;
    private AnchorPane parent;

    private String currentViewName = "";
    private String changeTo = "";

    private Map<String, IViewHandler> handlers = new HashMap<>();

    private boolean changeView = false;

    public MainViewController() {
        this.root = new AnchorPane();
        this.parent = new AnchorPane();
        this.currentViewName = "start";
    }


    public MainViewController(AnchorPane root) {
        this.root = root;
        setHandlers();
    }


    private void setHandlers() {

//        ServiceLoader<ChildHandler> handlers = ServiceLoader.load(ChildHandler.class);

        //on new instance - set MainMenuHandler as default

        MainMenuHandler mainMenuHandler = new MainMenuHandler(this);
        this.parent = mainMenuHandler.getCorrespondingView();

    }

    public void changeView() {
        this.changeView = true;
    }


    private void processToNewGame() {
        MainGamePane mainGamePane = new MainGamePane();
        Scene scene = this.parent.getScene();
        mainGamePane.translateXProperty().set(scene.getWidth());
        this.root.getChildren().add(mainGamePane);
        transitionToNewView(mainGamePane);
    }

    private void transitionToNewView(Parent root) {
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.6), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(move -> {
            this.root.getChildren().remove(this.parent);
        });
        timeline.play();
    }
}
