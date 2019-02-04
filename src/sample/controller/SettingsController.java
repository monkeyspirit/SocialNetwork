package sample.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
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
    private ListView<CheckBox> catgPrefListView;

    private List<CheckBox> categoryCheck;
    private ArrayList<String> selectedCategory;
    private List<String> categoryAlrPref;


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

        categoryCheck = new ArrayList<>();

        categoryAlrPref = sessionUser.getCategoryPref();

        for(Category category : socialNetwork.getCategories()){

            CheckBox catCheck = new CheckBox();
            catCheck.setText(category.getName());
            categoryCheck.add(catCheck);

            for(String categoryPref: categoryAlrPref){
                if(categoryPref.equals(catCheck.getText())){
                    catCheck.setSelected(true);
                }
            }
        }


        // Questa parte serve per creare il check box nella list view

        catgPrefListView.setItems(FXCollections.observableList(categoryCheck));


    }

    public void delete(){
        thisStage.close();
    }

    public void saveExit(){

        selectedCategory = new ArrayList<>(); // array di stringhe delle categorie preferite

        for(CheckBox check: categoryCheck){
            if(check.isSelected()){
                selectedCategory.add(check.getText());
            }
        }
        sessionUser.setCategoryPref(selectedCategory);

        if (minAgeCB.getValue() != null && maxAgeCB.getValue() != null) {
            ageGroup.setRange(minAgeCB.getValue(), maxAgeCB.getValue());
            String ageRangeIns = ageGroup.getRange();
            sessionUser.setAgeRange(ageRangeIns);
        }



        socialNetwork.updateUsersListFile();
        thisStage.close();
    }




    }
