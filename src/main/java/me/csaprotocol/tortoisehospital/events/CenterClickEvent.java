package me.csaprotocol.tortoisehospital.events;

import javafx.event.Event;
import javafx.event.EventType;

public class CenterClickEvent extends Event {
    public static final EventType<CenterClickEvent> CENTER_CLICK_EVENT = new EventType<>(Event.ANY, "CENTER_CLICK_EVENT");

    public CenterClickEvent() {
        super(CENTER_CLICK_EVENT);
    }
}
