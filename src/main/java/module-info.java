module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;

    opens org.turkey to javafx.fxml;
    exports org.turkey;
    exports org.turkey.controllers;
    opens org.turkey.controllers to javafx.fxml;
}
