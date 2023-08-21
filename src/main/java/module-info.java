module sorters {
    requires com.google.guice;
    requires jakarta.inject;
    requires javafx.controls;
    requires javafx.fxml;
    exports com.sorters.fx;
    exports com.sorters.options;
    opens com.sorters.fx to javafx.fxml;
}