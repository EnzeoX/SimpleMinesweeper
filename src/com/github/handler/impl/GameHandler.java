package com.github.handler.impl;

import com.github.controller.MainViewController;
import com.github.handler.IViewHandler;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class GameHandler implements IViewHandler {

    private AnchorPane parent;
    private AnchorPane child;
    private MainViewController mainViewController;

    private String handlerName;

    public GameHandler() {
        this.handlerName = this.getClass().getSimpleName();
    }

    public GameHandler(AnchorPane parent, AnchorPane child, MainViewController mainViewController) {
        this.parent = parent;
        this.child = child;
        this.mainViewController = mainViewController;
        this.handlerName = this.getClass().getSimpleName();
    }

    @Override
    public void handleAction(String objectId, Event event) {
        if (objectId == null || objectId.isEmpty()) {
            System.out.println("No objectId provided!");
            return;
        }
        if (event == null) {
            System.out.println("No event provided!");
            return;
        }
        if (event instanceof MouseEvent) {
            switch (objectId) {
                case "":
                    break;
                default:
                    break;
            }
        } else if (event instanceof KeyEvent) {

        }
    }

    @Override
    public void changeView(String changeTo) {
        this.mainViewController.changeView();
    }

    @Override
    public AnchorPane getCorrespondingView() {
        return null;
    }

    @Override
    public void setHandlerName(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("No name provided");
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
        return this.handlerName.equals(handlerName);
    }
}
