module com.fvgprinc.app.crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.fvgprinc.app.crud to javafx.fxml;
    exports com.fvgprinc.app.crud;
}
