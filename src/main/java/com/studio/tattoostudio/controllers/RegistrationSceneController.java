package com.studio.tattoostudio.controllers;

import com.studio.tattoostudio.data.Client;
import com.studio.tattoostudio.exceptions.ClientDoesntExistException;
import com.studio.tattoostudio.factory.Factory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class RegistrationSceneController {
    @FXML
    private Label allFieldsMandatoryLabel;
    @FXML
    private Label differentPasswordsLabel;
    @FXML
    private PasswordField confirmPasswordTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private Label incorrectEmailLabel;
    @FXML
    private Label incorrectNumberLabel;
    @FXML
    private Button loginButton;
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordTextfield;
    @FXML
    private TextField phoneTextField;
    @FXML
    private Button registerButton;
    @FXML
    private TextField surnameTextField;
    @FXML
    private Label existingUserLabel;


    private ChangeListener<String> textChangeListener = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (newValue.isEmpty() || newValue == null) {
                allFieldsMandatoryLabel.setVisible(true);
                registerButton.setDisable(true);
            }else {
                allFieldsMandatoryLabel.setVisible(false);
                registerButton.setDisable(false);
            }
        }
    };

    @FXML
    void initialize() {
        loginTextField.textProperty().addListener(textChangeListener);
        passwordTextfield.textProperty().addListener(textChangeListener);
        confirmPasswordTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals(passwordTextfield.getText())) {
                    differentPasswordsLabel.setVisible(true);
                    registerButton.setDisable(true);
                }else {
                    differentPasswordsLabel.setVisible(false);
                    registerButton.setDisable(false);
                }
            }
        });
        phoneTextField.textProperty().addListener(textChangeListener);
        emailTextField.textProperty().addListener(textChangeListener);
        surnameTextField.textProperty().addListener(textChangeListener);
        firstNameTextField.textProperty().addListener(textChangeListener);
        emailTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!emailTextField.textProperty().getValue().contains("@")) {
                    incorrectEmailLabel.setVisible(true);
                    registerButton.setDisable(true);
                }else {
                    incorrectEmailLabel.setVisible(false);
                    registerButton.setDisable(false);
                }
            }
        });
        phoneTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!phoneTextField.textProperty().getValue().startsWith("+")) {
                    incorrectNumberLabel.setVisible(true);
                    registerButton.setDisable(true);
                }else {
                    incorrectNumberLabel.setVisible(false);
                    registerButton.setDisable(false);
                }
            }
        });
        if (loginTextField.getText().isEmpty() || passwordTextfield.getText().isEmpty() || confirmPasswordTextField.getText().isEmpty() ||
                phoneTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() ||
                firstNameTextField.getText().isEmpty()) {
            registerButton.setDisable(true);
        }
    }
    @FXML
    void onCreateUser(ActionEvent event) {
        try {
            Factory.INSTANCE.getClientDao().saveClient(new Client(loginTextField.getText(),
                    passwordTextfield.getText(),
                    firstNameTextField.getText(),
                    surnameTextField.getText(),
                    emailTextField.getText(),
                    phoneTextField.getText()));
            existingUserLabel.setVisible(false);
        } catch (ClientDoesntExistException e) {
            existingUserLabel.setVisible(true);
        }
        if (!existingUserLabel.isVisible()) {
            this.emailTextField.getScene().getWindow().hide();
        }
    }

    @FXML
    void onLoginUser(ActionEvent event) {
        this.emailTextField.getScene().getWindow().hide();
    }
}
