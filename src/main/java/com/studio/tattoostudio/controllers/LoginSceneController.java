package com.studio.tattoostudio.controllers;
import com.studio.tattoostudio.data.Client;
import com.studio.tattoostudio.data.TattooArtist;
import com.studio.tattoostudio.exceptions.IncorrectLoginOrPasswordException;
import com.studio.tattoostudio.exceptions.TattooArtistDoesntExistException;
import com.studio.tattoostudio.factory.Factory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginSceneController {
    @FXML
    private ComboBox<String> clientOrArtistComboBox;

    @FXML
    private Label emptyLoginOrPasswordLabel;

    @FXML
    private Label wrongCredintialsLabel;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextfield;

    @FXML
    private Button registerButton;

    private boolean isClient = true;

    @FXML
    void initialize() {
        clientOrArtistComboBox.getItems().addAll("Client", "Artist");
        clientOrArtistComboBox.getSelectionModel().selectFirst();
        loginTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty() || newValue == null) {
                    emptyLoginOrPasswordLabel.setVisible(true);
                    loginButton.setDisable(true);
                }else {
                    emptyLoginOrPasswordLabel.setVisible(false);
                    loginButton.setDisable(false);
                }
            }
        } );
        passwordTextfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty() || newValue == null) {
                    emptyLoginOrPasswordLabel.setVisible(true);
                    loginButton.setDisable(true);
                }else {
                    emptyLoginOrPasswordLabel.setVisible(false);
                    loginButton.setDisable(false);
                }
            }
        });
    }

    @FXML
    void onChooseUser(ActionEvent event) {
        if (clientOrArtistComboBox.getSelectionModel().getSelectedItem().equals("Client")) {
            isClient = true;
        } else {
            isClient = false;
        }
    }

    @FXML
    void onLoginUserButton(ActionEvent event) {
        if (isClient) {
            try {
                Client client = Factory.INSTANCE.getClientDao().getByLogin(loginTextField.getText());
                if (!client.getPassword().equals(passwordTextfield.getText())) {
                    wrongCredintialsLabel.setVisible(true);
                }else {
                    MenuSceneController menuSceneController = new MenuSceneController(loginTextField.getText());
                    openMenuScene(menuSceneController);
                }
            } catch (IncorrectLoginOrPasswordException e) {
                wrongCredintialsLabel.setVisible(true);
            }
        } else {
            try {
                TattooArtist artist = Factory.INSTANCE.getTattooArtistDao().getByLogin(loginTextField.getText());
                if (!artist.getPassword().equals(passwordTextfield.getText())) {
                    wrongCredintialsLabel.setVisible(true);
                }else {
                    TatterMenuSceneController tatterMenuSceneController = new TatterMenuSceneController(loginTextField.getText());
                    openTaterMenuScene(tatterMenuSceneController);
                }
            } catch (TattooArtistDoesntExistException e) {
                wrongCredintialsLabel.setVisible(true);
            }
        }
    }

    @FXML
    void onRegisterUserButton(ActionEvent event) {
        openRegistrationScene();
    }

    private void openRegistrationScene() {
        try{
            RegistrationSceneController registrationSceneController = new RegistrationSceneController();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com.studio.tattoostudio/RegistrationScene.fxml"));
            fxmlLoader.setController(registrationSceneController);
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Tattoo Studio registration");
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void openMenuScene(MenuSceneController menuSceneController) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com.studio.tattoostudio/MenuScene.fxml"));
            fxmlLoader.setController(menuSceneController);
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Tattoo Studio menu");
            stage.show();
            loginButton.getScene().getWindow().hide();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void openTaterMenuScene(TatterMenuSceneController tatterMenuSceneController) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com.studio.tattoostudio/TatterMenuScene.fxml"));
            fxmlLoader.setController(tatterMenuSceneController);
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Tattoo Studio tater menu");
            stage.show();
            loginButton.getScene().getWindow().hide();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
