package me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu;

import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.Setter;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.controllers.EventController;
import me.csaprotocol.tortoisehospital.events.TurtleClickEvent;
import me.csaprotocol.tortoisehospital.events.eventbuses.EventBus;

import java.net.URL;
import java.util.ResourceBundle;

public class TurtleButton implements Initializable {

    @Setter private boolean isSelected = false;
    @FXML private Label idTurtleLabel;
    @FXML private Label nameTurtleLabel;
    @FXML private AnchorPane turtleID;

    public void setIdTurtleLabel(String idTurtle) {
        idTurtleLabel.setText(idTurtle);
    }
    public void setNameTurtleLabel(String turtleName) {
        nameTurtleLabel.setText(turtleName);
    }

    @FXML public void onTurtleClick(MouseEvent event) {
        EventController co = new EventController();
        co.fireTurtleEvent();
        turtleID.setStyle("-fx-background-color: #165DCE; -fx-background-radius: 1em");
        isSelected = true;
        ControllerOrchestrator coo = new ControllerOrchestrator();
        coo.handleTurtleClick(idTurtleLabel.getText());
    }

    @FXML public void onMouseEnter(MouseEvent event) {
        if(!isSelected) {
            turtleID.setStyle("-fx-background-color: #6393E7; -fx-background-radius: 1em");
        }
    }
    @FXML public void onMouseExit(MouseEvent event) {
        if (!isSelected) {
            turtleID.setStyle("-fx-background-color: #1E1E1E");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventBus.getInstance().register(this);
    }

    //Event Bus for TurtleClickEvent
    @Subscribe
    public void handleTurtleClickEvent(TurtleClickEvent event) {
        turtleID.setStyle("-fx-background-color: #1E1E1E");
        isSelected = false;
    }

}