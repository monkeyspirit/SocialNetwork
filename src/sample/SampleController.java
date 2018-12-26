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
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import versione1.Category;
import versione1.Event;
import versione1.SocialNetwork;
import versione1.User;

import java.io.IOException;
import java.util.ArrayList;

public class SampleController {

    public static final String SOCCER_NAME = "Partite di calcio";


    // ~~~~~ Sample Stage ~~~~~~~~~~~~~

    @FXML
    private ListView categoryListView;
    @FXML
    private ListView eventListView;
    @FXML
    private Tab userTb;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



    private Category catSelected;

    private SocialNetwork socialNetwork;
    private User sessionUser;
    private ArrayList<String> catName;
    private ArrayList<String> eventName;
    private ObservableList<String> obsCatName;
    private ObservableList<String> obsEventName;



    // ~~~~~~~~ Metodi ~~~~~~~~~~~~~

    public void setSocialNetwork(SocialNetwork socialNetwork) { this.socialNetwork = socialNetwork; }

    public SocialNetwork getSocialNetwork() { return socialNetwork;}

    public User getSessionUserUser() { return sessionUser; }

    public void setSessionUser(User sessionUser) { this.sessionUser = sessionUser; }

    @FXML
    private void initialize() throws IOException {

        userTb.setText(sessionUser.getUsername());

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

        System.out.println("Carico la View Utente di: "+sessionUser.getUsername());
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
        createController.setCreator(sessionUser);

        Stage create = new Stage();

        createController.setThisStage(create);
        Parent eventCreate =  (Parent) loaderCreate.load();
        Scene scene = new Scene(eventCreate, 600, 400);
        create.setTitle("Crea");
        create.setScene(scene);
        create.show();


    }




}
