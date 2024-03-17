package me.csaprotocol.tortoisehospital.fxmlcontrollers.modularmenu;

import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.controllers.GUIUtilsController;
import me.csaprotocol.tortoisehospital.exceptions.CoreException;
import me.csaprotocol.tortoisehospital.exceptions.ExceptionHandler;

public class ThirdColumnStatsMenu {
    
    @FXML private MFXDatePicker startDate;
    @FXML private HBox lowerHBox;
    @FXML private MFXDatePicker endDate;
    @FXML private Pane thirdColumn;
    @FXML private HBox upperHBox;

    @FXML private void onShowStatsClick() {
        try {
            if(startDate.getValue() == null || endDate.getValue() == null) {
                throw new CoreException("Please select a date range to view the stats");
            }
            if(startDate.getValue().isAfter(endDate.getValue())) {
                throw new CoreException("Start date cannot be after end date");
            }

            ControllerOrchestrator co = new ControllerOrchestrator();
            Integer[] values = co.handleCenterStatistics(startDate.getValue(), endDate.getValue());

            GUIUtilsController guic = new GUIUtilsController();
            upperHBox.getChildren().clear();
            upperHBox.getChildren().add(guic.buildConcentricRingChartCenter(values));
            upperHBox.setAlignment(javafx.geometry.Pos.CENTER);

            lowerHBox.getChildren().clear();
            lowerHBox.getChildren().add(guic.buildCoxCombChartCenter(values));
            lowerHBox.setAlignment(javafx.geometry.Pos.CENTER);

        } catch (CoreException e) {
            ExceptionHandler eh = new ExceptionHandler();
            eh.handleException(e.getMessage());
        }
    }

    public void initialize() {
        startDate.setPromptText("From");
        endDate.setPromptText("To");
    }

}