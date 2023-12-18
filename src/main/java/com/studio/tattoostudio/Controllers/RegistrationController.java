package com.studio.tattoostudio.Controllers;

import com.studio.tattoostudio.Tattoo;
import com.studio.tattoostudio.data.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationController {
    @FXML
    private Button RegisterButton;

    @FXML
    private PasswordField passwordTextfield;

    @FXML
    private PasswordField ConfirmPasswordTextField;

    @FXML
    private ImageView logoImageview;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginTextField;

    @FXML
    void onLoginUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Tattoo.class.getResource("LoginScene.fxml"));
            LoginSceneController controller = new LoginSceneController();
            loader.setController(controller);
            Parent loginScene = loader.load();
            Scene scene = new Scene(loginScene);

            Stage loginStage = (Stage) loginButton.getScene().getWindow();;
            loginStage.setScene(scene);
            loginStage.setTitle("Login - TattooStudio");
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onCreateUser(ActionEvent event) {
        String username = loginTextField.getText();


        try {
            User newUser = new User();
            String passw = passwordTextfield.getText();
            if (passw == null){
                return;
            }
            newUser.(username);

            String conpassw = ConfirmPasswordTextField.getText();
            if (passw.equals(conpassw)){
                if (isValidPassword(passw)){
                    newUser.setPassUser(passw);
                    Client userDao = DaoFactory.INSTANCE.getUserDao();
                    if (userDao.add(newUser)){
                        RegistrationAlert.setText("Account created!");
                    }else {
                        RegistrationAlert.setText("Change Username!");
                    }
                }else {
                    RegistrationAlert.setText("Password isnt strong!");
                }
            }else {
                RegistrationAlert.setText("Passwords must be same!");
            }
        }catch (EmptyResultDataAccessException e){
            RegistrationAlert.setText("Registration is incorrect!");
        }

    }


    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
