package me.csaprotocol.tortoisehospital.events.eventbuses;

import com.google.common.eventbus.EventBus;
public class TurtleClickEventBus {
    private static final EventBus eventBus = new EventBus();

    public static EventBus getInstance() {
        return eventBus;
    }
}