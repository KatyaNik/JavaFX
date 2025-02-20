module com.example.lb2_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.lb2_javafx to javafx.fxml;
    exports com.example.lb2_javafx;
}