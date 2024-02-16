package me.csaprotocol.tortoisehospital.controllers;

import me.csaprotocol.tortoisehospital.events.CenterClickEvent;
import me.csaprotocol.tortoisehospital.events.TankClickEvent;
import me.csaprotocol.tortoisehospital.events.TurtleClickEvent;
import me.csaprotocol.tortoisehospital.events.eventbuses.CenterClickEventBus;
import me.csaprotocol.tortoisehospital.events.eventbuses.TankClickEventBus;
import me.csaprotocol.tortoisehospital.events.eventbuses.TurtleClickEventBus;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.userMenu;

public class EventController {

    public void throwTurtleEvent() {
        TurtleClickEventBus.getInstance().post(new TurtleClickEvent());
    }

    public void throwCenterEvent() {
        CenterClickEventBus.getInstance().post(new CenterClickEvent());
    }
    public void throwTankEvent() {
        TankClickEventBus.getInstance().post(new TankClickEvent());
    }

}
