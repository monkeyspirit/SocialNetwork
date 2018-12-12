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
    @FXML
    private ListView eventListView;

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

        ObservableList<Category> categories = FXCollections.observableArrayList(socialNetwork.getCategories());
        ObservableList<Event> events;

        for (int i=0; i<categories.size();i++) { // popolo automaticamente la ListView con gli elementi dell'array caetgories di SocialNetwork
            categoryListView.getItems().add(categories.get(i).getName());
            events = FXCollections.observableArrayList( FXCollections.observableArrayList(categories.get(i).getEvents()));
            for (int j=0; j<events.size(); j++) {
                eventListView.getItems().add(events.get(j).getTitle().getName());
            }
        }




    }



}
