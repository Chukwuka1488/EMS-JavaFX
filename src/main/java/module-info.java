module sample.ems {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;
//    requires mysql.connector.java;

//    requires com.dlsc.formsfx;
//    requires org.kordamp.bootstrapfx.core;

    opens sample.ems to javafx.fxml;
    exports sample.ems;
    exports sample.ems.controller;
    opens sample.ems.controller to javafx.fxml;
    exports sample.ems.model;
    opens sample.ems.model to javafx.fxml;
}