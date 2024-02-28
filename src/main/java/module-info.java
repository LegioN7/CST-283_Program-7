module org.example.project7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.project7 to javafx.fxml;
    exports org.example.project7;
}