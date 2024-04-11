module com.example.busticketing {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.persistence;
    requires java.sql;


    opens com.example.busticketing to javafx.fxml;
    exports com.example.busticketing;
}