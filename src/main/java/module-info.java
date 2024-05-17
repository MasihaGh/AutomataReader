module com.ed.editdistance {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.ed to javafx.fxml;
    exports com.ed;
}