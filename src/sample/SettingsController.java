package sample;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import versione1.AgeGroup;
import versione1.Category;
import versione1.SocialNetwork;
import versione1.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SettingsController {

    private User sessionUser;
    private SocialNetwork socialNetwork;
    private Stage thisStage;

    private AgeGroup ageGroup;
    private ArrayList<Integer> ageRangeMin;

    @FXML
    private ChoiceBox<Integer> minAgeCB, maxAgeCB;
    @FXML
    private Label usernameLbl;
    @FXML
    private ListView catgPrefListView;

    private List<String> catName;
    private ArrayList<String> selectedCategory;

    // ~~~~~~~~ Metodi ~~~~~~~~~~~~~

    public void setSocialNetwork(SocialNetwork socialNetwork) { this.socialNetwork = socialNetwork; }

    public void setSessionUser(User sessionUser) { this.sessionUser = sessionUser; }

    public void setThisStage(Stage thisStage) { this.thisStage = thisStage; }


    @FXML
    private void initialize() throws IOException {
        ageGroup = new AgeGroup();
        ageRangeMin = ageGroup.getNumeri();

        minAgeCB.setItems(FXCollections.observableArrayList(ageRangeMin));

        /**
         * ActionListeren che permette una volta selezionato il minimo dell'età di selezionare il max, in questo modo lo popolo con i
         * numeri maggiorni stretti del minimo, dopo i 20 anni si va di decina in decina per facilitare
         * la scelta all'utente
         */
        minAgeCB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                maxAgeCB.setDisable(false);
                ArrayList<Integer> ageRangeMax = ageGroup.getMinOf(minAgeCB.getSelectionModel().getSelectedItem());
                maxAgeCB.setItems(FXCollections.observableArrayList(ageRangeMax));
            }
        });

        usernameLbl.setText(sessionUser.getUsername());

        catName = new ArrayList<>();
        for(Category category : socialNetwork.getCategories()){
            catName.add(category.getName());
        }

        // Questa parte serve per creare il check box nella list view

        catgPrefListView.setItems(FXCollections.observableList(catName));

        selectedCategory = new ArrayList<>(); // array di stringhe delle categorie preferite
        catgPrefListView.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(String item) {
                BooleanProperty observable = new SimpleBooleanProperty();
                observable.addListener((obs, wasSelected, isNowSelected) -> {
                    if (isNowSelected) {
                        selectedCategory.add(item);
                    } else {
                        selectedCategory.remove(item);
                    }
                });
                return observable;
            }
        }));




    }

    public void delete(){
        thisStage.close();
        for (String sel : selectedCategory) {
            System.out.println(sel);
        }
    }

    public void saveExit(){

        if (minAgeCB.getValue() != null && maxAgeCB.getValue() != null) {
            ageGroup.setRange(minAgeCB.getValue(), maxAgeCB.getValue());
            String ageRangeIns = ageGroup.getRange();
            sessionUser.setAgeRange(ageRangeIns);
        }

        sessionUser.setCategoryPref(selectedCategory);


        socialNetwork.updateUsersListFile();
        thisStage.close();
    }




    }
