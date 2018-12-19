package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import versione1.Category;
import versione1.EventSoccerMatch;
import versione1.SocialNetwork;

import java.io.IOException;
import java.time.LocalDate;

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
    @FXML
    private DatePicker deadLine;




    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    boolean complete = true;

    // ~~~~~~ Campi FACOLTATIVI ~~~~~~~~

    String titleIns;
    int numParIns;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // ~~~~~~ Campi OBBLIGATORI ~~~~~~

    String category;
    LocalDate deadLineIns;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



    private SocialNetwork socialNetwork;
    private Category catSelected;
    private Stage thisStage;




    // ~~~~~~~~ Metodi ~~~~~~~~~~~~~


    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }

    public void setSocialNetwork(SocialNetwork socialNetwork) { this.socialNetwork = socialNetwork; }

    public SocialNetwork getSocialNetwork() { return socialNetwork;}


    @FXML
    private void initialize() throws IOException {

        catType.setItems(FXCollections.observableArrayList(SOCCER_NAME));

    }



    /**
     * Metodo per la creazione effettiva dell'evento, il metotodo applicato su thisStage chiude la finestra corrente una volta inseriti tutti
     * i campi e effettuati i controlli
     * @param actionEvent
     * @throws IOException
     */
    public void createEvent(ActionEvent actionEvent) throws IOException {




        do {
            category = catType.getSelectionModel().getSelectedItem();
            titleIns = title.getText();
            numParIns = Integer.parseInt(numPart.getText());
            deadLineIns = deadLine.getValue();

        } while (!complete);


        switch (category) {
            case SOCCER_NAME: {

                EventSoccerMatch match = new EventSoccerMatch(titleIns, numParIns);
                socialNetwork.getCategories().get(0).addEvent(match);
                thisStage.close();
            }
        }



    }
}
