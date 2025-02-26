package me.csaprotocol.tortoisehospital.controllers;

import me.csaprotocol.tortoisehospital.events.*;
import me.csaprotocol.tortoisehospital.events.eventbuses.EventBus;

public class EventController {

    public void fireTurtleEvent() {
        EventBus.getInstance().post(new TurtleClickEvent());
    }
    public void fireCenterEvent() {
        EventBus.getInstance().post(new CenterClickEvent());
    }
    public void fireTankEvent() {
        EventBus.getInstance().post(new TankClickEvent());
    }
    public void fireMeasurementEvent() {
        EventBus.getInstance().post(new MeasurementClickEvent());
    }
    public void fireMedicalRecordEvent() {
        EventBus.getInstance().post(new MedicalRecordClickEvent());
    }

    public void fireExaminationEvent() {
        EventBus.getInstance().post(new ExaminationClickEvent());
    }
}
