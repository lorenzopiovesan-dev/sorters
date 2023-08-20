package com.sorters.fx;

import com.sorters.options.SortOrder;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {

    @FXML
    Canvas canvasLayer;
    @FXML
    BorderPane mainPane;
    @FXML
    Button generateButton;
    @FXML
    Button sortingButton;
    @FXML
    RadioButton ascendingOrderButton;
    @FXML
    RadioButton descendingOrderButton;

    private PointList points;
    private Random random;
    private long timeStart;

    private SortOrder sortOrder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mainPane.setPrefWidth(Main.WINDOW_WIDTH);
        mainPane.setPrefHeight(Main.WINDOW_HEIGHT);
        canvasLayer.setWidth(Main.CANVAS_WIDTH);
        canvasLayer.setHeight(Main.CANVAS_HEIGHT);

        sortingButton.setDisable(true);

        points = new PointList();
        random = new Random();
        this.ascendingOrderButton.setSelected(true);
        this.descendingOrderButton.setSelected(false);
        this.ascendingOrderButton.setDisable(true);
        this.descendingOrderButton.setDisable(true);
        sortOrder = SortOrder.ASCENDING;

    }

    @FXML
    void startSorting() {
        Task<PointList> quickSort = new QuickSortTask(points, canvasLayer.getGraphicsContext2D(), this.random, this.sortOrder);
        quickSort.setOnRunning(e -> {
            generateButton.setDisable(true);
            sortingButton.setDisable(true);
            this.ascendingOrderButton.setDisable(true);
            this.descendingOrderButton.setDisable(true);
        });
        quickSort.setOnSucceeded(e -> {
            long duration = System.currentTimeMillis() - timeStart;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(duration);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Completed");
            alert.setContentText("ARRAY SORTED in approx. " + seconds + " s");
            alert.setTitle("Sort Done");
            alert.showAndWait();
            generateButton.setDisable(false);
            sortingButton.setDisable(true);
            this.ascendingOrderButton.setDisable(true);
            this.descendingOrderButton.setDisable(true);
        });
        new Thread(quickSort).start();
        timeStart = System.currentTimeMillis();
    }

    @FXML
    private void generatePoints() {
        canvasLayer.getGraphicsContext2D().clearRect(0, 0, Main.CANVAS_WIDTH, Main.CANVAS_HEIGHT);
        points.clear();
        for (int x = 0; x < Main.CANVAS_WIDTH; x++) {
            int y = random.nextInt(Main.MAX_VALUE);
            points.add(new Point(x, y));
            canvasLayer.getGraphicsContext2D().fillOval(x, y, 2, 2);
        }
        sortingButton.setDisable(false);
        this.ascendingOrderButton.setDisable(false);
        this.descendingOrderButton.setDisable(false);
    }

    @FXML
    private void setAscendingOrder() {
        this.descendingOrderButton.setSelected(false);
        this.sortOrder = SortOrder.ASCENDING;
    }

    @FXML
    private void setDescendingOrder() {
        this.ascendingOrderButton.setSelected(false);
        this.sortOrder = SortOrder.DESCENDING;
    }
}
