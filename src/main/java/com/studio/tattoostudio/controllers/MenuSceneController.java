package com.studio.tattoostudio.controllers;
import com.studio.tattoostudio.TattooStudioApplication;
import com.studio.tattoostudio.buisness.DesignPictureManager;
import com.studio.tattoostudio.dao.StudioDao;
import com.studio.tattoostudio.data.Client;
import com.studio.tattoostudio.data.DateOfTattoo;
import com.studio.tattoostudio.data.Studio;
import com.studio.tattoostudio.data.TattooArtist;
import com.studio.tattoostudio.exceptions.EntityNotFoundException;
import com.studio.tattoostudio.exceptions.IncorrectLoginOrPasswordException;
import com.studio.tattoostudio.factory.Factory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MenuSceneController {
    @FXML
    private ListView<TattooArtist> artistsTableView;
    @FXML
    private Pagination oldphotosPagination;
    @FXML
    private ChoiceBox<Studio> salonesChoiceBox;
    @FXML
    private Tab studiosTab;
    @FXML
    private Button showArtist;
    @FXML
    private ListView<DateOfTattoo> datesTableView;
    @FXML
    private Button deleteReservartionButton;
    @FXML
    private Button refreshButton;
    private final StudioDao studioDao = Factory.INSTANCE.getStudioDao();
    private String client;

    public MenuSceneController(String client) {
        this.client = client;
    }

    @FXML
    void initialize() {
        salonesChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Studio>() {
            @Override
            public void changed(ObservableValue<? extends Studio> observable, Studio oldValue, Studio newValue) {
                artistsTableView.setItems(FXCollections.observableList(Factory.INSTANCE.getTattooArtistDao().getAllByStudio(salonesChoiceBox.getSelectionModel().getSelectedItem().getId())));
            }
        });
        salonesChoiceBox.setItems(FXCollections.observableList(studioDao.getAll()));
        salonesChoiceBox.getSelectionModel().selectFirst();
        artistsTableView.setItems(FXCollections.observableList(Factory.INSTANCE.getTattooArtistDao().getAllByStudio(salonesChoiceBox.getSelectionModel().getSelectedItem().getId())));
        datesTableView.setItems(FXCollections.observableList(Factory.INSTANCE.getDateOfTattooDao().getAllByClient(client)));

        List<Image> images = Arrays.asList(
                new Image(getClass().getResourceAsStream("/Pictures/love ink front table.jpg"), 300, 300, true, true),
                new Image(getClass().getResourceAsStream("/Pictures/tattoo_work_1.jpg"), 300, 300, true, true),
                new Image(getClass().getResourceAsStream("/Pictures/love ink couch.jpg"), 300, 300, true, true),
                new Image(getClass().getResourceAsStream("/Pictures/tattoo_work_2.jpg"), 300, 300, true, true),
                new Image(getClass().getResourceAsStream("/Pictures/love ink seats.jpg"), 300, 300, true, true)
        );
        List<ImageView> imageViews = images.stream()
                .map(ImageView::new)
                .toList();
        oldphotosPagination.setPageFactory(imageViews::get);
    }

    @FXML
    void onShowArtistButton(ActionEvent event) {
        TattooArtist artist = artistsTableView.getSelectionModel().getSelectedItem();
        Client client = null;
        try {
            client = Factory.INSTANCE.getClientDao().getByLogin(this.client);
        } catch (IncorrectLoginOrPasswordException e) {
            throw new RuntimeException(e);
        }
        TatterProfileSceneController tatterProfileSceneController = new TatterProfileSceneController(artist, client);
        openTatterProfileScene(tatterProfileSceneController);
        reloadTable();
    }
    @FXML
    void onDeleteReservartionButton(ActionEvent event) {
        DateOfTattoo date = datesTableView.getSelectionModel().getSelectedItem();
        try {
            Factory.INSTANCE.getDateOfTattooDao().delete(date.getId());
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
        reloadTable();
    }
    @FXML
    void onRefreshButton(ActionEvent event) {
        reloadTable();
    }

    private void openTattooDescriptionTattooScene(TattooDescriptionController tattooDescriptionController) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com.studio.tattoostudio/TattooDescriptionScene.fxml"));
            loader.setController(tattooDescriptionController);
            Parent tattooDescriptionTattooSceneParent = loader.load();
            Scene tattooDescriptionTattooScene = new Scene(tattooDescriptionTattooSceneParent);
            Stage tattooDescriptionTattooStage = new Stage();
            tattooDescriptionTattooStage.getIcons().add(new Image(getClass().getResourceAsStream("/Pictures/ikona.jpeg")));
            tattooDescriptionTattooStage.setScene(tattooDescriptionTattooScene);
            tattooDescriptionTattooStage.setResizable(false);
            tattooDescriptionTattooStage.setTitle("Tattoo reservation");
            tattooDescriptionTattooStage.initModality(Modality.APPLICATION_MODAL);
            tattooDescriptionTattooStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void openTatterProfileScene(TatterProfileSceneController tatterProfileSceneController) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com.studio.tattoostudio/TatterProfileScene.fxml"));
            loader.setController(tatterProfileSceneController);
            Parent tatterProfileSceneParent = loader.load();
            Scene tatterProfileScene = new Scene(tatterProfileSceneParent);
            Stage tatterProfileStage = new Stage();
            tatterProfileStage.getIcons().add(new Image(getClass().getResourceAsStream("/Pictures/ikona.jpeg")));
            tatterProfileStage.setScene(tatterProfileScene);
            tatterProfileStage.setResizable(false);
            tatterProfileStage.setTitle("Tattoo Artist");
            tatterProfileStage.initModality(Modality.APPLICATION_MODAL);
            tatterProfileStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void reloadTable(){
        datesTableView.setItems(FXCollections.observableList(Factory.INSTANCE.getDateOfTattooDao().getAllByClient(client)));
    }
}