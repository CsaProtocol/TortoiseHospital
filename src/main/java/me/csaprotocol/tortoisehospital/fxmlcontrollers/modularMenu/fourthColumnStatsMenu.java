package me.csaprotocol.tortoisehospital.fxmlcontrollers.modularMenu;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

import java.time.LocalDate;

public class fourthColumnStatsMenu {

    @FXML
    private Pane fourthColumn;

    public void showTurtleStats() {
        // Create the x and y axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(1, 5, 1);

        // Set labels for the x-axis (dates)
        xAxis.setCategories(FXCollections.observableArrayList(
            LocalDate.now().minusDays(4).toString(),
            LocalDate.now().minusDays(3).toString(),
            LocalDate.now().minusDays(2).toString(),
            LocalDate.now().minusDays(1).toString(),
            LocalDate.now().toString()
        ));

        // Create the line chart
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Example LineChart");

        // Define a series
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Example Series");

        // Add data to the series
        series.getData().add(new XYChart.Data<>(LocalDate.now().minusDays(4).toString(), 2));
        series.getData().add(new XYChart.Data<>(LocalDate.now().minusDays(3).toString(), 3));
        series.getData().add(new XYChart.Data<>(LocalDate.now().minusDays(2).toString(), 4));
        series.getData().add(new XYChart.Data<>(LocalDate.now().minusDays(1).toString(), 5));
        series.getData().add(new XYChart.Data<>(LocalDate.now().toString(), 1));

        // Add the series to the chart
        lineChart.getData().add(series);
    }

}