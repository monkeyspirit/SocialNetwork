package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import versione1.Category;
import versione1.Event;
import versione1.EventSoccerMatch;
import versione1.SocialNetwork;

import java.io.IOException;
import java.util.ArrayList;

public class SampleController {


    @FXML
    private ListView categoryListView;
    @FXML
    private ListView eventListView;

    // Crea evento
    @FXML
    private TextField title;
    @FXML
    private TextField numPart;
    @FXML
    private Button create;

    private Category catSelected;

    private SocialNetwork socialNetwork;
    private EventSoccerMatch eventSoccerMatch;
    private ObservableList<Category> categories;
    private ObservableList<Event> eventsSoccerMatch;
    private ObservableList<String> catname;
    ObservableList<String> Eventname;

    // Metodi

    public void setSocialNetwork(SocialNetwork socialNetwork) { this.socialNetwork = socialNetwork; }

    public SocialNetwork getSocialNetwork() { return socialNetwork;}

    public EventSoccerMatch getEventSoccerMatch() { return eventSoccerMatch; }

    public void setEventSoccerMatch(EventSoccerMatch eventSoccerMatch) { this.eventSoccerMatch = eventSoccerMatch; }


    public void showEvents(String categoryName) {
        Category category = null;
        for(Category cat : categories) {
            if(cat.getName().equals(categoryName)) {
                category = cat;
                break;
            }
        }
        if(eventListView.getItems().size()==0) {
            eventsSoccerMatch = FXCollections.observableArrayList(category.getEvents());
            for (int j=0; j<eventsSoccerMatch.size(); j++) {
                eventListView.getItems().add(eventsSoccerMatch.get(j).getTitle().getValue());
            }
        }

    }



    @FXML
    private void initialize() throws IOException {

//  Funziona come pezzo di codice ma così è inutile
        categoryListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                for(Category cat : socialNetwork.getCategories()) {
                    if(cat.getName().equals(categoryListView.getSelectionModel().getSelectedItems())) {
                        catSelected = cat;
                        break;
                    }
                }
                System.out.println("Hai cliccato su "+categoryListView.getSelectionModel().getSelectedItems());

                ArrayList<String> eventnameStr = new ArrayList<>();
                for(int i=0; i<catSelected.getEvents().size(); i++) {
                    eventnameStr.add((String) catSelected.getEvents().get(i).getTitle().getValue());
                     }
                Eventname = FXCollections.observableArrayList(eventnameStr);

                eventListView.setItems(Eventname);
            }
        });

        System.out.println("Carico la View Utente...");

        ArrayList<String> catnameStr = new ArrayList<>();
        for(int i=0; i<socialNetwork.getCategories().size(); i++) {
            catnameStr.add(socialNetwork.getCategories().get(i).getName());
        }
        catname = FXCollections.observableArrayList(catnameStr);
        categoryListView.setItems(catname);

        System.out.println(" Le categorie sono: " + socialNetwork.getCategories().size());
        System.out.println( " Gli eventi sono: "+ socialNetwork.getCategories().get(0).getEvents().size());

    }

    public void openEventEditor(ActionEvent actionEvent) throws IOException {

        FXMLLoader loaderCreate = new FXMLLoader(Main.class.getResource("newEvent.fxml"));

        // Imposto il controller
        loaderCreate.setController(this);

        Stage create = new Stage();

        Parent eventcreate =  (Parent) loaderCreate.load();
        Scene scene = new Scene(eventcreate, 600, 400);
        create.setTitle("Crea");
        create.setScene(scene);
        create.show();

    }

    public void createEvent(ActionEvent actionEvent) throws IOException {

        String titolo = title.getText();
        int numeroPart = Integer.parseInt(numPart.getText());
        EventSoccerMatch partita = new EventSoccerMatch(titolo, numeroPart);

        socialNetwork.getCategories().get(0).addEvent(partita);


    }


}
