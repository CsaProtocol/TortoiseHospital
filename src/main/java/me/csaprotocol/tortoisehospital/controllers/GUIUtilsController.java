package me.csaprotocol.tortoisehospital.controllers;

import eu.hansolo.fx.charts.ChartType;
import eu.hansolo.fx.charts.Symbol;
import eu.hansolo.fx.charts.data.XYChartItem;
import eu.hansolo.fx.charts.series.XYSeries;
import eu.hansolo.fx.charts.series.XYSeriesBuilder;
import javafx.scene.paint.Color;

import java.time.LocalDate;

public class GUIUtilsController {

    public XYSeries<XYChartItem> createData(LocalDate startDate, LocalDate endDate) {
        ControllerOrchestrator co = new ControllerOrchestrator();

        @SuppressWarnings("unchecked")
        XYSeries<XYChartItem> xySeries1 = XYSeriesBuilder.create()
            .items(co.handleTurtleStats(startDate, endDate))
            .chartType(ChartType.SMOOTH_LINE)
            .fill(Color.web("#00AEF520"))
            .stroke(Color.web("#00AEF5"))
            .symbolFill(Color.web("#00AEF5"))
            .symbolStroke(Color.web("#293C47"))
            .symbolSize(6)
            .strokeWidth(2)
            .animated(true)
            .symbol(Symbol.CIRCLE)
            .symbolsVisible(true)
            .build();

        return xySeries1;
    }
}
