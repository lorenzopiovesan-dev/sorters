module sorters {
    requires com.google.guice;
    requires jakarta.inject;
    requires javafx.controls;
    requires javafx.fxml;
    exports com.sorters.fx;
    opens com.sorters.fx to javafx.fxml;
}