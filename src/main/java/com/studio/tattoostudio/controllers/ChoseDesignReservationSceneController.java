package com.studio.tattoostudio.controllers;

import com.studio.tattoostudio.data.Client;
import com.studio.tattoostudio.data.DateOfTattoo;
import com.studio.tattoostudio.data.Design;
import com.studio.tattoostudio.data.TattooArtist;
import com.studio.tattoostudio.exceptions.TattooArtistDoesntExistException;
import com.studio.tattoostudio.factory.Factory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ChoseDesignReservationSceneController {
    @FXML
    private ChoiceBox<String> bodyPartChoiceBox;
    @FXML
    private Pane designPane;
    @FXML
    private ChoiceBox<String> preferedDayChoiceBox;
    @FXML
    private Button reservationButton;
    @FXML
    private TextArea descriptionTextField;
    private String artist;
    private ImageView selectedImageView;
    private Client client;

    public ChoseDesignReservationSceneController(String artist, Client client, ImageView selectedImageView) {
        this.artist = artist;
        this.client = client;
        this.selectedImageView = selectedImageView;
    }

    @FXML
    void initialize() {
        bodyPartChoiceBox.getItems().addAll("head", "neck", "neck", "shoulder", "chest", "back", "left arm", "right arm", "left leg", "right leg");
        preferedDayChoiceBox.getItems().addAll("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        bodyPartChoiceBox.getSelectionModel().selectFirst();
        preferedDayChoiceBox.getSelectionModel().selectFirst();
    }
    @FXML
    void onReservationButton(ActionEvent event) {
        TattooArtist tattooArtist = null;
        try {
            tattooArtist = Factory.INSTANCE.getTattooArtistDao().getByLogin(artist);
        } catch (TattooArtistDoesntExistException e) {
            throw new RuntimeException(e);
        }
        Design design = Factory.INSTANCE.getDesignDao().getById(Long.valueOf(selectedImageView.getId()));
        Factory.INSTANCE.getDateOfTattooDao().save(new DateOfTattoo(client, tattooArtist, design, null, descriptionTextField.getText() + " " + preferedDayChoiceBox.getValue() + " " + bodyPartChoiceBox.getValue()));
        reservationButton.getScene().getWindow().hide();
    }
}
