package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import versione1.Category;
import versione1.EventSoccerMatch;
import versione1.SocialNetwork;

import java.io.IOException;

public class CreateController {

    public static final String SOCCER_NAME = "Partite di calcio";


    // ~~~~~ newEvent Stage ~~~~~~~~~~~

    @FXML
    private TextField title;
    @FXML
    private TextField numPart;
    @FXML
    private Button create;
    @FXML
    private ChoiceBox<String> catType;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    private SocialNetwork socialNetwork;
    private Category catSelected;


    // ~~~~~~~~ Metodi ~~~~~~~~~~~~~

    public void setSocialNetwork(SocialNetwork socialNetwork) { this.socialNetwork = socialNetwork; }

    public SocialNetwork getSocialNetwork() { return socialNetwork;}


    @FXML
    private void initialize() throws IOException {


        catType.setItems(FXCollections.observableArrayList(SOCCER_NAME));

    }



    /**
     * Metodo per la creazione effettiva dell'evento
     * @param actionEvent
     * @throws IOException
     */
    public void createEvent(ActionEvent actionEvent) throws IOException {

        String category = catType.getSelectionModel().getSelectedItem();
        String titleIns = title.getText();
        int numParIns = Integer.parseInt(numPart.getText());

        switch (category) {
            case SOCCER_NAME: {

                EventSoccerMatch match = new EventSoccerMatch(titleIns, numParIns);
                socialNetwork.getCategories().get(0).addEvent(match);
            }
        }



    }
}
