package me.csaprotocol.tortoisehospital.controllers;

import me.csaprotocol.tortoisehospital.events.CenterClickEvent;
import me.csaprotocol.tortoisehospital.events.TankClickEvent;
import me.csaprotocol.tortoisehospital.events.TurtleClickEvent;
import me.csaprotocol.tortoisehospital.events.eventbuses.CenterClickEventBus;
import me.csaprotocol.tortoisehospital.events.eventbuses.TankClickEventBus;
import me.csaprotocol.tortoisehospital.events.eventbuses.TurtleClickEventBus;

public class EventController {

    public void fireTurtleEvent() {
        TurtleClickEventBus.getInstance().post(new TurtleClickEvent());
    }

    public void fireCenterEvent() {
        CenterClickEventBus.getInstance().post(new CenterClickEvent());
    }
    public void fireTankEvent() {
        TankClickEventBus.getInstance().post(new TankClickEvent());
    }

}
