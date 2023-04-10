package com.github.models;

public class ReturnData {

    private EventType eventType;
    private int valueData;

    public ReturnData() {
        setEventType(EventType.NO_CELL);
    }

    public ReturnData(EventType endType) {
        this.eventType = endType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setValueData(int value) {
        this.valueData = value;
    }

    public int getValueData() {
        return this.valueData;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public enum EventType {
        START_GAME,
        GAME_OVER,
        CONTINUE,
        WIN_GAME,
        MARK,
        UNMARK,
        NO_CELL
    }
}
