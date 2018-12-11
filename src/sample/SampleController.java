package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import versione1.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SampleController{


    @FXML
    private ListView categoryListView;
    private ListView eventListView;

    @FXML
    //private ListView eventListView;
    private SocialNetwork socialNetwork;
    private EventSoccerMatch eventSoccerMatch;


    //Metodi

    public void setSocialNetwork(SocialNetwork socialNetwork) { this.socialNetwork = socialNetwork; }

    public SocialNetwork getSocialNetwork() { return socialNetwork;}

    public EventSoccerMatch getEventSoccerMatch() { return eventSoccerMatch; }

    public void setEventSoccerMatch(EventSoccerMatch eventSoccerMatch) { this.eventSoccerMatch = eventSoccerMatch; }


    @FXML
    private void initialize() {
        System.out.println("Carico la View...");

        for (Category category : socialNetwork.getCategories()) { // popolo automaticamente la ListView con gli elementi dell'array caetgories di SocialNetwork
            categoryListView.getItems().add(category.getName());
        }




    }



}
