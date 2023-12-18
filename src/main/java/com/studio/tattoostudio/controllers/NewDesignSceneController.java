package com.studio.tattoostudio.controllers;

import com.studio.tattoostudio.buisness.DesignPictureManager;
import com.studio.tattoostudio.data.Design;
import com.studio.tattoostudio.exceptions.EntityNotFoundException;
import com.studio.tattoostudio.factory.Factory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;

public class NewDesignSceneController {
    @FXML
    private Button chooseImageButton;
    @FXML
    private Button saveButton;
    @FXML
    private Pane selectedPicturePane;
    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextField priceTextField;
    private String artist;
    private Image image;

    public NewDesignSceneController(String artist) {
        this.artist = artist;
    }

    @FXML
    void onChooseImageButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png", "*.gif", "*.bmp")
        );
        // Show the file dialog and retrieve the selected file
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Convert the selected image to a byte array and update the ImageView
            Image image = new Image(selectedFile.toURI().toString());
            this.image = image;
            selectedPicturePane.getChildren().add(new ImageView(image));
        }
    }

    @FXML
    void onSaveButton(ActionEvent event) {
        Design design = new Design(artist, DesignPictureManager.convertToBytes(image), Integer.parseInt(priceTextField.getText()), descriptionTextArea.getText());
        try {
            Factory.INSTANCE.getDesignDao().save(design);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
        saveButton.getScene().getWindow().hide();
    }
}
