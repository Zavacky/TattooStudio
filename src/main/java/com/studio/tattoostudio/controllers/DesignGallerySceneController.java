package com.studio.tattoostudio.controllers;

import com.studio.tattoostudio.buisness.DesignPictureManager;
import com.studio.tattoostudio.data.Client;
import com.studio.tattoostudio.data.Design;
import com.studio.tattoostudio.factory.Factory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DesignGallerySceneController {
    @FXML
    private Button chooseDesignButton;
    @FXML
    private GridPane galleryGridPane;
    private String artist;
    private Client client;
    private ImageView selectedImageView;
    public DesignGallerySceneController(String artist, Client client) {
        this.artist = artist;
    }
    @FXML
    void initialize() {
        List<Design> designs = Factory.INSTANCE.getDesignDao().getAllByArtist(artist);
        for (int i = 0; i < designs.size(); i++) {
            Design design = designs.get(i);
            int row = i / 3;
            int column = i % 3;
            Image image = DesignPictureManager.convertToPicture(design.getPicture());
            ImageView imageView = new ImageView(image);
            imageView.setId(design.getId().toString());
            imageView.setOnMouseClicked(event -> selectImageView(imageView));
            galleryGridPane.add(imageView, column, row);
        }
    }

    private void selectImageView(ImageView imageView) {
        this.selectedImageView = imageView;
    }

    @FXML
    void onChooseDesignButton(ActionEvent event) {
        ChoseDesignReservationSceneController controller = new ChoseDesignReservationSceneController(artist, client, selectedImageView);
        openChooseDesignReservationScene(controller);
    }

    private void openChooseDesignReservationScene(ChoseDesignReservationSceneController choseDesignReservationSceneController) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com.studio.tattoostudio/ChoseDesignReservartionScene.fxml"));
            loader.setController(choseDesignReservationSceneController);
            Parent tattooDescriptionTattooSceneParent = loader.load();
            Scene tattooDescriptionTattooScene = new Scene(tattooDescriptionTattooSceneParent);
            Stage tattooDescriptionTattooStage = new Stage();
            tattooDescriptionTattooStage.setScene(tattooDescriptionTattooScene);
            tattooDescriptionTattooStage.setResizable(false);
            tattooDescriptionTattooStage.setTitle("Tattoo reservation");
            tattooDescriptionTattooStage.initModality(Modality.APPLICATION_MODAL);
            tattooDescriptionTattooStage.show();
            chooseDesignButton.getScene().getWindow().hide();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
