package me.csaprotocol.tortoisehospital.fxmlcontrollers.modularMenu;

import eu.hansolo.fx.charts.ConcentricRingChart;
import eu.hansolo.fx.charts.data.ChartItem;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.controllers.GUIUtilsController;

import java.util.ArrayList;

public class thirdColumnStatsMenu {
    @FXML private MFXDatePicker firstDate;
    @FXML private HBox lowerHBox;
    @FXML private MFXDatePicker secondDate;
    @FXML private Pane thirdColumn;
    @FXML private HBox upperHBox;

    @FXML private void onShowStatsClick() {
        GUIUtilsController guc = new GUIUtilsController();

        ControllerOrchestrator co = new ControllerOrchestrator();
        Integer[] values = co.handleCenterStatistics(firstDate.getValue(), secondDate.getValue());

        upperHBox.getChildren().clear();
        upperHBox.getChildren().add(guc.buildConcentricRingChartCenter(values));
        upperHBox.setAlignment(javafx.geometry.Pos.CENTER);

        lowerHBox.getChildren().clear();
        lowerHBox.getChildren().add(guc.buildCoxCombChartCenter(values));
        lowerHBox.setAlignment(javafx.geometry.Pos.CENTER);
    }

    public void initialize() {
        firstDate.setPromptText("From");
        secondDate.setPromptText("To");
    }

}