package com.github.handler;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.layout.AnchorPane;

public interface IViewHandler {

    void handleAction(String objectId, Event event);
    void changeView(String changeTo);

    AnchorPane getCorrespondingView();

    void setHandlerName(String name);
    String getHandlerName();
    boolean canHandle(String handlerName);
}
