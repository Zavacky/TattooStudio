package com.studio.tattoostudio.Controllers;

import com.studio.tattoostudio.Tattoo;
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
    private ComboBox<String> clientoradminComboBox;

    @FXML
    private CheckBox VisibilityCheckBox;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField VisiblePasswordField;


    @FXML
    void onLoginUser(ActionEvent event) {
    
    }

    @FXML
    void onRegisterUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Tattoo.class.getResource("RegistrationScene.fxml"));
            RegistrationController controller = new RegistrationController();
            loader.setController(controller);
            Parent registerScene = loader.load();
            Scene scene = new Scene(registerScene);
            Stage registrationStage = (Stage) registerButton.getScene().getWindow();
            ;
            registrationStage.setScene(scene);
            registrationStage.setTitle("Register - TattooStudio");
            registrationStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void initialize() {
        // Initialize the ComboBox with two user types
        clientoradminComboBox.getItems().addAll("Zakaznik", "Tater");

        // Optionally set a default value
        clientoradminComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    void onChooseUser(ActionEvent event) {
        String selectedUserType = clientoradminComboBox.getValue();

        // Handle the selection
        if ("Zakaznik".equals(selectedUserType)) {
            // Logic for when 'Zakaznik' is selected
            System.out.println("Customer selected");
        } else if ("Tater".equals(selectedUserType)) {
            // Logic for when 'Tater' is selected
            System.out.println("Tattoo Artist selected");
        } else {
            // Logic for no selection or unexpected value
            System.out.println("No valid user type selected");
        }
    }

    @FXML
    void onVisibleCheck(ActionEvent event) {
        boolean isChecked = VisibilityCheckBox.isSelected();
        VisiblePasswordField.setVisible(isChecked);
        VisiblePasswordField.setManaged(isChecked);
        passwordTextfield.setVisible(!isChecked);
        passwordTextfield.setManaged(!isChecked);

        if (isChecked) {
            VisiblePasswordField.setText(passwordTextfield.getText());
        } else {
            passwordTextfield.setText(VisiblePasswordField.getText());
        }
    }


}
