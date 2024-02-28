package me.csaprotocol.tortoisehospital.fxmlcontrollers.modularMenu;

import eu.hansolo.fx.charts.ConcentricRingChart;
import eu.hansolo.fx.charts.data.ChartItem;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;

import java.util.ArrayList;

public class thirdColumnStatsMenu {
    @FXML private MFXDatePicker firstDate;
    @FXML private HBox lowerHBox;
    @FXML private MFXDatePicker secondDate;
    @FXML private Pane thirdColumn;
    @FXML private HBox upperHBox;

    @FXML private void onShowStatsClick() {
        ControllerOrchestrator co = new ControllerOrchestrator();
        Integer[] values = co.handleCenterStatistics(firstDate.getValue(), secondDate.getValue());

        ConcentricRingChart chart = new ConcentricRingChart();
        ChartItem item = new ChartItem("Compromised", values[0]);
        ChartItem item2 = new ChartItem("Deep wounds", values[1]);
        ChartItem item3 = new ChartItem("Light wounds", values[2]);
        ChartItem item4 = new ChartItem("Normal", values[3]);
        ChartItem item5 = new ChartItem("Perfect", values[4]);

        item.setFill(javafx.scene.paint.Color.RED);
        item2.setFill(javafx.scene.paint.Color.ORANGE);
        item3.setFill(javafx.scene.paint.Color.CYAN);
        item4.setFill(javafx.scene.paint.Color.GREEN);
        item5.setFill(javafx.scene.paint.Color.WHITE);

        chart.setItems(item, item2, item3, item4, item5);
        upperHBox.getChildren().add(chart);
        upperHBox.setAlignment(javafx.geometry.Pos.CENTER);
    }
    public void initialize() {
        firstDate.setPromptText("From");
        secondDate.setPromptText("To");
    }

}