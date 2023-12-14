package com.studio.tattoostudio.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Pagination;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuScene implements Initializable{
        @FXML
        private Pagination oldphotosPagination;

        @FXML
        private ChoiceBox<String> whatcityChoiceBox;

        public void ini

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                whatcityChoiceBox.getItems().addAll("Kosice", "Bardejov", "Humenne");
        }
}



