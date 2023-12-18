package com.studio.tattoostudio.controllers;

import com.studio.tattoostudio.buisness.DesignPictureManager;
import com.studio.tattoostudio.data.DateOfTattoo;
import com.studio.tattoostudio.data.Design;
import com.studio.tattoostudio.data.TattooArtist;
import com.studio.tattoostudio.exceptions.EntityNotFoundException;
import com.studio.tattoostudio.factory.Factory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TatterMenuSceneController {
    @FXML
    private Button addDesignButton;
    @FXML
    private GridPane designsGridPane;
    @FXML
    private Button deleteDesignButton;
    @FXML
    private Button editReservationButton;
    @FXML
    private ListView<DateOfTattoo> reservationsTableView;
    private String artist;
    private ImageView selectedImageView;
    public TatterMenuSceneController(String artist) {
        this.artist = artist;
    }

    @FXML
    void initialize() {
        reservationsTableView.setItems(FXCollections.observableList(Factory.INSTANCE.getDateOfTattooDao().getAllByArtist(artist)));
        List<Design> designs = Factory.INSTANCE.getDesignDao().getAllByArtist(artist);
        for (int i = 0; i < designs.size(); i++) {
            Design design = designs.get(i);
            int row = i / 3;
            int column = i % 3;
            Image image = DesignPictureManager.convertToPicture(design.getPicture());
            ImageView imageView = new ImageView(image);
            imageView.setOnMouseClicked(event -> setSelectedImageView(imageView));
            imageView.setId(design.getId().toString());
            designsGridPane.add(imageView, column, row);
        }
    }

    @FXML
    void onAddDesignButton(ActionEvent event) {
        NewDesignSceneController controller = new NewDesignSceneController(artist);
        openNewDesignScene(controller);
    }

    @FXML
    void onDeleteDesignButton(ActionEvent event) {
        if (selectedImageView != null) {
            String id = selectedImageView.getId();
            try {
                Factory.INSTANCE.getDesignDao().delete(Long.parseLong(id));
            } catch (EntityNotFoundException e) {
                throw new RuntimeException(e);
            }
            selectedImageView.setImage(null);
            selectedImageView = null;
        }
    }

    @FXML
    void onEditReservartionButton(ActionEvent event) {

    }
    private void openNewDesignScene(NewDesignSceneController newDesignSceneController) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com.studio.tattoostudio/NewDesignScene.fxml"));
            fxmlLoader.setController(newDesignSceneController);
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Tattoo Studio new design");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void openEditReservationScene(NewDesignSceneController newDesignSceneController) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com.studio.tattoostudio/TattooDescriptionScene.fxml"));
            fxmlLoader.setController(newDesignSceneController);
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Tattoo Studio edit reservation");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setSelectedImageView(ImageView imageView) {
        this.selectedImageView = imageView;
    }
}
