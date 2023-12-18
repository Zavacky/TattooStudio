package com.studio.tattoostudio.controllers;

import com.studio.tattoostudio.data.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DecideSceneController {
    @FXML
    private Button vlastnyNavrhButton;
    @FXML
    private Button vyberNavrhButton;
    private String artist;
    private Client client;

    public DecideSceneController(String login, Client client) {
        this.artist = login;
        this.client = client;
    }
    @FXML
    void onVlastnyNavrhButton(ActionEvent event) {
        TattooDescriptionController tattooDescriptionController = new TattooDescriptionController();
        openDescriptionScene(tattooDescriptionController);
    }

    @FXML
    void onVyberNavrhuButton(ActionEvent event) {
        DesignGallerySceneController designGallerySceneController = new DesignGallerySceneController(artist, client);
        openDesignGalleryScene(designGallerySceneController);
    }

    private void openDesignGalleryScene(DesignGallerySceneController designGallerySceneController) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com.studio.tattoostudio/DesignGalleryScene.fxml"));
            fxmlLoader.setController(designGallerySceneController);
            Parent tatterProfileSceneParent = fxmlLoader.load();
            Scene tatterProfileScene = new Scene(tatterProfileSceneParent);
            Stage tatterProfileStage = new Stage();
            tatterProfileStage.setScene(tatterProfileScene);
            tatterProfileStage.setResizable(false);
            tatterProfileStage.setTitle("Tattoo gallery");
            tatterProfileStage.initModality(Modality.APPLICATION_MODAL);
            tatterProfileStage.show();
            this.vlastnyNavrhButton.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openDescriptionScene(TattooDescriptionController tattooDescriptionController) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com.studio.tattoostudio/TattooDescriptionScene.fxml"));
            fxmlLoader.setController(tattooDescriptionController);
            Parent tatterProfileSceneParent = fxmlLoader.load();
            Scene tatterProfileScene = new Scene(tatterProfileSceneParent);
            Stage tatterProfileStage = new Stage();
            tatterProfileStage.setScene(tatterProfileScene);
            tatterProfileStage.setResizable(false);
            tatterProfileStage.setTitle("Tattoo date reservartion");
            tatterProfileStage.initModality(Modality.APPLICATION_MODAL);
            tatterProfileStage.show();
            this.vlastnyNavrhButton.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
