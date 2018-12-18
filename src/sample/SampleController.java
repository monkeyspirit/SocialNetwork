package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import versione1.Category;
import versione1.Event;
import versione1.SocialNetwork;

import java.io.IOException;
import java.util.ArrayList;

public class SampleController {

    public static final String SOCCER_NAME = "Partite di calcio";


    // ~~~~~ Sample Stage ~~~~~~~~~~~~~

    @FXML
    private ListView categoryListView;
    @FXML
    private ListView eventListView;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



    private Category catSelected;

    private SocialNetwork socialNetwork;
    private ArrayList<String> catName;
    private ArrayList<String> eventName;
    private ObservableList<String> obsCatName;
    private ObservableList<String> obsEventName;



    // ~~~~~~~~ Metodi ~~~~~~~~~~~~~

    public void setSocialNetwork(SocialNetwork socialNetwork) { this.socialNetwork = socialNetwork; }

    public SocialNetwork getSocialNetwork() { return socialNetwork;}




    @FXML
    private void initialize() throws IOException {

//  Funziona come pezzo di codice ma così è inutile
        categoryListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Category selected = null;

//                System.out.println("Hai cliccato su "+categoryListView.getSelectionModel().getSelectedIndex());
                selected = socialNetwork.findCategoryByIndex(categoryListView.getSelectionModel().getSelectedIndex());

                eventName = new ArrayList<>();

                for(Event match : selected.getEvents()){
                    eventName.add((String) match.getTitle().getValue());
                }

                obsEventName = FXCollections.observableArrayList(eventName);
                eventListView.setItems(obsEventName);

            }
        });

//        System.out.println("Carico la View Utente...");
        catName = new ArrayList<>();

        for(Category category : socialNetwork.getCategories()){
            catName.add(category.getName());
        }

        obsCatName = FXCollections.observableArrayList(catName);

        categoryListView.setItems(obsCatName);

    }


    /**
     * Metodo per l'apertura dell'editor per inserire un nuovo evento della categoria Partita di calcio
     * @param actionEvent
     * @throws IOException
     */
    public void openEventEditor(ActionEvent actionEvent) throws IOException {

        FXMLLoader loaderCreate = new FXMLLoader(Main.class.getResource("newEvent.fxml"));
        //loaderCreate.setController(this);
        CreateController createController = new CreateController();

        loaderCreate.setController(createController);

        createController.setSocialNetwork(socialNetwork);

        Stage create = new Stage();

        Parent eventCreate =  (Parent) loaderCreate.load();
        Scene scene = new Scene(eventCreate, 600, 400);
        create.setTitle("Crea");
        create.setScene(scene);
        create.show();

    }


//    /**
//     * Metodo per la creazione effettiva dell'evento
//     * @param actionEvent
//     * @throws IOException
//     */
//    public void createEvent(ActionEvent actionEvent) throws IOException {
//
//        ObservableList<String> listOfCat = FXCollections.observableArrayList(SOCCER_NAME);
//        catType= new ChoiceBox<>(listOfCat);
//        catType.setItems(listOfCat);
//
//
//        String category = catType.getSelectionModel().getSelectedItem();
//        String titleIns = title.getText();
//        int numParIns = Integer.parseInt(numPart.getText());
//
//        switch (category) {
//            case SOCCER_NAME: {
//
//                EventSoccerMatch match = new EventSoccerMatch(titleIns, numParIns);
//                socialNetwork.getCategories().get(0).addEvent(match);
//            }
//        }
//
//
//
//    }


}
