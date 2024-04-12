module com.example.busticketing {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.persistence;
    requires java.sql;
    requires com.google.gson;
    requires org.apache.commons.dbcp2;
    requires java.desktop;


    opens com.example.busticketing to javafx.fxml;
    exports com.example.busticketing;
}