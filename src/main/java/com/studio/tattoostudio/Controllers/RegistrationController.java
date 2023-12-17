package com.studio.tattoostudio.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationController {

    @FXML
    private Label registrationAlert;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button registerButton;



    @FXML
    void onRegister(ActionEvent event) {
        String username = loginTextField.getText();
        String password = passwordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();

        if (isValidUsername(username) && isValidPassword(password) && password.equals(confirmPassword)) {
            // Implement your registration logic here
            // For example, adding a new user to the database

            registrationAlert.setText("Registration successful for username: " + username);
        } else {
            // Handle registration failure
            registrationAlert.setText("Registration failed. Please check your details.");
        }
    }

    private boolean isValidUsername(String username) {
        // Implement your username validation logic here
        return username != null && !username.trim().isEmpty();
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    // Add any additional methods or logic as per your application's requirements
}
