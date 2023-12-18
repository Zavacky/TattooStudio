package com.studio.tattoostudio.controllers;
import com.studio.tattoostudio.data.DateOfTattoo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    private DateOfTattoo date;

    public TattooDescriptionController() {
    }
    public TattooDescriptionController(DateOfTattoo date) {
        this.date = date;
    }

    @FXML
    void initialize() {
        placeOnBodyComboBox.getItems().addAll("head", "chest", "back", "left arm", "right arm", "left leg", "right leg");
        placeOnBodyComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    void onIfBWTattoo(ActionEvent event) {
        //TODO
    }

    @FXML
    void onIfColorTattoo(ActionEvent event) {
        //TODO
    }

    @FXML
    void onReservationrequest(ActionEvent event) {
        //TODO
    }
}
