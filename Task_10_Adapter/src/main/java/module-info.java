module com.example.task_10_adapter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.task_10_adapter to javafx.fxml;
    exports com.example.task_10_adapter;
}