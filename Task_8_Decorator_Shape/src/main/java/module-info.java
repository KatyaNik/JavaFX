module com.example.decorator_shape {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.decorator_shape to javafx.fxml;
    exports com.example.decorator_shape;
}