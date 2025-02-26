package me.csaprotocol.tortoisehospital.events;

import javafx.event.Event;
import javafx.event.EventType;

public class TankClickEvent extends Event {
    public static final EventType<TankClickEvent> TANK_CLICK_EVENT = new EventType<>(Event.ANY, "TANK_CLICK_EVENT");

    public TankClickEvent() {
        super(TANK_CLICK_EVENT);
    }
}