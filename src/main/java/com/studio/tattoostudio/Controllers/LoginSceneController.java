package com.studio.tattoostudio.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginSceneController {

    @FXML
    private PasswordField passwordTextfield;

    @FXML
    private Button registerButton;

    @FXML
    private ImageView logoImageview;

    @FXML
    private Button loginButton;

    @FXML
    private ComboBox<?> clientoradminComboBox;

    @FXML
    private CheckBox VisibilityCheckBox;

    @FXML
    private TextField loginTextField;

    @FXML
    void onLoginUser(ActionEvent event) {

    }

    @FXML
    void onRegisterUser(ActionEvent event) {

    }

    @FXML
    void onChooseUser(ActionEvent event) {

    }

    @FXML
    void onVisibleCheck(ActionEvent event) {

    }




    @FXML
    void onLogin(ActionEvent event) {
        String username = loginTextField.getText();
        String password = passwordTextfield.getText();
        String userType = clientoradminComboBox.getSelectionModel().getSelectedItem().toString();

        if (authenticate(username, password, userType)) {
            loadMenuScene();
        } else {
            System.out.println("Login failed");
        }
    }

    private boolean authenticate(String username, String password, String userType) {

        return "user".equals(username) && "password".equals(password) && userType != null;
    }

    private void loadMenuScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuScene.fxml"));
            Parent menuScene = loader.load();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(menuScene));
            stage.setTitle("Tattoo Studio Menu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onRegister(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterScene.fxml"));
            Parent registerScene = loader.load();
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(registerScene));
            stage.setTitle("Tattoo Studio Registration");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
