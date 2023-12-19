package com.studio.tattoostudio.controllers;
import com.studio.tattoostudio.data.Client;
import com.studio.tattoostudio.data.DateOfTattoo;
import com.studio.tattoostudio.data.TattooArtist;
import com.studio.tattoostudio.exceptions.IncorrectLoginOrPasswordException;
import com.studio.tattoostudio.exceptions.TattooArtistDoesntExistException;
import com.studio.tattoostudio.factory.Factory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TattooDescriptionController {
    @FXML
    private CheckBox bwtattooCheckBox;

    @FXML
    private CheckBox colorTattooCheckBox;

    @FXML
    private ComboBox<String> placeOnBodyComboBox;

    @FXML
    private Button reservationrequestButton;

    @FXML
    private TextArea tattooDescriptionTextArea;

    @FXML
    private TextField tattooSizeXTextField;
    @FXML
    private TextField tattooSizeYTextField;
    @FXML
    private ChoiceBox<String> preferedDayChoiceBox;
    private boolean bwtattoo;
    private boolean colorTattoo;
    private String artistLogin;
    private String clientLogin;

    public TattooDescriptionController(String artistLogin, String clientLogin) {
        this.artistLogin = artistLogin;
        this.clientLogin = clientLogin;
    }

    @FXML
    void initialize() {
        placeOnBodyComboBox.getItems().addAll("head", "face", "neck", "chest", "back", "left arm", "right arm", "left leg", "right leg");
        placeOnBodyComboBox.getSelectionModel().selectFirst();
        preferedDayChoiceBox.getItems().addAll("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        preferedDayChoiceBox.getSelectionModel().selectFirst();
    }

    @FXML
    void onIfBWTattoo(ActionEvent event) {
        this.bwtattoo = bwtattooCheckBox.isSelected();
    }

    @FXML
    void onIfColorTattoo(ActionEvent event) {
        this.colorTattoo = colorTattooCheckBox.isSelected();
    }

    @FXML
    void onReservationrequest(ActionEvent event) {
        TattooArtist artist;
        Client client;
        try {
            artist = Factory.INSTANCE.getTattooArtistDao().getByLogin(artistLogin);
            client = Factory.INSTANCE.getClientDao().getByLogin(clientLogin);
        } catch (TattooArtistDoesntExistException e) {
            throw new RuntimeException(e);
        } catch (IncorrectLoginOrPasswordException e) {
            throw new RuntimeException(e);
        }
        DateOfTattoo dateOfTattoo;
        if (bwtattoo) {
            dateOfTattoo = new DateOfTattoo(client, artist, null, null, "Black and white tattoo " +
                    "size: " + tattooSizeXTextField.getText() + "x" + tattooSizeYTextField.getText() +
                    " place on body: " + placeOnBodyComboBox.getSelectionModel().getSelectedItem() +
                    " description: " + tattooDescriptionTextArea.getText() +
                    " prefered day: " + preferedDayChoiceBox.getSelectionModel().getSelectedItem());
        } else if (colorTattoo) {
            dateOfTattoo = new DateOfTattoo(client, artist, null, null, "Color tattoo " +
                    "size: " + tattooSizeXTextField.getText() + "x" + tattooSizeYTextField.getText() +
                    " place on body: " + placeOnBodyComboBox.getSelectionModel().getSelectedItem() +
                    " description: " + tattooDescriptionTextArea.getText() +
                    " prefered day: " + preferedDayChoiceBox.getSelectionModel().getSelectedItem());
        }else{
            dateOfTattoo = new DateOfTattoo(client, artist, null, null, "Not selected color variation " +
                    "size: " + tattooSizeXTextField.getText() + "x" + tattooSizeYTextField.getText() +
                    " place on body: " + placeOnBodyComboBox.getSelectionModel().getSelectedItem() +
                    " description: " + tattooDescriptionTextArea.getText() +
                    " prefered day: " + preferedDayChoiceBox.getSelectionModel().getSelectedItem());
        }
        Factory.INSTANCE.getDateOfTattooDao().save(dateOfTattoo);
        reservationrequestButton.getScene().getWindow().hide();
    }
}
