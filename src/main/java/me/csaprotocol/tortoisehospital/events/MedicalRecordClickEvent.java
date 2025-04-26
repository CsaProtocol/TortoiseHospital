package me.csaprotocol.tortoisehospital.events;

import javafx.event.Event;
import javafx.event.EventType;

public class MedicalRecordClickEvent extends Event {
    public static final EventType<MedicalRecordClickEvent> MEDICAL_RECORD_CLICK_EVENT = new EventType<>(Event.ANY, "MEDICAL_RECORD_CLICK_EVENT");

    public MedicalRecordClickEvent() {
        super(MEDICAL_RECORD_CLICK_EVENT);
    }
}
