module sample.ems.ems {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    exports sample.ems;
    exports sample.ems.controller;
    opens sample.ems.controller to javafx.fxml;
    exports sample.ems.model;
    opens sample.ems.model to javafx.fxml;
}