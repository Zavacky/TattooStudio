module com.studio.tattoostudio {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires spring.jdbc;
    requires lombok;
    requires java.sql;
    requires commons.dbcp2;


    opens com.studio.tattoostudio to javafx.fxml;
    exports com.studio.tattoostudio;
    exports com.studio.tattoostudio.controllers;
    opens com.studio.tattoostudio.controllers to javafx.fxml;
}