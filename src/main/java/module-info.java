module com.example.finalnm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.finalnm to javafx.fxml;
    exports com.finalnm;
    exports com.finalnm.controller;
    opens com.finalnm.controller to javafx.fxml;
}