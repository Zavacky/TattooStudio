package com.studio.tattoostudio.controllers;
import com.studio.tattoostudio.buisness.DesignPictureManager;
import com.studio.tattoostudio.data.Client;
import com.studio.tattoostudio.data.Design;
import com.studio.tattoostudio.data.TattooArtist;
import com.studio.tattoostudio.factory.Factory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TatterProfileSceneController {
    @FXML
    private GridPane designsGridPane;
    @FXML
    private Label specializationLabel;
    @FXML
    private Label mailLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label numberLabel;
    @FXML
    private Button reservationButton;
    private TattooArtist tattooArtist;
    private Client client;

    public TatterProfileSceneController(TattooArtist tattooArtist, Client client) {
        this.tattooArtist = tattooArtist;
        this.client = client;
    }

    @FXML
    void initialize() {
        nameLabel.setText((nameLabel.getText()) + " " +  tattooArtist.getName());
        numberLabel.setText((numberLabel.getText() + " " + tattooArtist.getPhoneNumber()));
        mailLabel.setText((mailLabel.getText() + " " + tattooArtist.getMail()));
        specializationLabel.setText(tattooArtist.getSpecialization());
        List<Design> designs = Factory.INSTANCE.getDesignDao().getAllByArtist(tattooArtist.getLogin());
        for (int i = 0; i < designs.size(); i++) {
            Design design = designs.get(i);
            int row = i / 3;
            int column = i % 3;
            Image image = DesignPictureManager.convertToPicture(design.getPicture());
            designsGridPane.add(new ImageView(image), column, row);
        }
    }
    @FXML
    void onReservationButton(ActionEvent event) {
        DecideSceneController decideSceneController = new DecideSceneController(tattooArtist.getLogin(), client);
        opendecideScene(decideSceneController);
    }

    private void opendecideScene(DecideSceneController decideSceneController) {
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.studio.tattoostudio/DecideScene.fxml"));
        loader.setController(decideSceneController);
        Parent tatterProfileSceneParent = loader.load();
        Scene tatterProfileScene = new Scene(tatterProfileSceneParent);
        Stage tatterProfileStage = new Stage();
        tatterProfileStage.setScene(tatterProfileScene);
        tatterProfileStage.setResizable(false);
        tatterProfileStage.setTitle("Tattoo date reservartion");
        tatterProfileStage.initModality(Modality.APPLICATION_MODAL);
        tatterProfileStage.show();
        this.mailLabel.getScene().getWindow().hide();
        } catch (
        IOException e) {
            throw new RuntimeException(e);
        }
    }
}
