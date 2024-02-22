package me.csaprotocol.tortoisehospital.events;

import javafx.event.Event;
import javafx.event.EventType;

public class MeasurementClickEvent extends Event {
    public static final EventType<MeasurementClickEvent> MEASUREMENT_CLICK_EVENT = new EventType<>(Event.ANY, "MEASUREMENT_CLICK_EVENT");

    public MeasurementClickEvent() {
        super(MEASUREMENT_CLICK_EVENT);
    }
}
