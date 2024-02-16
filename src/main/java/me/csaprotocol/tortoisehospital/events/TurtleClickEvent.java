package me.csaprotocol.tortoisehospital.events;

import javafx.event.Event;
import javafx.event.EventType;

public class TurtleClickEvent extends Event {
    public static final EventType<TurtleClickEvent> TURTLE_CLICK_EVENT = new EventType<>(Event.ANY, "TURTLE_CLICK_EVENT");

    public TurtleClickEvent() {
        super(TURTLE_CLICK_EVENT);
    }
}
