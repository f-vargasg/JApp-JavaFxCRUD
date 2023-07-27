module com.fvgprinc.app.crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.fvgprinc.app.crud to javafx.fxml;
    exports com.fvgprinc.app.crud;
    exports com.fvgprinc.app.crud.data;
    exports com.fvgprinc.app.crud.model;
}
