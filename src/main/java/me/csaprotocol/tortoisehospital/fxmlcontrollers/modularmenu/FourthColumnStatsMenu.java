package me.csaprotocol.tortoisehospital.fxmlcontrollers.modularmenu;

import eu.hansolo.fx.charts.*;
import eu.hansolo.fx.charts.data.XYChartItem;
import eu.hansolo.fx.charts.series.XYSeries;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import me.csaprotocol.tortoisehospital.controllers.GUIUtilsController;
import me.csaprotocol.tortoisehospital.exceptions.CoreException;
import me.csaprotocol.tortoisehospital.exceptions.ExceptionHandler;

import java.net.URL;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ResourceBundle;

public class FourthColumnStatsMenu implements Initializable {

    @FXML private MFXDatePicker endDate;
    @FXML private Pane fourthColumn;
    @FXML private MFXButton showCenterStatsButton;
    @FXML private MFXDatePicker startDate;
    @FXML private AnchorPane turtleStats;
    @FXML private void onShowStatsClick() {
        if(startDate.getValue() == null || endDate.getValue() == null) {
            try {
                throw new CoreException("Please select a date range to view the stats");
            } catch (CoreException e) {
                ExceptionHandler eh = new ExceptionHandler();
                eh.handleException(e.getMessage());
            }
        }
        showTurtleStats();
    }

    public void showTurtleStats() {
        XYSeries<XYChartItem> xySeries;

        try {
            if(startDate.getValue().isAfter(endDate.getValue())) {
                throw new CoreException("Start date cannot be after end date");
            }

            GUIUtilsController guiC = new GUIUtilsController();
            xySeries = guiC.createData(startDate.getValue(), endDate.getValue());
        } catch (CoreException e) {
            ExceptionHandler eh = new ExceptionHandler();
            eh.handleException(e.getMessage());
            return;
        }

        Axis xAxisBottom = createXAxis(startDate.getValue().atStartOfDay().toInstant(ZoneOffset.UTC),
                                        endDate.getValue().atStartOfDay().toInstant(ZoneOffset.UTC));
        Axis yAxisLeft = createYAxis();

        Grid grid = GridBuilder.create(xAxisBottom, yAxisLeft)
            .gridLinePaint(Color.web("#384C57"))
            .minorHGridLinesVisible(false)
            .mediumHGridLinesVisible(false)
            .minorVGridLinesVisible(false)
            .mediumVGridLinesVisible(false)
            .gridLineDashes(4, 4)
            .build();

        XYPane lineChartPane = new XYPane(xySeries);

        XYChart<XYChartItem> lineChart = new XYChart<>(lineChartPane, grid, yAxisLeft, xAxisBottom);
        turtleStats.getChildren().clear();
        turtleStats.getChildren().add(lineChart);
    }

    private Axis createXAxis(Instant start, Instant end) {
        Axis x = AxisBuilder.create(Orientation.HORIZONTAL, Position.BOTTOM)
            .type(AxisType.TIME)
            .setStart(Instant.from(start))
            .setEnd(Instant.from(end))
            .autoScale(true)
            .autoFontSize(true)
            .axisColor(Color.web("#82909B"))
            .tickLabelColor(Color.web("#82909B"))
            .tickMarkColor(Color.web("#82909B"))
            .majorTickMarksVisible(true)
            .build();
        AnchorPane.setBottomAnchor(x, 0d);
        AnchorPane.setLeftAnchor(x, 25d);
        AnchorPane.setRightAnchor(x, 25d);
        return x;
    }

    private Axis createYAxis() {
        Axis y = AxisBuilder.create(Orientation.VERTICAL, Position.LEFT)
            .type(AxisType.TEXT)
            .categories("P", "N", "L", "D", "C", "Dead")
            .minValue(0)
            .maxValue(6)
            .autoScale(true)
            .autoFontSize(true)
            .axisColor(Color.web("#82909B"))
            .tickLabelColor(Color.web("#82909B"))
            .tickMarkColor(Color.web("#82909B"))
            .build();
        AnchorPane.setTopAnchor(y, 0d);
        AnchorPane.setBottomAnchor(y, 25d);
        AnchorPane.setLeftAnchor(y, 0d);
        return y;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startDate.setPromptText("From");
        endDate.setPromptText("To");
    }
}