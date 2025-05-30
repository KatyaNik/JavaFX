module com.example.task_8a_dao {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.task_8a_dao to javafx.fxml;
    exports com.example.task_8a_dao;
}