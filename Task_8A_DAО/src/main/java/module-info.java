module com.example.task_8a_dao {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    // Для работы приложения
    opens com.example.task_8a_dao to javafx.fxml;
    exports com.example.task_8a_dao;
}