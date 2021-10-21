module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires com.google.gson;

    opens org.turkey to javafx.fxml;
    exports org.turkey;
//    exports org.turkey.controllers;
    exports org.turkey.models;
//    opens org.turkey.controllers to javafx.fxml;
    exports org.turkey.controllers.stock;
    opens org.turkey.controllers.stock to javafx.fxml;
    exports org.turkey.controllers.report;
    opens org.turkey.controllers.report to javafx.fxml;
    exports org.turkey.controllers.saleOrder;
    opens org.turkey.controllers.saleOrder to javafx.fxml;
    exports org.turkey.controllers.customer;
    opens org.turkey.controllers.customer to javafx.fxml;
    exports org.turkey.controllers.purchaseOrder;
    opens org.turkey.controllers.purchaseOrder to javafx.fxml;
    exports org.turkey.controllers.home;
    opens org.turkey.controllers.home to javafx.fxml;
}
