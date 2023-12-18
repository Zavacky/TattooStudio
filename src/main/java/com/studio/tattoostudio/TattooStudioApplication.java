package com.studio.tattoostudio;

import com.studio.tattoostudio.controllers.LoginSceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TattooStudioApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LoginSceneController loginSceneController = new LoginSceneController();
        FXMLLoader fxmlLoader = new FXMLLoader(TattooStudioApplication.class.getResource("/com.studio.tattoostudio/LoginScene.fxml"));
        fxmlLoader.setController(loginSceneController);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Tattoo Studio");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}