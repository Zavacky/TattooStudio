package com.studio.tattoostudio.controllers;

import com.studio.tattoostudio.data.DateOfTattoo;
import com.studio.tattoostudio.data.TattooArtist;
import com.studio.tattoostudio.exceptions.TattooArtistDoesntExistException;
import com.studio.tattoostudio.factory.Factory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EditReservationSceneController {
    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<Time> timeChoiceBox;
    private String artistLogin;
    private DateOfTattoo dateOfTattoo;
    public EditReservationSceneController(String artistLogin, DateOfTattoo dateOfTattoo) {
        this.artistLogin = artistLogin;
        this.dateOfTattoo = dateOfTattoo;
    }

    @FXML
    void initialize() {
        datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        });
        List<Time> times = new ArrayList<>();
        for (int i = 8; i < 18; i++) {
            for (int j = 0; j < 60; j += 30) {
                times.add(new Time(i, j, 0));
            }
        }
        timeChoiceBox.setItems(FXCollections.observableArrayList(times));
        nameLabel.setText(dateOfTattoo.getClient().getName());
        descriptionLabel.setText(dateOfTattoo.getNotes());
    }

    @FXML
    void onCancelButton(ActionEvent event) {
        cancelButton.getScene().getWindow().hide();
    }

    @FXML
    void onSaveButton(ActionEvent event) {
        Time time = timeChoiceBox.getSelectionModel().getSelectedItem();
        Date date = Date.valueOf(datePicker.getValue());
        TattooArtist tattooArtist;
        try {
            tattooArtist = Factory.INSTANCE.getTattooArtistDao().getByLogin(artistLogin);
        } catch (TattooArtistDoesntExistException e) {
            throw new RuntimeException(e);
        }
        DateOfTattoo newDateOfTattoo = new DateOfTattoo(dateOfTattoo.getId(), dateOfTattoo.getClient(), tattooArtist, dateOfTattoo.getDesign(), LocalDateTime.of(date.toLocalDate(), time.toLocalTime()), dateOfTattoo.getNotes());
        Factory.INSTANCE.getDateOfTattooDao().save(newDateOfTattoo);
        cancelButton.getScene().getWindow().hide();
    }
}
