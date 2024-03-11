package me.csaprotocol.tortoisehospital.events.eventbuses;

public class EventBus {
    private static final com.google.common.eventbus.EventBus eventBus = new com.google.common.eventbus.EventBus();
    public static com.google.common.eventbus.EventBus getInstance() {
        return eventBus;
    }
}
