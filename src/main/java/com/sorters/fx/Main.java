package com.sorters.fx;/*
It is a simple animation of how an array of random points is sorted using my version of the
Quick Sort algorithm.
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // These are the values that can be edited to define the size of the animation
    // Normally the canvas needs to be smaller then the actual main pane otherwise the
    // generate and sort button won't appear.
    // The MAX_VALUE is based on the maximum height of the canvas

    static final int WINDOW_WIDTH = 1000;
    static final int WINDOW_HEIGHT = 1000;
    static final int CANVAS_WIDTH = 800;
    static final int CANVAS_HEIGHT = 800;
    static final int MAX_VALUE = CANVAS_HEIGHT;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/mainPane.fxml"));
            primaryStage.setTitle("Sorter");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

}
