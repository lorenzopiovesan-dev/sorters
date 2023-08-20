package com.sorters.fx;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    private PointList points;
    private Random random;
    private long timeStart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mainPane.setPrefWidth(Main.WINDOW_WIDTH);
        mainPane.setPrefHeight(Main.WINDOW_HEIGHT);
        canvasLayer.setWidth(Main.CANVAS_WIDTH);
        canvasLayer.setHeight(Main.CANVAS_HEIGHT);

        sortingButton.setDisable(true);

        points = new PointList();
        random = new Random();

    }

    // This is the method that sort the array and calculate the time needed to complete the task

    @FXML
    void startSorting() {
        Task<PointList> quickSort = new QuickSortTask(points, canvasLayer.getGraphicsContext2D());
        quickSort.setOnRunning(e-> {
            generateButton.setDisable(true);
            sortingButton.setDisable(true);
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
        });
        new Thread(quickSort).start();
        timeStart = System.currentTimeMillis();
    }

    // This method generate the array of random points to sort

    @FXML
    private void generatePoints() {
        canvasLayer.getGraphicsContext2D().clearRect(0, 0, Main.CANVAS_WIDTH, Main.CANVAS_HEIGHT);
        points.clear();
        for (int x = 0; x< Main.CANVAS_WIDTH; x++){
            int y = random.nextInt(Main.MAX_VALUE);
            points.add(new Point(x, y));
                canvasLayer.getGraphicsContext2D().fillOval(x, y, 2, 2);
        }
        sortingButton.setDisable(false);
    }
}
