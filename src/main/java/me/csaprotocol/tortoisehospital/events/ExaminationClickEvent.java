package me.csaprotocol.tortoisehospital.events;

import javafx.event.Event;
import javafx.event.EventType;
import me.csaprotocol.tortoisehospital.entities.Examination;

public class ExaminationClickEvent extends Event {
    public static final EventType<ExaminationClickEvent> EXAMINATION_CLICK_EVENT = new EventType<>(Event.ANY, "EXAMINATION_CLICK_EVENT");

    public ExaminationClickEvent() {
        super(EXAMINATION_CLICK_EVENT);
    }
}
