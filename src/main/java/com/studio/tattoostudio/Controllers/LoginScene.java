package com.studio.tattoostudio.Controllers;

import com.studio.tattoostudio.Tattoo;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginScene extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginSceneController controller = new LoginSceneController();

        FXMLLoader loader = new FXMLLoader(Tattoo.class.getResource("LoginScene.fxml"));
        loader.setController(controller);
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
